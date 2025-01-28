package ydzhao.weixin.tuisong.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName CaiHongPi
 * @Description TODO
 * @Author ydzhao
 * @Date 2022/8/2 17:26
 */
public class CaiHongPi {
    private static String key = "f36f224f24b1a12400c37410c4bd7a2e";
    private static String url = "http://api.tianapi.com/caihongpi/index?key=";
    private static List<String> jinJuList = new ArrayList<>();
    private static String name = "郑鑫";
    private static final String DEFAULT_JINJU = "尚未缴械就是美好生活";
    public static String getCaiHongPi() {
        //默认彩虹屁
        String str = "阳光落在屋里，爱你藏在心里";
        try {
            String apiResponse = HttpUtil.getUrl(url + key).replace("XXX", name);
            System.out.println("API Response: " + apiResponse);

            JSONObject jsonObject = JSONObject.parseObject(HttpUtil.getUrl(url+key).replace("XXX", name));
            if(jsonObject.getIntValue("code") == 200){
                List<Object> newslist = jsonObject.getJSONArray("newslist");
                for(Object obj : newslist){
                    String content = ((JSONObject) obj).getString("content");
                    if(content != null && content.length() <= 19){
                        return content;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            str = "阳光落在屋里，爱你藏在心里";  // 确保返回默认值
        }
        return str;
    }

    /**
     * 加载金句列表
     */
    static {
        InputStream inputStream = CaiHongPi.class.getClassLoader().getResourceAsStream("jinju.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!StringUtils.isEmpty(line) && line.length() <= 19) { // 仅加载长度在 25 字以内的金句
                    jinJuList.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 如果加载后列表为空，添加默认值
        if (jinJuList.isEmpty()) {
            jinJuList.add(DEFAULT_JINJU);
        }
    }

    /**
     * 获取随机金句
     */
    public static String getJinJu() {
        if (jinJuList.isEmpty()) {
            return DEFAULT_JINJU; // 确保返回默认值
        }
        Random random = new Random();
        String jinju;
        do{
            jinju = jinJuList.get(random.nextInt(jinJuList.size()));
        }while (jinju.length() > 19);
        return jinju;
    }

    public static void main(String[] args) {
        System.out.println(getJinJu());
    }
}
