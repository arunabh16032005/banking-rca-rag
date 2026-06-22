//Every future system component will depend on this structure


package model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

// This class represents one log event flowing through the system

@Document(indexName = "banking-logs")
public class LogEvent {

    @Id
    private String id;

    private String service;
    private String level;
    private String message;
    private String timestamp;

    public LogEvent() {
    }

    public LogEvent(String id,
                    String service,
                    String level,
                    String message,
                    String timestamp) {

        this.id = id;
        this.service = service;
        this.level = level;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}