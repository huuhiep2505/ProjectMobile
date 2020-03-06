package hiepnh.se1304_nguyenhuuhiep.dtos;

import java.io.Serializable;

public class HistoryDTO implements Serializable {
    String id,timeFrom, timeTo, status, username;

    public HistoryDTO() {
    }

    public HistoryDTO(String id, String timeFrom, String timeTo, String status, String username) {
        this.id = id;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.status = status;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
