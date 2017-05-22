package club.teammate.wx.service.wx;

import club.teammate.wx.http.WX;
import club.teammate.wx.util.BeanFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by don on 2017/4/15.
 */
public class UserInfoService {

    public JSONObject getUserInfo(String openid) throws IOException {

        JSONObject userInfo = WX.get(new WX.WXRequest() {

            @Override
            public String getUrl() {
                return "https://api.weixin.qq.com/cgi-bin/user/info";
            }

            @Override
            public Map<String, Object> getParams() {

                String accessToken = BeanFactory.getBean(AccessTokenService.class).getAccessToken();
                Map<String, Object> params = new HashMap<>();
                params.put("openid", openid);
                params.put("access_token", accessToken);
                params.put("lang", "zh_CN");
                return params;
            }
        });

        return userInfo;
    }

    public String getOpenId(String ticket) throws IOException {

        JSONObject result = WX.post(new WX.WXRequest() {

            @Override
            public String getUrl() {

                String accessToken = BeanFactory.getBean(AccessTokenService.class).getAccessToken();
                return "https://api.weixin.qq.com/shakearound/user/getshakeinfo?access_token=" + accessToken;
            }

            @Override
            public Map<String, Object> getParams() {

                Map<String, Object> params = new HashMap<>();
                params.put("ticket", ticket);
                return params;
            }
        });

        String openid = result.getJSONObject("data").getString("openid");

        return openid;
    }

}
