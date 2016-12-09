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

    private static final String APIKEY = "d518c386-2723-4087-aae5-61687ab4";

    private static final String SECRETKEY = "0b39aa4b6b1a3dddc4fa35db40fb1bb108aa10aefbd3298d632375b2d530b8b7";

    private static String APIHOST = "http://ucapi.ucredit.erongyun.net/credit/api/v1/query";

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
     * 上数 H5 URL 地址获取请求
     *
     * @throws Exception
     */
    private static void shulidataRequest() throws Exception {
        APIHOST = "http://ucdevapi.ucredit.erongyun.net/credit/api/v1.5/query";

//        APIHOST = "http://ucapi.ucredit.erongyun.net/credit/api/v1.5/query"; //正式线地址

        final String channelNo = "CH0673607634";
        final String interfaceName = "buildAuthCollItemListUrl";
        long timestamp = new Date().getTime();
        Header header = new Header(APIKEY, channelNo, interfaceName, timestamp);

        CreditRequest creditRequest = new CreditRequest(APIHOST, header);

        Map<String, Object> payload = new HashMap<>();
//        payload.put("phone", "15858265121");
//        payload.put("name", "沈叶青");
        payload.put("prodCode", "OPERATOR");//ACCUFUND 为公积金，OPERATOR 为运营商，BANK 为银行，CHSI 为学信
//        payload.put("idcard", "331082198711300316");//身份证号
        payload.put("notifyUrl","http://ucdevapi.ucredit.erongyun.net/mytest");//用户回调的接口地址
        creditRequest.setPayload(payload);

        creditRequest.signByKey(SECRETKEY);

        String result = creditRequest.request();

        System.out.println(result);

    }

    /**
     * 上数 结果信息获取接口
     *
     * @throws Exception
     */
    private static void shulidataResultRequest() throws Exception {
        String SSAPIHOST = "http://ucdevapi.ucredit.erongyun.net/notify/slops/ret";

//        String SSAPIHOST = "http://ucapi.ucredit.erongyun.net/notify/slops/ret"; //正式线地址

        long timestamp = new Date().getTime();
        Header header = new Header(APIKEY, timestamp);

        CreditRequest creditRequest = new CreditRequest(SSAPIHOST, header);

        Map<String, Object> payload = new HashMap<>();
        payload.put("orderNo", "201611250003728575");//订单号

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
        payload.put("name", "周XX");
        payload.put("idCard", "41152*******080735");


        File file = new File("E:/20160830ce2.jpg");

        linkfaceRequest.setPayload(payload);
        linkfaceRequest.setLivingImg(file);

        linkfaceRequest.signByKey(SECRETKEY);

        String result = linkfaceRequest.request();

        System.out.println(result);

    }





    public static void main(String[] args) throws Exception {
//        apiRequest();
        shulidataRequest();
//        shulidataResultRequest();


    }


}
