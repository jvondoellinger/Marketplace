package zjg.marketplace.infrastructure.logging.loki.models;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StreamsModel {
    protected StreamsModel() {}

    private List<Streams> model;

    public List<Streams> getStreams() {
        return this.model;
    }
    public void setStreams(List<Streams> streams) {
        this.model = streams;
    }
    public void addStreams(Streams streams) {
        if(Objects.isNull(model)) this.model = new ArrayList<>();
        this.model.add(streams);
    }

    public static class Streams {
        public Streams(Stream stream, List<List<String>> values){
            this.stream = stream;
            this.values = values;
        }
        public Streams(Stream stream){
            this.stream = stream;
        }
        private Stream stream;
        private List<List<String>> values;

        public List<List<String>> getValues() {
            return values;
        }
        public void setValues(List<List<String>> values) {
            this.values = values;
        }

        public void addValue(String str) {
            if(Objects.isNull(values)) this.values = new ArrayList<>();
            long instant = (Instant.now().toEpochMilli() * 1_000_000);
            var parsed = String.valueOf(instant);
            var value = List.of(parsed, str);
            values.add(value);
        }
        public Stream getStream() {
            return stream;
        }
        public void setStream(Stream stream) {
            this.stream = stream;
        }

    }

    public static class Stream {
        public Stream(String app, String level) {
            this.app = app;
            this.level = level;
        }
        private String app;
        private String level;

        public String getApp() {
            return app;
        }
        public String getLevel() {
            return level;
        }

        public void setApp(String app) {
            this.app = app;
        }
        public void setLevel(String level) {
            this.level = level;
        }
    }
}
