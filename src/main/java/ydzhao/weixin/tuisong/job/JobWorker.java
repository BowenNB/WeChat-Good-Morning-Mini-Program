package ydzhao.weixin.tuisong.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ydzhao.weixin.tuisong.util.Pusher;

/**
 *@ClassName JobWorker
 *@Description TODO
 *@Author ydzhao
 *@Date 2022/8/2 16:00
 */
@Component
public class JobWorker {
    private static final Logger logger = LoggerFactory.getLogger(JobWorker.class);

    @Autowired
    private Pusher pusher; //改为依赖注入
    //要推送的用户openid
    private static String openId = "oeDFM7Oq6pvmUC9zRzjlqY0aHQT8";

    @Scheduled(cron = "0 20 05 * * ?", zone = "Asia/Shanghai") //添加了时区
    //@Scheduled(cron = "0/30 * * * * ?", zone = "Asia/Shanghai")
    public void goodMorning(){
        try{
            logger.info("开始执行定时推送任务...");
            pusher.push(openId);
            logger.info("推送任务执行完成");
        }catch (Exception e){
            logger.error("推送任务失败", e);
        }

    }

}
