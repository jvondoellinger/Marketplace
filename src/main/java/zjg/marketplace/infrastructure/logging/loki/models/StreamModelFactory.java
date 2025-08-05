package zjg.marketplace.infrastructure.logging.loki.models;

import org.springframework.stereotype.Service;
import zjg.marketplace.application.config.app.AppInfo;
import zjg.marketplace.core.valueObjects.log.Log;

@Service
public class StreamModelFactory {
    private AppInfo info;
    public StreamModelFactory(AppInfo info) {
        this.info = info;
    }

    public StreamsModel factory(Log log) {
        var streamsModel = new StreamsModel();
        var stream = new StreamsModel.Stream(info.getAppName(), log.getLevel().toString());
        var streams = new StreamsModel.Streams(stream);
        var formated = String.format("ORIGIN: %s \nMESSAGE: %s \nROOT CAUSE: %s",
                log.getOrigin(), log.getMessage(), log.getRoot());
        streams.addValue(formated);
        streamsModel.addStreams(streams);
        return streamsModel;
    }
}
