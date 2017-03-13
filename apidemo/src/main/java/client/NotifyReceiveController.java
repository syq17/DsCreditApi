//package client;
//
//import credit.ApiSignUtil;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//
///**
// * Created by syq on 2017/3/7.
// */
//@Controller
//public class NotifyReceiveController {
//
//
//    @RequestMapping(value = "/receive", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
//    @ResponseBody
//    public String receive(HttpServletRequest request, @RequestBody String content) throws Exception {
//
//        /*打印出推送的所有信息*/
//        System.out.println("回调测试：" + content);
//        Map<String, String> params = new HashMap<>();
//        if (null != request) {
//            Set<String> paramsKey = request.getParameterMap().keySet();
//            for (String key : paramsKey) {
//                params.put(key, request.getParameter(key));
//            }
//        }
//        System.out.println(params);
//
//
//        String res = params.get("res");
//        String orderNo = params.get("orderNo");
//        String timestamp = params.get("timestamp");
//        String sign = params.get("sign");
//
//        String apiKey = " f6c52ea7-b02a-41c4-8788-2526495b";
//        String secretKey = " 6352098b160723847c81cf983b4b2bc6836f7b101cc4d4982bba02c3e08e3626";
//
//        /*构造签名参数*/
//        Map<String, Object> signMap = new HashMap<>();/*验签map*/
//        signMap.put("res", res);
//        signMap.put("orderNo", orderNo);
//        signMap.put("timestamp", timestamp);
//
//        String mySign = ApiSignUtil.sign(apiKey, secretKey, signMap);
//
//        if (sign.equals(mySign)) {
//            //说明验签通过，可以处理自己的业务
//        }
//        return "SUCCESS";
//    }
//
//
//}
