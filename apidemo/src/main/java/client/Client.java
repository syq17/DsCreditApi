package client;


import credit.CreditRequest;
import credit.Header;
import credit.LinkfaceHsRequest;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by syq on 2016/8/28.
 */
public class Client {

    private static final String APIKEY = "bfde3396-3956-4fab-bc73-9b015026";

    private static final String SECRETKEY = "cab3f18c37712ec6cef1dce6e3ca79bca7b605e3ca42f889ba12ca0ab94a6c1c";


    /**
     * 银行卡四要素接口调用示例
     *
     * @throws Exception
     */
    private static void apiBankCardRequest() throws Exception {
        final String APIHOST = "http://ucdevapi.ucredit.erongyun.net/credit/api/mock/query";
//        String APIHOST = "http://127.0.0.1:8082/credit/api/v1/query";

        final String channelNo = "CH1709907004";
        final String interfaceName = "bankCardFourQuery";

        long timestamp = new Date().getTime();
        Header header = new Header(APIKEY, channelNo, interfaceName, timestamp);

        CreditRequest creditRequest = new CreditRequest(APIHOST, header);

        Map<String, Object> payload = new HashMap<>();
        payload.put("bankNo", "440343324*******3443");//银行卡号
        payload.put("mobile", "1585****121");
        payload.put("name", "老王");//姓名
        payload.put("idType", "01");//固定值
        payload.put("idNo", "33108******16");//身份证号

        creditRequest.setPayload(payload);

        creditRequest.signByKey(SECRETKEY);

        String result = creditRequest.request();

        System.out.println(result);

    }


    /**
     * 华道黑名单接口调用示例
     *
     * @throws Exception
     */
    private static void apiBlackRequest() throws Exception {
        final String APIHOST = "http://127.0.0.1:8082/credit/api/mock/query";
//        String APIHOST = "http://127.0.0.1:8082/credit/api/v1/query";

        final String channelNo = "CH2022086619";
        final String interfaceName = "IS_Be_Overdue_Black";
        long timestamp = new Date().getTime();
        Header header = new Header(APIKEY, channelNo, interfaceName, timestamp);

        CreditRequest creditRequest = new CreditRequest(APIHOST, header);

        Map<String, Object> payload = new HashMap<>();

        payload.put("Phone", "15800000000");

        creditRequest.setPayload(payload);

        creditRequest.signByKey(SECRETKEY);

        String result = creditRequest.request();

        System.out.println(result);

    }


    /**
     * 个人不良信息接口调用示例
     *
     * @throws Exception
     */
    private static void apiPersonBadInfoRequest() throws Exception {
        final String APIHOST = "http://127.0.0.1:8082/credit/api/mock/query";
//        String APIHOST = "http://127.0.0.1:8082/credit/api/v1/query";

        final String channelNo = "CH0055576693";
        final String interfaceName = "WstGetPersonBadInfo";
        long timestamp = new Date().getTime();
        Header header = new Header(APIKEY, channelNo, interfaceName, timestamp);

        CreditRequest creditRequest = new CreditRequest(APIHOST, header);

        Map<String, Object> payload = new HashMap<>();

        payload.put("name", "老王");
        payload.put("idNumber", "320681********1234");

        creditRequest.setPayload(payload);

        creditRequest.signByKey(SECRETKEY);

        String result = creditRequest.request();

        System.out.println(result);

    }


    private static void apiblackComboRequest() throws Exception {
        final String APIHOST = "http://10.10.2.16:8082/credit/api/combo/v1/query";
//        final String APIHOST = "http://127.0.0.1:8082/credit/api/v1/query";

        long timestamp = new Date().getTime();
        Header header = new Header(APIKEY, timestamp);

        CreditRequest creditRequest = new CreditRequest(APIHOST, header);

        Map<String, Object> payload = new HashMap<>();

        payload.put("comboNo", "CO2090532314");
        payload.put("queryName", "老王");
        payload.put("mobile", "18767809988");
        payload.put("idCard", "12345");

        creditRequest.setPayload(payload);

        creditRequest.signByKey(SECRETKEY);

        String result = creditRequest.request();

        System.out.println(result);

    }


    /**
     * 上数 H5 URL 地址获取请求
     *
     * @throws Exception
     */
    private static void shulidataRequest() throws Exception {
        final String APIHOST = "http://ucdevapi.ucredit.erongyun.net/credit/api/v1.5/query";
//        APIHOST = "http://127.0.0.1:8082/credit/api/v1.5/query";

        final String channelNo = "CH0673607634";
        final String interfaceName = "buildAuthCollItemListUrl";
        long timestamp = new Date().getTime();
        Header header = new Header(APIKEY, channelNo, interfaceName, timestamp);

        CreditRequest creditRequest = new CreditRequest(APIHOST, header);

        Map<String, Object> payload = new HashMap<>();
        payload.put("prodCode", "OPERATOR");//OPERATOR 为运营商
//        payload.put("notifyUrl", "自己的回调地址");//用户回调的接口地址，当获取通话记录的时候，会调用该接口，将数据推送至你的服务器
        creditRequest.setPayload(payload);

        creditRequest.signByKey(SECRETKEY);

        String result = creditRequest.request();

        System.out.println(result);

    }


    /***
     * linkface 人像对比
     * @throws Exception
     */
    private static void apiLinkfaceHsRequest() throws Exception {
        String LINKFACEHOST = "http://ucdevapi.ucredit.erongyun.net/linkface/hsVerification";
//        String LINKFACEHOST = "http://127.0.0.1:8082/linkface/hsVerification";

        long timestamp = new Date().getTime();
        Header header = new Header(APIKEY, timestamp);

        LinkfaceHsRequest linkfaceRequest = new LinkfaceHsRequest(LINKFACEHOST, header);

        File livingImgfile = new File("E:/20160824ce5.jpg");
        File ocrImgfile = new File("E:/20160825ce1.jpg");

        linkfaceRequest.setLivingImg(livingImgfile);
        linkfaceRequest.setOcrImg(ocrImgfile);
        linkfaceRequest.setName("老王");
        linkfaceRequest.setIdCard("431381198109106573");

        linkfaceRequest.signByKey(SECRETKEY);

        String result = linkfaceRequest.request();

        System.out.println(result);
    }


    public static void main(String[] args) throws Exception {
//        apiBankCardRequest();
//        shulidataRequest();
//
//        apiLinkfaceHsRequest();
//        apiBlackRequest();
//        apiPersonBadInfoRequest();
//
//        apiblackComboRequest();

    }


}
