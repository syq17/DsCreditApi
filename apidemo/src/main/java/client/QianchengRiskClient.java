package client;

import credit.Header;
import credit.QianchengRiskRequest;
import vo.qiancheng.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 请求浅橙风控接口demo
 * Created by syq on 2017/3/10.
 */
public class QianchengRiskClient {

    private static final String APIKEY = "2b0c383a-debb-40bf-afa6-3f045d9f";

    private static final String SECRETKEY = "abedbdc17cd24c598bc758a12e3fcdd2d94147d1510eb6192cb28ee518ec3e79";


    /**
     * 提交风控参数进行审核
     *
     * @throws Exception
     */
    private static void riskCheck() throws Exception {

        String url = "http://ucdevapi.ucredit.erongyun.net/qiancheng/risk/check";

        long timestamp = new Date().getTime();
        Header header = new Header(APIKEY, timestamp);


         /*下面开始构造风控参数*/
        RiskData riskData = new RiskData();

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setAmount(100000);
        orderDetail.setLoan_term(30);
        orderDetail.setOrder_time("2017-03-10 14:47:00");
        orderDetail.setOrder_id((long) ((Math.random() * 9 + 1) * 1000000000));//你自己的订单号
        riskData.setOrder_detail(orderDetail);

        BasicInfo basicInfo = new BasicInfo();
        List<UserContact> ucList = new ArrayList<>();
        UserContact userContact1 = new UserContact();
        userContact1.setName("杨振坤");
        userContact1.setMobile("18285109303");
        userContact1.setRelation(3);
        ucList.add(userContact1);
        UserContact userContact2 = new UserContact();
        userContact2.setName("邓昭荣");
        userContact2.setMobile("18817240593");
        userContact2.setRelation(3);
        ucList.add(userContact2);
        basicInfo.setUser_contact(ucList);
        basicInfo.setCompany_name("杭州融都科技股份有限公司");
        basicInfo.setCompany_address("杭州市拱墅区");
        basicInfo.setCompany_phone("0571-85132616");
        basicInfo.setCur_address("杭州市拱墅区");
        basicInfo.setPerm_address("杭州市拱墅区");
        riskData.setBasic_info(basicInfo);


        List<UserProofMateria> user_proof_materia = new ArrayList<>();
        UserProofMateria upm1 = new UserProofMateria();
        upm1.setUrl("http://ol6xtpta7.bkt.clouddn.com/20160830ce1.jpg");
        upm1.setType(0);
        UserProofMateria upm2 = new UserProofMateria();
        upm2.setUrl("http://ol6xtpta7.bkt.clouddn.com/172051174553077341.jpg");
        upm2.setType(1);
        UserProofMateria upm3 = new UserProofMateria();
        upm3.setUrl("http://ol6xtpta7.bkt.clouddn.com/773583060332943738.jpg");
        upm3.setType(2);
        user_proof_materia.add(upm1);
        user_proof_materia.add(upm2);
        user_proof_materia.add(upm3);
        riskData.setUser_proof_materia(user_proof_materia);

        List<UserMobileContacts> user_mobile_contacts = new ArrayList<>();
        UserMobileContacts umc1 = new UserMobileContacts();
        umc1.setMobile("18285109303");
        umc1.setName("杨振坤");
        user_mobile_contacts.add(umc1);
        riskData.setUser_mobile_contacts(user_mobile_contacts);

        QianchengRiskRequest qianchengRiskRequest = new QianchengRiskRequest(url, header);
        qianchengRiskRequest.setName("余凌峰");
        qianchengRiskRequest.setMobile("15858265133");
        qianchengRiskRequest.setIdCard("330127199212275915");
        qianchengRiskRequest.setTelecomOrderNo("201703070307731807");
        qianchengRiskRequest.setData(riskData);
        qianchengRiskRequest.signByKey(SECRETKEY);


        String result = qianchengRiskRequest.request();

        System.out.println(result);

    }


    public static void main(String[] args) throws Exception {
        riskCheck();
    }


}
