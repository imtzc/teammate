package club.teammate.wx.mapper;

import club.teammate.wx.domain.User;

public interface UserMapper {
    User selectById(int var1);

    User selectByOpenid(String var1);

    int insert(User var1);

    int update(User var1);
}
