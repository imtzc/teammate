package club.teammate.wx.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by don on 16/9/8.
 */
public class ExecutorFactory {

    private ExecutorFactory(){}

    public static ExecutorService getFixedThreadPool(){

        return SingleHolder.FIXED_THREAD_POOL;
    }

    public static ExecutorService getCachedThreadPool(){

        return SingleHolder.CACHED_THREAD_POOL;
    }

    public static ScheduledExecutorService getScheduledThreadPool(){

        return SingleHolder.SCHEDULED_THREAD_POOL;
    }

    public static void shutdownNow(){

        SingleHolder.FIXED_THREAD_POOL.shutdownNow();
        SingleHolder.CACHED_THREAD_POOL.shutdownNow();
        SingleHolder.SCHEDULED_THREAD_POOL.shutdownNow();
    }

    private static class SingleHolder{

        private static final ExecutorService FIXED_THREAD_POOL = Executors.newFixedThreadPool(10);
        private static final ExecutorService CACHED_THREAD_POOL = Executors.newCachedThreadPool();
        private static final ScheduledExecutorService SCHEDULED_THREAD_POOL = Executors.newScheduledThreadPool(10);
    }
}
