package club.teammate.wx.domain;

public class User {

    private int id;
    private String openid;
    private String realName;
    private String mark;
    private String phone;

    public User() {
    }

    public User(int id, String openid, String realName, String mark, String phone) {
        this.id = id;
        this.openid = openid;
        this.realName = realName;
        this.mark = mark;
        this.phone = phone;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpenid() {
        return this.openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getRealName() {
        return this.realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMark() {
        return this.mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

