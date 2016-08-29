package credit;

import com.alibaba.fastjson.JSON;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by syq on 2016/8/28.
 */
public class CreditRequest {

    private String host;

    private Header header;

    private Map<String, Object> payload;

    private String sign;

    private HttpClient client = new HttpClient();


    public CreditRequest(String host, Header header) {
        this.host = host;
        this.header = header;
    }

    public CreditRequest(String host, Header header, Map<String, Object> payload) {
        this.host = host;
        this.header = header;
        this.payload = payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }


    public void signByKey(String secretKey) {
        this.sign = ApiSignUtil.sign(header.getApiKey(), header.getChannelNo(), header.getInterfaceName(), header.getTimestamp(), secretKey, this.payload);
    }


    /**
     * httpclient开始请求api
     *
     * @return
     */
    public String request() {
        PostMethod post = new PostMethod(this.host);
        String body = requestBody();
        String responseStr = null;
        InputStream inputStream = null;
        try {
            RequestEntity requestEntity = new StringRequestEntity(body, "application/json", "utf-8");
            post.setRequestEntity(requestEntity);
            client.executeMethod(post);
            inputStream = post.getResponseBodyAsStream();

            byte[] buffer = new byte[1024];
            int readBytes;
            StringBuilder stringBuilder = new StringBuilder();
            while((readBytes = inputStream.read(buffer)) > 0){
                stringBuilder.append(new String(buffer, 0, readBytes));
            }
            responseStr = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return responseStr;
    }


    private String requestBody() {
        /*按照格式需求，组装请求json字符串*/
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("apiKey", this.header.getApiKey());
        paramMap.put("channelNo", this.header.getChannelNo());
        paramMap.put("interfaceName", this.header.getInterfaceName());
        paramMap.put("timestamp", this.header.getTimestamp());
        paramMap.put("sign", this.sign);
        paramMap.put("payload", this.payload);
        return JSON.toJSONString(paramMap);
    }


}
