package club.teammate.wx.listener;

import club.teammate.wx.service.wx.AccessTokenService;
import club.teammate.wx.util.BeanFactory;
import club.teammate.wx.util.ExecutorFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by don on 2017/4/15.
 */
@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        // 初始化 access token 的更新任务
        ExecutorFactory.getScheduledThreadPool().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                AccessTokenService accessTokenService = BeanFactory.getBean(AccessTokenService.class);
                try {
                    accessTokenService.updateAccessToken();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 100, TimeUnit.MINUTES);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        ExecutorFactory.shutdownNow();
    }
}
