package club.teammate.wx.mapper;

import club.teammate.wx.domain.ActiveLog;

public interface ActiveLogMapper {
    int insert(ActiveLog var1);

    int selectCount(int var1);

    int checkJoinTodayActive(int var1);
}