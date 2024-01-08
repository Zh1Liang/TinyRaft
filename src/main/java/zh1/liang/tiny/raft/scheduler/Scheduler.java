package zh1.liang.tiny.raft.scheduler;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhe.liang
 * @create: 2024-01-08 19:30
 **/
public interface Scheduler {

    ScheduledFuture<?> schedule(final Runnable command, final long delay, final TimeUnit unit);


    ScheduledFuture<?> scheduleAtFixedRate(final Runnable command, final long initialDelay,final long period, final TimeUnit unit);


    ScheduledFuture<?> scheduleWithFixedDelay(final Runnable command, final long initialDelay, final long delay, final TimeUnit unit);

    void shutdown();


}
