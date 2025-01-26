package ydzhao.weixin.tuisong.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @ClassName JiNianRi
 * @Description TODO
 * @Author ydzhao
 * @Date 2022/8/2 17:32
 */
public class JiNianRi {
    /**
     * 恋爱（认识）
     */
    static String lianAi = "2022-07-08";
    /**
     * 领证（毕业）
     */
    static String linZheng = "2025-06-01";
    /**
     * 结婚
     */
    static String jieHun = "2022-07-08";
    /**
     * 生日
     */
    static String shengRi = "2025-09-19";

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 距离date还有多少天
     * @param date
     * @return
     */
    public static int before(String date) {
        int day = 0;
        try {
            long time = simpleDateFormat.parse(date).getTime() - System.currentTimeMillis();
            day = (int) (time / 86400000L);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }


    /**
     * 已经过去date多少天
     * @param date
     * @return
     */
    public static int after(String date) {
        int day = 0;
        try {
            long time = System.currentTimeMillis() - simpleDateFormat.parse(date).getTime();
            day = (int) (time / 86400000L);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }

    public static int getJieHun() {
        return before(jieHun);
    }

    public static int getLinZhen() {
        return after(linZheng);
    }

    public static int getLianAi() {
        return after(lianAi);
    }

    public static int getShengRi(){
        return before(shengRi);
    }

    public static void main(String[] args) {
        System.out.println(getJieHun());
    }


}
