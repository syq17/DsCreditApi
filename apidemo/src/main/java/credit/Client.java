package credit;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by syq on 2016/8/28.
 */
public class Client {

    private static final String APIKEY = "161c5ce6-7385-48db-bf68-bc3a8e83";

    private static final String SECRETKEY = "536cee574c5d05df15b9df7b8236d53d4db96841a504f13ca462eb761152de2d";

    private static final String APIHOST = "http://127.0.0.1:8082/credit/api/v1/query";

    /**
     * 普通接口请求示例
     *
     * @throws Exception
     */
    private static void apiRequest() throws Exception {
        final String channelNo = "CH1709907004";
        final String interfaceName = "mailboxAttribution";
        long timestamp = new Date().getTime();
        Header header = new Header(APIKEY, channelNo, interfaceName, timestamp);

        CreditRequest creditRequest = new CreditRequest(APIHOST, header);

        Map<String, Object> payload = new HashMap<>();
        payload.put("email", "148371250@qq.com");

        creditRequest.setPayload(payload);

        creditRequest.signByKey(SECRETKEY);

        String result = creditRequest.request();

        System.out.println(result);
    }


    private static void taskRequest() throws Exception{

    }





    /**
     * linkface 带文件上传的接口请求示例
     *
     * @throws Exception
     */
    private static void apiLinkfaceRequest() throws Exception {

    }


    public static void main(String[] args) throws Exception{
        apiRequest();
        apiLinkfaceRequest();
        taskRequest();
    }


}
