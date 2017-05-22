package club.teammate.wx.servlet.wx.jsapi;

import club.teammate.wx.service.wx.JsApiTicketService;
import club.teammate.wx.servlet.BaseServlet;
import club.teammate.wx.util.BeanFactory;

import javax.servlet.annotation.WebServlet;

/**
 * Created by don on 2017/4/15.
 */
@WebServlet("/wx/jsapi/ticket")
public class JsApiTicketServlet extends BaseServlet {

    @Override
    protected void execute() {

        String ticket = BeanFactory.getBean(JsApiTicketService.class).getJsApiTicket();

        putResult("ticket", ticket);
    }
}
