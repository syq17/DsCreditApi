package credit;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by syq on 2016/8/30.
 */
public abstract class DsCreditRequest implements RequestEntityHandler{

    private String host;

    protected Header header;

    protected Map<String, Object> payload;

    protected String sign;

    private HttpClient client = new HttpClient();


    public DsCreditRequest(String host, Header header) {
        this.host = host;
        this.header = header;
    }


    public DsCreditRequest(String host, Header header, Map<String, Object> payload) {
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
        post.getParams();
//        String body = requestBody();
        String responseStr = null;
        InputStream inputStream = null;
        try {
//            RequestEntity requestEntity = new StringRequestEntity(body, "application/json", "utf-8");
            RequestEntity requestEntity = handle();
            post.setRequestEntity(requestEntity);
            client.executeMethod(post);
            inputStream = post.getResponseBodyAsStream();

            byte[] buffer = new byte[1024];
            int readBytes;
            StringBuilder stringBuilder = new StringBuilder();
            while ((readBytes = inputStream.read(buffer)) > 0) {
                stringBuilder.append(new String(buffer, 0, readBytes));
            }
            responseStr = stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return responseStr;
    }


}
