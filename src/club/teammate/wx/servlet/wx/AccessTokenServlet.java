package club.teammate.wx.servlet.wx;

import club.teammate.wx.service.wx.AccessTokenService;
import club.teammate.wx.servlet.BaseServlet;
import club.teammate.wx.util.BeanFactory;

import javax.servlet.annotation.WebServlet;

/**
 * Created by don on 2017/4/15.
 */
@WebServlet("/wx/token")
public class AccessTokenServlet extends BaseServlet {

    @Override
    protected void execute() {

        String accessToken = BeanFactory.getBean(AccessTokenService.class).getAccessToken();

        putResult("token", accessToken);
    }
}
