package club.teammate.wx.service;

import club.teammate.wx.UserVo;
import club.teammate.wx.domain.ActiveLog;
import club.teammate.wx.domain.User;
import club.teammate.wx.mapper.ActiveLogMapper;
import club.teammate.wx.mapper.ActiveMapper;
import club.teammate.wx.mapper.UserMapper;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ActiveMapper activeMapper;
    @Autowired
    private ActiveLogMapper activeLogMapper;

    public UserService() {
    }

    public User selectById(Integer id) {
        return this.userMapper.selectById(id.intValue());
    }

    public User selectByOpenid(String openid) {
        User user = this.userMapper.selectByOpenid(openid);
        if(user == null) {
            user = new User();
        }

        return user;
    }

    public UserVo selectWithJoinByOpenid(String openid) {
        User user = this.userMapper.selectByOpenid(openid);
        UserVo vo;
        if(user != null) {
            int num = this.activeLogMapper.checkJoinTodayActive(user.getId());
            vo = new UserVo(user, num > 0);
        } else {
            vo = new UserVo();
        }

        return vo;
    }

    @Transactional
    public int save(User user) {
        User old_user = this.userMapper.selectByOpenid(user.getOpenid());
        if(old_user != null) {
            user.setId(old_user.getId());
            this.userMapper.update(user);
        } else {
            this.userMapper.insert(user);
        }

        ActiveLog log = new ActiveLog();
        log.setUserId(user.getId());
        log.setActiveId(this.activeMapper.selectTodayActive().getId());
        log.setDatetime(new Date());
        this.activeLogMapper.insert(log);
        return this.activeLogMapper.selectCount(user.getId());
    }
}
