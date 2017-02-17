package client;


import credit.CreditRequest;
import credit.Header;
import credit.LinkfaceOCRRequest;
import credit.LinkfaceHsRequest;

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
     * 银行卡四要素接口调用示例
     *
     * @throws Exception
     */
    private static void apiBankCardRequest() throws Exception {
//        String APIHOST = "http://127.0.0.1:8082/credit/api/mock/query";
        String APIHOST = "http://127.0.0.1:8082/credit/api/v1/query";
//        String APIHOST = "http://ucdevapi.ucredit.erongyun.net/credit/api/v1/query";
        String APIKEY = "2b0c383a-debb-40bf-afa6-3f045d9f";
        String SECRETKEY = "abedbdc17cd24c598bc758a12e3fcdd2d94147d1510eb6192cb28ee518ec3e79";

//        final String channelNo = "CH1709907004";
//        final String interfaceName = "bankCardFourQuery";
        final String channelNo = "CH0844282371";
        final String interfaceName = "txbankCardFourCheckFourQuery";
        long timestamp = new Date().getTime();
        Header header = new Header(APIKEY, channelNo, interfaceName, timestamp);

        CreditRequest creditRequest = new CreditRequest(APIHOST, header);

        Map<String, Object> payload = new HashMap<>();
//        payload.put("bankNo", "440343324*******3443");//银行卡号
//        payload.put("mobile", "1585****121");
//        payload.put("name", "老王");//姓名
//        payload.put("idType", "01");//固定值
//        payload.put("idNo", "33108******16");//身份证号

//        payload.put("bankNo", "4403433244444443443");//银行卡号
//        payload.put("mobile", "1585353121");
//        payload.put("name", "老王");//姓名
//        payload.put("idType", "01");//固定值
//        payload.put("idNo", "3310834324255216");//


        payload.put("accountNO", "6228481698729890079");//银行卡号
        payload.put("bankPreMobile", "15787987621");
        payload.put("name", "老王");//姓名
        payload.put("idCard", "431381198109106573");//身份证号

        creditRequest.setPayload(payload);

        creditRequest.signByKey(SECRETKEY);

        String result = creditRequest.request();

        System.out.println(result);

    }



    private static void apiBlackRequest() throws Exception {
        final String APIHOST = "http://127.0.0.1:8082/credit/api/mock/query";
        final String APIKEY = "47c5101f-c94e-4a8c-a255-56b3c807";
        final String SECRETKEY = "e5c880a495c194688fcd50c18a20a798c493210e70fa0ad75d61422735c57597";

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



    private static void apiPersonBadInfoRequest() throws Exception {
        final String APIHOST = "http://127.0.0.1:8082/credit/api/mock/query";
        final String APIKEY = "4fe6b420-c480-45d8-802b-26b3f5ca";
        final String SECRETKEY = "79561b81488cdc249a148232d0855a18014cc14abf16755a65eae5d1ed9f5a0d";

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
        final String APIKEY = "a2a22960-d7f5-4a01-acc9-f50d8a7c";
        final String SECRETKEY = "42f1f31dbd63c249cae93e0556a422556207563f4b3b6f9535d489b6b287057c";

//        final String channelNo = "CH2022086619";
//        final String interfaceName = "IS_Be_Overdue_Black";
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
//        APIHOST = "http://ucdevapi.ucredit.erongyun.net/credit/api/v1.5/query";

        APIHOST = "http://ucapi.ucredit.erongyun.net/credit/api/v1.5/query"; //正式线地址

        String APIKEY = "d518c386-2723-4087-aae5-61687ab4";
        String SECRETKEY = "0b39aa4b6b1a3dddc4fa35db40fb1bb108aa10aefbd3298d632375b2d530b8b7";

//        String APIKEY = "2b0c383a-debb-40bf-afa6-3f045d9f";
//        String SECRETKEY = "abedbdc17cd24c598bc758a12e3fcdd2d94147d1510eb6192cb28ee518ec3e79";


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
        payload.put("notifyUrl", "http://ucapi.ucredit.erongyun.net/mytest");//用户回调的接口地址
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
    private static void apiLinkfaceOcrRequest() throws Exception {
        String LINKFACEHOST = "http://127.0.0.1:8082/linkface/ocr";
        String APIKEY = "2b0c383a-debb-40bf-afa6-3f045d9f";
        String SECRETKEY = "abedbdc17cd24c598bc758a12e3fcdd2d94147d1510eb6192cb28ee518ec3e79";
        File file = new File("E:/456716030794523820.jpg");

        long timestamp = new Date().getTime();

        Header header = new Header(APIKEY, timestamp);

        LinkfaceOCRRequest request = new LinkfaceOCRRequest(LINKFACEHOST, header);
        request.setIdCardImg(file);
        request.signByKey(SECRETKEY);

        String result = request.request();

        System.out.println(result);



    }


    private static void apiLinkfaceHsRequest() throws Exception {

        String LINKFACEHOST = "http://ucdevapi.ucredit.erongyun.net/linkface/hsVerification";
//        String LINKFACEHOST = "http://127.0.0.1:8082/linkface/hsVerification";
        String APIKEY = "4fe6b420-c480-45d8-802b-26b3f5ca";
        String SECRETKEY = "79561b81488cdc249a148232d0855a18014cc14abf16755a65eae5d1ed9f5a0d";

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
//        shulidataResultRequest();

        apiLinkfaceHsRequest();
//        apiBlackRequest();
//        apiPersonBadInfoRequest();

//        apiblackComboRequest();

    }


}
