package ydzhao.weixin.tuisong.controller;

/**
 * @ClassName PushController
 * @Description TODO
 * @Author ydzhao
 * @Date 2022/8/2 15:48
 */

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ydzhao.weixin.tuisong.util.Pusher;

@RestController
public class PushController {
    //要推送的用户openid
    private static String wh = "oeDFM7Oq6pvmUC9zRzjlqY0aHQT8";
    //private static String zyd = "odbd-6U6ygdSTCwldsJ6qs0kxXeA";
    private static String zx = "";
    private static String hm = "";
    private static String wf = "";
    private static String test1 = "oeDFM7Oq6pvmUC9zRzjlqY0aHQT8";
    private static String test2 = "";



    /**
     * 微信测试账号推送
     *
     */
    @GetMapping("/push")
    public void push() {
        Pusher.push(wh);
    }

    /**
     * 微信测试账号推送
     * */
//    @GetMapping("/push/test1")
//    public void pushTest1() {
//        Pusher.push(test1);
//    }


    /**
     * 微信测试账号推送
     * */
    @GetMapping("/push/{id}")
    public void pushId(@PathVariable("id") String id) {
        Pusher.push(id);
    }
}