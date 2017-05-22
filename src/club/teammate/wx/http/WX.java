package club.teammate.wx.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by don on 2017/4/15.
 */
public class WX {

    private static final Logger logger = Logger.getLogger("wx-api");
    private static final OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");

    public static JSONObject get(WXRequest wxRequest) throws IOException {

        logger.info("GET:" + wxRequest.getUrl() + wxRequest.getUrlParams());

        Request request = new Request.Builder()
                .url(wxRequest.getUrl() + wxRequest.getUrlParams())
                .header("Content-Type", "application/json; charset=utf-8")
                .build();

        Response response = client.newCall(request).execute();

        String result = new String(response.body().bytes(), "UTF-8");

        logger.info("RESULT:" + result);
        return JSON.parseObject(result);
    }

    public static JSONObject post(WXRequest wxRequest) throws IOException {

        logger.info("POST:" + wxRequest.getUrl() + wxRequest.getUrlParams());

        RequestBody body = RequestBody.create(JSON_TYPE, wxRequest.getJsonParams());
        Request request = new Request.Builder()
                .url(wxRequest.getUrl())
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();

        logger.info("RESULT:" + result);
        return JSON.parseObject(result);
    }

    public abstract static class WXRequest {

        public abstract String getUrl();

        public abstract Map<String, Object> getParams();

        String getUrlParams() {

            Map<String, Object> map = getParams();
            if (map == null) {
                return "";
            }
            StringBuffer sb = new StringBuffer("?");
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                sb.append(entry.getKey() + "=" + entry.getValue());
                sb.append("&");
            }
            String s = sb.toString();
            s = s.substring(0, s.length() - 1);
            return s;
        }

        String getJsonParams() {

            Map<String, Object> map = getParams();
            if (map == null) {
                return "{}";
            }

            return JSON.toJSONString(map);
        }
    }
}
