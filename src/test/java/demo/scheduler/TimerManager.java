package demo.scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhe.liang
 * @create: 2024-01-08 19:48
 **/
public class TimerManager implements Scheduler{

    private final ScheduledExecutorService executorService;


    public TimerManager(int workerNum){
        this(workerNum, "JRaft-ScheduleThreadPool");
    }



    public TimerManager(int workerNum,String name){
        this.executorService = Executors.newScheduledThreadPool(workerNum);
    }

    @Override
    public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
        return this.executorService.schedule(command, delay, unit);
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        return this.executorService.scheduleAtFixedRate(command, initialDelay, period, unit);
    }

    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
        return this.executorService.scheduleWithFixedDelay(command, initialDelay, delay, unit);
    }

    @Override
    public void shutdown() {
        this.executorService.shutdown();
    }
}
