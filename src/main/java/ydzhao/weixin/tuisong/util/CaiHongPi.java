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
 * @Description 彩虹屁 & 金句生成器
 * @Author ydzhao
 * @Date 2022/8/2 17:26
 */
public class CaiHongPi {
    private static final String key = "f36f224f24b1a12400c37410c4bd7a2e";
    private static final String url = "http://api.tianapi.com/caihongpi/index?key=";
    private static final List<String> jinJuList = new ArrayList<>();
    private static final String name = "郑鑫";
    private static final String DEFAULT_CAIHONGPI = "阳光落在屋里，爱你藏在心里";
    private static final String DEFAULT_JINJU = "尚未缴械就是美好生活";
    private static final int MAX_LENGTH = 20;

    /**
     * 获取彩虹屁（优先 API，否则回退到 jinJuList）
     */
    public static String getCaiHongPi() {
        try {
            String apiResponse = HttpUtil.getUrl(url + key).replace("XXX", name);
            System.out.println("API Response: " + apiResponse);

            JSONObject jsonObject = JSONObject.parseObject(apiResponse);
            if (jsonObject.getIntValue("code") == 200) {
                JSONArray newslist = jsonObject.getJSONArray("newslist");
                List<String> validResults = new ArrayList<>();

                for (Object obj : newslist) {
                    String content = ((JSONObject) obj).getString("content");
                    if (content != null && content.length() <= MAX_LENGTH) {
                        validResults.add(content);
                    }
                }

                // 随机选择符合长度的内容
                if (!validResults.isEmpty()) {
                    return validResults.get(new Random().nextInt(validResults.size()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 如果 API 无法提供合适内容，从本地 `jinJuList` 里选
        return getJinJu();
    }

    /**
     * 加载金句列表（保证不会为空）
     */
    static {
        InputStream inputStream = CaiHongPi.class.getClassLoader().getResourceAsStream("jinju.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim().replaceAll("\\s+", ""); // 去除多余空白
                if (!StringUtils.isEmpty(line) && line.length() <= MAX_LENGTH) {
                    jinJuList.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 兜底策略：如果 `jinJuList` 为空，则加入默认金句
        if (jinJuList.isEmpty()) {
            jinJuList.add(DEFAULT_JINJU);
        }
    }

    /**
     * 获取随机金句（确保长度符合）
     */
    public static String getJinJu() {
        if (jinJuList.isEmpty()) {
            return DEFAULT_JINJU;
        }

        Random random = new Random();
        String jinju;
        do {
            jinju = jinJuList.get(random.nextInt(jinJuList.size()));
        } while (jinju.length() > MAX_LENGTH); // 确保不会超长

        return jinju;
    }

    public static void main(String[] args) {
        System.out.println("彩虹屁: " + getCaiHongPi());
        System.out.println("金句: " + getJinJu());
    }
}
