package zjg.marketplace.infrastructure.worker;

import org.springframework.stereotype.Service;
import zjg.marketplace.core.logging.model.Log;
import zjg.marketplace.infrastructure.logging.loki.config.LokiConfig;
import zjg.marketplace.infrastructure.logging.loki.models.StreamModelFactory;
import zjg.marketplace.infrastructure.request.RequisitionService;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class SimpleLogWorker {
    private final BlockingQueue<Log> queue = new LinkedBlockingQueue<>();
    private final AtomicBoolean running = new AtomicBoolean(true);
    private final RequisitionService service;
    private final StreamModelFactory factory;
    private final LokiConfig config;

    public SimpleLogWorker(RequisitionService service, StreamModelFactory factory, LokiConfig config) {
        this.service = service;
        this.factory = factory;
        this.config = config;
        Thread.startVirtualThread(this::process);
    }
    public void enqueueLog(Log log) {
        queue.add(log);
    }

    protected void process() {
        while (running.get() || !queue.isEmpty()){
            try {
                var log = queue.poll(1, TimeUnit.SECONDS);
                if(log == null) continue;
                var streams = factory.factory(log);
                service.post(config.getUrl(), streams).block();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void shutdown() {
        running.set(false);
    }
}
