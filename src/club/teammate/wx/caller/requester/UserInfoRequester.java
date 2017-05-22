package club.teammate.wx.caller.requester;

import club.teammate.wx.caller.WXHttp;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInfoRequester {
    @Autowired
    private AccessTokenRequester accessTokenRequester;
    @Autowired
    private WXHttp wxHttp;

    public UserInfoRequester() {
    }

    public JSONObject call(String openid) {
        String url = "https://api.weixin.qq.com/cgi-bin/user/info";
        String accessToken = this.accessTokenRequester.call();
        Map<String, Object> params = new HashMap();
        params.put("openid", openid);
        params.put("access_token", accessToken);
        params.put("lang", "zh_CN");
        JSONObject userInfo = null;

        try {
            userInfo = this.wxHttp.get(url, params);
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        return userInfo;
    }
}
