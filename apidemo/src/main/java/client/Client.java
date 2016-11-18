package client;


import credit.CreditRequest;
import credit.Header;
import credit.LinkfaceRequest;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by syq on 2016/8/28.
 */
public class Client {

    private static final String APIKEY = "97706a19-bbe9-4891-b92c-c18f5ed7";

    private static final String SECRETKEY = "bf148b8dea1d2110627098f5ab52dc871487b938cc77ced81994ce7b9b2b071c";

    private static final String APIHOST = "http://ucapi.ucredit.erongyun.net/credit/api/v1/query";

    private static final String GXBAPIHOST = "http://ucapi.ucredit.erongyun.net/notify/gxb/ret";

    private static final String LINKFACEHOST = "http://ucapi.ucredit.erongyun.net/credit/api/v1/linkface";

    /**
     * 普通接口调用示例
     *
     * @throws Exception
     */
    private static void apiRequest() throws Exception {
        final String channelNo = "CH1709907004";
        final String interfaceName = "bankCardFourQuery";
        long timestamp = new Date().getTime();
        Header header = new Header(APIKEY, channelNo, interfaceName, timestamp);

        CreditRequest creditRequest = new CreditRequest(APIHOST, header);

        Map<String, Object> payload = new HashMap<>();
        payload.put("bankNo", "6212***********4440");//银行卡号
        payload.put("mobile", "15858265121");
        payload.put("name", "沈叶青");
        payload.put("idType", "01");//01为固定值
        payload.put("idNo", "3310********00316");//身份证号

        creditRequest.setPayload(payload);

        creditRequest.signByKey(SECRETKEY);

        String result = creditRequest.request();

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
        payload.put("name", "周学成");
        payload.put("idCard", "411528198811080735");


        File file = new File("E:/20160830ce2.jpg");

        linkfaceRequest.setPayload(payload);
        linkfaceRequest.setLivingImg(file);

        linkfaceRequest.signByKey(SECRETKEY);

        String result = linkfaceRequest.request();

        System.out.println(result);

    }


    /**
     * 公信宝结果信息获取接口
     *
     * @throws Exception
     */
    private static void gxbResultRequest() throws Exception {
        long timestamp = new Date().getTime();
        Header header = new Header(APIKEY, timestamp);

        CreditRequest creditRequest = new CreditRequest(GXBAPIHOST, header);

        Map<String, Object> payload = new HashMap<>();
        payload.put("token", "0000000000000LwoMqBlbRKnsgvMJZCW");//公信宝token

        creditRequest.setPayload(payload);

        creditRequest.signByKey(SECRETKEY);

        String result = creditRequest.request();

        System.out.println(result);
    }


    public static void main(String[] args) throws Exception {
        apiRequest();
//        apiLinkfaceRequest();
        gxbResultRequest();
    }


}
