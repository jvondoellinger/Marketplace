package zjg.marketplace.infrastructure.logging.loki.impl;

import org.springframework.stereotype.Service;
import zjg.marketplace.core.valueObjects.log.Log;
import zjg.marketplace.core.enums.LogLevel;
import zjg.marketplace.core.factory.log.LogFactory;
import zjg.marketplace.core.interfaces.services.logging.ILogger;
import zjg.marketplace.infrastructure.logging.loki.models.StreamModelFactory;
import zjg.marketplace.infrastructure.request.IRequisitionService;
import zjg.marketplace.infrastructure.worker.SimpleLogWorker;

@Service
public class LokiLoggerImpl implements ILogger {
    private final StreamModelFactory factory;
    private final IRequisitionService service;
    private final SimpleLogWorker worker;
    public LokiLoggerImpl(StreamModelFactory factory, IRequisitionService service, SimpleLogWorker worker) {
        this.factory = factory;
        this.service = service;
        this.worker = worker;
    }

    // Info -------------------------------------------------------------------------------------------------------
    @Override
    public void info(String message) {
        var log = LogFactory.factory(LogLevel.INFO, Void.class, message, "N/A");
        send(log);
    }

    @Override
    public <Origin> void info(Class<Origin> origin, String message) {
        var log = LogFactory.factory(LogLevel.INFO, origin, message, "N/A");
        send(log);
    }

    @Override
    public <Origin, E extends Throwable> void info(Class<Origin> origin, String message, E exception) {
        var log = LogFactory.factory(LogLevel.INFO, origin, message, "WAITING IMPLEMENTATION");
        send(log);
    }

    // Warning -------------------------------------------------------------------------------------------------------
    @Override
    public void warning(String message) {
        var log = LogFactory.factory(LogLevel.WARNING, Void.class, message, "N/A");
        send(log);
    }

    @Override
    public <Origin> void warning(Class<Origin> origin, String message) {
        var log = LogFactory.factory(LogLevel.WARNING, origin, message, "N/A");
        send(log);
    }

    @Override
    public <Origin, TException extends Throwable> void warning(Class<Origin> origin, String message, TException e) {
        var log = LogFactory.factory(LogLevel.WARNING, origin, message, "WAITING IMPLEMENTATION");
        send(log);
    }

    // Error -------------------------------------------------------------------------------------------------------
    @Override
    public void error(String message) {
        var log = LogFactory.factory(LogLevel.ERROR, Void.class, message, "N/A");
        send(log);
    }

    @Override
    public <Origin> void error(Class<Origin> origin, String message) {
        var log = LogFactory.factory(LogLevel.ERROR, origin, message, "N/A");
        send(log);
    }

    @Override
    public <Origin, TException extends Throwable> void error(Class<Origin> origin, String message, TException e) {
        var log = LogFactory.factory(LogLevel.ERROR, origin, message, "WAITING IMPLEMENTATION");
        send(log);
    }

    private void send(Log log) {
        try{
            worker.enqueueLog(log);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
