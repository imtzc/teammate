package club.teammate.wx.domain;

import java.util.Date;

public class ActiveLog {
    private int id;
    private int userId;
    private int activeId;
    private Date datetime;

    public ActiveLog() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getActiveId() {
        return this.activeId;
    }

    public void setActiveId(int activeId) {
        this.activeId = activeId;
    }

    public Date getDatetime() {
        return this.datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
