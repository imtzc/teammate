package club.teammate.wx.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by don on 2017/4/15.
 */
public final class BeanFactory {

    private static final Map<Class, Object> beans = new ConcurrentHashMap<>();

    public static <T> T getBean(Class<T> clazz) {

        T t = (T) beans.get(clazz);

        if(t == null) {

            try {

                t = clazz.newInstance();
                beans.put(clazz, t);
            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        return t;
    }
}
