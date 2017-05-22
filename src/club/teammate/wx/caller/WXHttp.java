package club.teammate.wx.caller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Request.Builder;
import org.springframework.stereotype.Component;

@Component
public class WXHttp {
    private static final Logger logger = Logger.getLogger("wx-api");
    private static final OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");

    public WXHttp() {
    }

    public JSONObject get(String url, Map<String, Object> params) throws IOException {
        String newUrl = this.getNewUrl(url, params);
        logger.info("GET:" + newUrl);
        Request request = (new Builder()).url(newUrl).header("Content-Type", "application/json; charset=utf-8").build();
        Response response = client.newCall(request).execute();
        String result = new String(response.body().bytes(), "UTF-8");
        logger.info("RESULT:" + result);
        return JSON.parseObject(result);
    }

    public JSONObject post(String url, Map<String, Object> params) throws IOException {
        String newUrl = this.getNewUrl(url, params);
        logger.info("POST:" + newUrl);
        RequestBody body = RequestBody.create(JSON_TYPE, this.getJsonParams(params));
        Request request = (new Builder()).url(newUrl).post(body).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        logger.info("RESULT:" + result);
        return JSON.parseObject(result);
    }

    private String getNewUrl(String url, Map<String, Object> map) {
        if(map == null) {
            return url;
        } else {
            String prefix;
            if(url.indexOf("?") > 0) {
                prefix = "&";
            } else {
                prefix = "?";
            }

            StringBuffer sb = new StringBuffer(prefix);
            Iterator i$ = map.entrySet().iterator();

            while(i$.hasNext()) {
                Entry<String, Object> entry = (Entry)i$.next();
                sb.append(entry.getKey() + "=" + entry.getValue());
                sb.append("&");
            }

            String s = sb.toString();
            s = s.substring(0, s.length() - 1);
            return url + s;
        }
    }

    private String getJsonParams(Map<String, Object> map) {
        return map == null?"{}":JSON.toJSONString(map);
    }
}
