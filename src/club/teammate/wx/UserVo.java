package club.teammate.wx;

import club.teammate.wx.domain.User;

public class UserVo extends User {
    private boolean hasJoin;

    public UserVo() {
    }

    public UserVo(User user, boolean hasJoin) {
        super(user.getId(), user.getOpenid(), user.getRealName(), user.getMark(), user.getPhone());
        this.hasJoin = hasJoin;
    }

    public boolean isHasJoin() {
        return this.hasJoin;
    }

    public void setHasJoin(boolean hasJoin) {
        this.hasJoin = hasJoin;
    }
}