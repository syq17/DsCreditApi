package client;


import credit.CreditRequest;
import credit.Header;
import credit.LinkfaceRequest;
import credit.TaskRequest;

import java.io.File;
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

    private static final String TASKHOST = "http://127.0.0.1:8082/credit/api/v1/task";

    private static final String LINKFACEHOST = "http://127.0.0.1:8082/credit/api/v1/linkface";

    /**
     * 普通接口调用示例
     *
     * @throws Exception
     */
    private static void apiRequest() throws Exception {
        final String channelNo = "CH1276321320";
        final String interfaceName = "jszstatus";
        long timestamp = new Date().getTime();
        Header header = new Header(APIKEY, channelNo, interfaceName, timestamp);

        CreditRequest creditRequest = new CreditRequest(APIHOST, header);

        Map<String, Object> payload = new HashMap<>();
        payload.put("jszh", "1305xxxxxxxxxxxx12");

        creditRequest.setPayload(payload);

        creditRequest.signByKey(SECRETKEY);

        String result = creditRequest.request();

        System.out.println(result);
    }

    /**
     * 任务接口调用示例
     *
     * @throws Exception
     */
    private static void taskRequest() throws Exception {
        long timestamp = new Date().getTime();
        Header header = new Header(APIKEY, timestamp);

        TaskRequest taskRequest = new TaskRequest(TASKHOST, header);

        Map<String, Object> payload = new HashMap<>();
        payload.put("taskNo", "TA1157774850");

        taskRequest.setPayload(payload);
        taskRequest.signByKey(SECRETKEY);

        String result = taskRequest.request();
        System.out.println(result);
    }


    /**
     * linkface 带文件上传的接口请求示例
     *
     * @throws Exception
     */
    private static void apiLinkfaceRequest() throws Exception {
        long timestamp = new Date().getTime();
        final String interfaceName = "hsVerification";
        final String channelNo = "CH1824755184";
        Header header = new Header(APIKEY, channelNo, interfaceName, timestamp);

        LinkfaceRequest linkfaceRequest = new LinkfaceRequest(LINKFACEHOST, header);

        Map<String, Object> payload = new HashMap<>();
        payload.put("name","王xx");
        payload.put("idCard","33xxxxxxxxxxxxxxxxx15");


        File file = new File("E:/20160830ce1.jpg");

        linkfaceRequest.setPayload(payload);
        linkfaceRequest.setLivingImg(file);

        linkfaceRequest.signByKey(SECRETKEY);

        String result = linkfaceRequest.request();

        System.out.println(result);

    }


    public static void main(String[] args) throws Exception {
//        apiRequest();
//        apiLinkfaceRequest();
//        taskRequest();
    }


}
