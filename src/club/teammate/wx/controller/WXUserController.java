package club.teammate.wx.controller;

import club.teammate.wx.caller.requester.AccessTokenRequester;
import club.teammate.wx.caller.requester.UserInfoRequester;
import club.teammate.wx.caller.requester.UserOpenIdRequester;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/wxuser"})
public class WXUserController {
    @Autowired
    private AccessTokenRequester accessTokenRequester;
    @Autowired
    private UserOpenIdRequester userOpenIdRequester;
    @Autowired
    private UserInfoRequester userInfoRequester;

    public WXUserController() {
    }

    @ResponseBody
    @RequestMapping({"/token"})
    public String getToken() {
        String token = this.accessTokenRequester.call();
        return token;
    }

    @ResponseBody
    @RequestMapping({"/openid"})
    public String getOpenId(@RequestParam String ticket) {
        String openid = this.userOpenIdRequester.call(ticket);
        return openid;
    }

    @ResponseBody
    @RequestMapping({"/info"})
    public JSONObject getWXInfo(@RequestParam String ticket) {
        String openid = this.userOpenIdRequester.call(ticket);
        JSONObject userinfo = this.userInfoRequester.call(openid);
        return userinfo;
    }
}
