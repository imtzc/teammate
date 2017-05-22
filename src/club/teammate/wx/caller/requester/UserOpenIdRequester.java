package club.teammate.wx.caller.requester;

import club.teammate.wx.caller.WXHttp;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserOpenIdRequester {
    @Autowired
    private AccessTokenRequester accessTokenRequester;
    @Autowired
    private WXHttp wxHttp;

    public UserOpenIdRequester() {
    }

    public String call(String ticket) {
        String accessToken = this.accessTokenRequester.call();
        String url = "https://api.weixin.qq.com/shakearound/user/getshakeinfo?access_token=" + accessToken;
        Map<String, Object> params = new HashMap();
        params.put("ticket", ticket);
        JSONObject result = null;

        try {
            result = this.wxHttp.post(url, params);
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        String openid = result.getJSONObject("data").getString("openid");
        return openid;
    }
}
