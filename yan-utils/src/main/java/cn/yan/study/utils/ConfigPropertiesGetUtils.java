package cn.yan.study.utils;
import org.apache.commons.collections.map.LinkedMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created
 * User  wankunYan
 * Date  2018/6/24
 * Time  11:48
 */
public class ConfigPropertiesGetUtils {

    /**
     * 配置项
     */
    private static Map<String, Object> configMap = new LinkedMap();
    /**
     * 内部变量
     */
    private static Map<String, Object> paramMap = new HashMap<>();

    private static List<String> configFileList;

    public ConfigPropertiesGetUtils setConfigFileList(String... configFiles) {
        ConfigPropertiesGetUtils.configFileList = Arrays.asList(configFiles);
        return this;
    }

    public static void main(String[] args) throws IOException {
        new ConfigPropertiesGetUtils()
                .setConfigFileList("F:\\worl_yan\\sino_new\\sino-message\\sino-message-impl\\src\\main\\java\\cn\\sino\\message\\config\\Config.java")
                .getConfigProperties();
    }

    /**
     * 获取配置的方法
     */
    public void getConfigProperties() throws IOException  {

        for (String fileName : configFileList) {
            FileReader fr=new FileReader(fileName);
            BufferedReader br=new BufferedReader(fr);
            String line="";
            while ((line=br.readLine())!=null) {
                if (line.contains("FundamentalConfigProvider") && line.contains("return")) {
                    getReturnLine(line);
                }
                if (line.contains("private static final")) {
                    getDefineLine(line);
                }
            }
            br.close();
            fr.close();
        }
        show();
    }

    /**
     * 从函数的配置行中获取配置信息
     * eg : return FundamentalConfigProvider.getString("phone.recharge.data.order.url","http://122.224.88.51:8280/charge.json");
     */
    private static void getReturnLine(String returnLine) {
        Integer indexOne = returnLine.indexOf("(") + 1;
        Integer indexEnd = returnLine.lastIndexOf(")");
        String subLine = returnLine.substring(indexOne, indexEnd);
        String key;
        Object value;

        // subLine eg :   "phone.recharge.data.order.url","http://122.224.88.51:8280/charge.json"
        if (subLine.contains(",")) { // 这个是包含默认值的
            String keyString = subLine.substring(0, subLine.indexOf(","));
            String valueString = subLine.substring(subLine.indexOf(",") + 1);

            key = getKey(keyString);
            value = getValue(returnLine, valueString);

        // subLine eg : "phone.recharge.data.order.url"
        } else { // 这个是不包行默认值的
            key = getKey(subLine);
            value = "default";
        }

        myPut(key, value, configMap);

    }

    /**
     * 从返回行中获取默认值
     * @param returnLine 返回配置的行
     * @param valueString 默认值的字符串
     */
    private static Object getValue(String returnLine, String valueString) {
        valueString = valueString.replace(" ", "");
        if (returnLine.contains("Boolean")) {
            return Boolean.valueOf(valueString);
        }
        if (returnLine.contains("Int")) {
            return new Integer(valueString);
        }
        if (returnLine.contains("String")) {
            return valueString.replace("\"", "");
        }
        if (returnLine.contains("Long")) {
            valueString = valueString.replace("L", "").replace("l", "");
            return Long.parseLong(valueString);
        }
        return null;
    }

    /**
     * 通过keyString 获取配置的名称
     * @param keyString
     * @return
     */
    private static String getKey(String keyString) {
        String key;
        if (keyString.contains("\"")) {
            key = keyString.replace("\"", "");
        } else {
            if (keyString.contains("+")) {
                String paramKey = keyString.substring(0, keyString.indexOf("+")).replace(" ", "");
                String endString = keyString.substring(keyString.indexOf("+"));
                key = paramMap.get(paramKey).toString() + endString;
                return key;
            }
            key = paramMap.get(keyString).toString();
        }
        return key;
    }

    /**
     * 从内部定义获取配置的内部变量
     * @param content
     * eg : private static final String MAIL_SMTP_HOST_KEY = "mail.smtp.host";
     */
    private static void getDefineLine(String content) {

        int index1 = content.indexOf("\"") + 1;
        int index2 = content.indexOf("\"", index1 + 1);

        String key = content.substring(index1, index2);

        String[] params = content.split(" ");
        int keyIndex = getKeyIndex(params);
        if (keyIndex == -1) {
            System.out.println(content);
            throw new RuntimeException("未处理情况请处理");
        }
        String paramKey = params[keyIndex];
        if (ObjectUtils.isEmpty(paramKey)) {
            System.out.println(content);
            throw new RuntimeException("未处理情况请处理");
        }

        myPut(paramKey, key, paramMap);

    }

    /**
     * 从数组中找到参数名称的下标
     */
    private static Integer getKeyIndex(String[] params) {
        if (ObjectUtils.isEmpty(params)) {
            return -1;
        }
        for (Integer index = 0; index < params.length; index++ ) {
            if ("=".equalsIgnoreCase(params[index])) {
                if (index > 1) {
                    return index - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 自己的put方法
     */
    private static void myPut(String key, Object value, Map<String, Object> maps) {
        Object existValue = maps.get(key);
        if (ObjectUtils.isEmpty(existValue)) {
            maps.put(key, value);
            return;
        }

        if (existValue.toString().equalsIgnoreCase("default")) {
            maps.put(key, value);
            return;
        } else {
            if (existValue.equals(value)) {
                return;
            }
            key = key + "_repeat";
            maps.put(key, value);
        }

    }

    /**
     * 输出配置信息
     */
    private static void show() {
        for (Map.Entry<String, Object> entry : configMap.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }
}
