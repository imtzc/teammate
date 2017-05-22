package club.teammate.wx.service.wx;

import club.teammate.wx.http.WX;
import club.teammate.wx.util.BeanFactory;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by don on 2017/4/15.
 */
public class JsApiTicketService {

    private static String jsApiTicket;

    public void updateJsApiTicket() throws IOException {

        JSONObject result = WX.get(new WX.WXRequest() {

            @Override
            public String getUrl() {
                return "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
            }

            @Override
            public Map<String, Object> getParams() {

                String accessToken = BeanFactory.getBean(AccessTokenService.class).getAccessToken();
                Map<String, Object> params = new HashMap<>();
                params.put("type", "grant_type");
                params.put("access_token", accessToken);
                return params;
            }
        });

        jsApiTicket = (String) result.get("ticket");
    }

    public String getJsApiTicket() {

        return jsApiTicket;
    }
}
