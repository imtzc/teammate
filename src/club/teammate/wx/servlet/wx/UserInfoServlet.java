package club.teammate.wx.servlet.wx;

import club.teammate.wx.service.wx.UserInfoService;
import club.teammate.wx.servlet.BaseServlet;
import club.teammate.wx.util.BeanFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * Created by don on 2017/4/15.
 */
@WebServlet("/user/info")
public class UserInfoServlet extends BaseServlet {

    @Override
    protected void execute() {

        String ticket = getParam("ticket");

        try {
            String openid = BeanFactory.getBean(UserInfoService.class).getOpenId(ticket);
            JSONObject jsonObject = BeanFactory.getBean(UserInfoService.class).getUserInfo(openid);
            putResult("user_info", JSON.toJSONString(jsonObject));
        } catch (IOException e) {
            e.printStackTrace();
            putResult("user_info", "{}");
        }
    }
}
