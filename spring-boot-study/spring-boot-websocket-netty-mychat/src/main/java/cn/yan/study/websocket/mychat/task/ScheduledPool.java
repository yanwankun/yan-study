package cn.yan.study.websocket.mychat.task;

import cn.yan.study.websocket.mychat.common.pool.Scheduled;
import cn.yan.study.websocket.mychat.common.properties.InitNetty;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


/**
 * @author  MySelf
 * @create  2018/9/22
 * @desc 定时任务
 **/
@Service
public class ScheduledPool implements Scheduled {

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(20);

    private final InitNetty serverBean;

    public ScheduledPool(InitNetty serverBean) {
        this.serverBean = serverBean;
    }

    @Override
    public ScheduledFuture<?> submit(Runnable runnable) {
        int initalDelay = serverBean.getInitalDelay();
        int period = serverBean.getPeriod();
        return scheduledExecutorService.scheduleAtFixedRate(runnable, initalDelay, period, TimeUnit.SECONDS);
    }

}
