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
public class AccessTokenService {

    private static String accessToken;

    private final static String APPID = "wx419e2770827962b5";
    private final static String APPSECRET = "63187aecaadea1c1bacf429925a430d5";

    public void updateAccessToken() throws IOException {

        JSONObject result = WX.get(new WX.WXRequest() {

            @Override
            public String getUrl() {
                return "https://api.weixin.qq.com/cgi-bin/token";
            }

            @Override
            public Map<String, Object> getParams() {

                Map<String, Object> params = new HashMap<>();
                params.put("grant_type", "client_credential");
                params.put("appid", APPID);
                params.put("secret", APPSECRET);
                return params;
            }
        });

        accessToken = (String) result.get("access_token");

        // 更新 jsapi api ticket
//        BeanFactory.getBean(JsApiTicketService.class).updateJsApiTicket();
    }

    public String getAccessToken() {

        return accessToken;
    }
}
