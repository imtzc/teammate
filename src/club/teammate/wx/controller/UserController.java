package club.teammate.wx.controller;

import club.teammate.wx.UserVo;
import club.teammate.wx.domain.User;
import club.teammate.wx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/user"})
public class UserController {
    @Autowired
    private UserService userService;

    public UserController() {
    }

    @ResponseBody
    @RequestMapping({"/create"})
    public int insert(@ModelAttribute User user) {
        int activeTime = this.userService.save(user);
        return activeTime;
    }

    @ResponseBody
    @RequestMapping({"/get"})
    public User getUser(@RequestParam Integer id) {
        User user = this.userService.selectById(id);
        return user;
    }

    @ResponseBody
    @RequestMapping({"/get_by_openid"})
    public UserVo getUserByOpenid(@RequestParam String openid) {
        UserVo user = this.userService.selectWithJoinByOpenid(openid);
        return user;
    }
}
