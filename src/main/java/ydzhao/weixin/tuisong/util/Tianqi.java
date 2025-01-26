package ydzhao.weixin.tuisong.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Tianqi {
    // 高德 API 密钥
    private static String apiKey = "8f2aca1aabf33451f081292ff53d7eb3";  // 替换为你的高德 API 密钥
    // 高德的城市编码（adcode）
    private static String cityId = "370785";  // 替换为目标城市的 adcode

    public static JSONObject getNanjiTianqi() {
        String result = null;
        JSONObject today = new JSONObject();
        try {
            // 构建高德天气 API 请求 URL
            String url = "https://restapi.amap.com/v3/weather/weatherInfo?city=" + cityId + "&key=" + apiKey + "&extensions=base";
            result = HttpUtil.getUrl(url);  // 使用 HttpUtil 发送 GET 请求
            System.out.println("API Response: " + result);  // 输出 API 响应

            // 解析返回的 JSON 数据
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject != null && "1".equals(jsonObject.getString("status"))) {
                // 获取 "lives" 字段，其中包含实时天气信息
                JSONArray lives = jsonObject.getJSONArray("lives");
                if (lives != null && !lives.isEmpty()) {  // 确保 lives 不为空
                    JSONObject weatherData = lives.getJSONObject(0);

                    // 将需要的数据放入到 today JSONObject 中
                    today.put("date", weatherData.getString("reporttime"));
                    today.put("weather", weatherData.getString("weather"));
                    today.put("temperature", weatherData.getString("temperature"));
                    today.put("winddirection", weatherData.getString("winddirection"));
                    today.put("windpower", weatherData.getString("windpower"));
                    today.put("humidity", weatherData.getString("humidity"));
                } else {
                    System.out.println("No data found in 'lives'");
                }
            } else {
                System.out.println("Weather API error: " + jsonObject.getString("info"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while fetching weather data: " + e.getMessage());
        }
        return today;
    }

    public static void main(String[] args) {
        JSONObject weatherData = getNanjiTianqi();
        System.out.println("Weather Data: " + weatherData.toString());
    }
}
