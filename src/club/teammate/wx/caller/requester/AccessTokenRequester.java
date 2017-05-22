package club.teammate.wx.caller.requester;

import club.teammate.wx.caller.WXHttp;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AccessTokenRequester {
    private static String accessToken;
    @Value("${wx.appid}")
    private String APPID;
    @Value("${wx.appsecret}")
    private String APPSECRET;
    @Autowired
    private WXHttp wxHttp;

    public AccessTokenRequester() {
    }

//    @PostConstruct
    @Scheduled(
            cron = "0 0/110 * * * ?"
    )
    public void update() throws IOException {
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        Map<String, Object> params = new HashMap();
        params.put("grant_type", "client_credential");
        params.put("appid", this.APPID);
        params.put("secret", this.APPSECRET);
        JSONObject result = this.wxHttp.get(url, params);
        accessToken = (String)result.get("access_token");
    }

    public String call() {
        return accessToken;
    }
}
