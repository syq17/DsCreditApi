package credit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

/**
 * Created by syq on 2016/8/16.
 */
public class ApiSignUtil {


    /**
     * 用于建立十六进制字符的输出的小写字符数组
     */
    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 拼装调用api请求所需要的JWT格式的参数
     *
     * @param apiKey        头信息中的apiKey
     * @param channelNo     头信息中的channelNo
     * @param interfaceName 头信息中的interfaceName
     * @param secretKey     用户的加密私钥
     * @param payload       用户查询的具体参数
     * @return
     */
    public static String sign(String apiKey, String channelNo, String interfaceName, long timestamp, String secretKey, Map<String, Object> payload) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("apiKey", apiKey);
        if(channelNo != null){
            paramMap.put("channelNo", channelNo);
        }
        if(interfaceName != null){
            paramMap.put("interfaceName", interfaceName);
        }
        paramMap.put("timestamp", timestamp);
        Set<String> payloadKeys = payload.keySet();
        for (String payloadKey : payloadKeys) {
            paramMap.put(payloadKey, payload.get(payloadKey));
        }

        JSONObject signJson = new JSONObject(true);
        List<String> sortKeyList = sortMapKeyList(paramMap);
        for (String key : sortKeyList) {
            signJson.put(key, paramMap.get(key));
        }
        /*对sign签名进行验证*/
        String signature = null;
        try {
            signature = ApiSignUtil.encodeHmacSHA256(JSON.toJSONString(signJson), secretKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return signature;
    }


    public static String encodeHmacSHA256(String paramForSign, String secretKey) throws Exception {
        String signature = encodeHmacSHA256(paramForSign.getBytes(), secretKey.getBytes());
        return signature;
    }

    private static String encodeHmacSHA256(byte[] data, byte[] key) throws Exception {
        // 还原密钥
        SecretKey secretKey = new SecretKeySpec(key, "HmacSHA256");
        // 实例化Mac
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        //初始化mac
        mac.init(secretKey);
        //执行消息摘要
        byte[] digest = mac.doFinal(data);
        return new String(encodeHex(digest, DIGITS_LOWER));
    }

    private static char[] encodeHex(byte[] data, char[] toDigits) {
        int l = data.length;
        char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0x0F & data[i]];
        }
        return out;
    }

    public static List<String> sortMapKeyList(Map<String, Object> map) {
        Set<String> listKeys = map.keySet();
        Iterator<String> it = listKeys.iterator();
        List<String> list = new ArrayList<String>();
        while (it.hasNext()) {
            list.add(it.next());
        }
        Collections.sort(list);
        return list;
    }
}
