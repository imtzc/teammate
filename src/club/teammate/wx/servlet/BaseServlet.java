package club.teammate.wx.servlet;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by don on 2017/4/15.
 */
public abstract class BaseServlet extends HttpServlet {

    protected ThreadLocal<HttpServletRequest> localRequest = new ThreadLocal<>();
    protected ThreadLocal<HttpServletResponse> localResponse = new ThreadLocal<>();

    protected ThreadLocal<Map<String, Object>> resultMap = new ThreadLocal<Map<String, Object>>() {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };
    protected ThreadLocal<Map<String, String[]>> paramsMap = new ThreadLocal<Map<String, String[]>>() {
        @Override
        protected Map<String, String[]> initialValue() {
            return new HashMap<>();
        }
    };

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        localRequest.set(request);
        localResponse.set(response);
        paramsMap.set(request.getParameterMap());

        execute();

        response.setHeader("Content-type", "application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.print(JSON.toJSONString(resultMap.get()));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract void execute();

    protected String getParam (String key){

        String[] vals = paramsMap.get().get(key);

        return vals.length > 0 ? vals[0] : null;
    }

    protected String[] getParams (String key){

        return paramsMap.get().get(key);
    }

    protected void putResult (String key, Object val){

        resultMap.get().put(key, val);
    }
}
