package vo.qiancheng;

import java.util.List;

/**
 * 包含所需提交的风控参数
 * Created by syq on 2017/3/3.
 */
public class RiskData {


    /**
     * 借款订单信息
     */
    private OrderDetail order_detail;


    /**
     * 借款人基本信息
     */
    private BasicInfo basic_info;


    /**
     * 身份证信息
     */
    private List<UserProofMateria> user_proof_materia;


    /**
     * 银行卡信息
     */
    private List<CardInfo> card_info;


    /**
     * 通讯录
     */
    private List<UserMobileContacts> user_mobile_contacts;


    /**
     * 运营商数据
     */
    private TelecomData telecom_data;


    public OrderDetail getOrder_detail() {
        return order_detail;
    }

    public void setOrder_detail(OrderDetail order_detail) {
        this.order_detail = order_detail;
    }

    public BasicInfo getBasic_info() {
        return basic_info;
    }

    public void setBasic_info(BasicInfo basic_info) {
        this.basic_info = basic_info;
    }

    public List<UserProofMateria> getUser_proof_materia() {
        return user_proof_materia;
    }

    public void setUser_proof_materia(List<UserProofMateria> user_proof_materia) {
        this.user_proof_materia = user_proof_materia;
    }

    public List<CardInfo> getCard_info() {
        return card_info;
    }

    public void setCard_info(List<CardInfo> card_info) {
        this.card_info = card_info;
    }

    public List<UserMobileContacts> getUser_mobile_contacts() {
        return user_mobile_contacts;
    }

    public void setUser_mobile_contacts(List<UserMobileContacts> user_mobile_contacts) {
        this.user_mobile_contacts = user_mobile_contacts;
    }

    public TelecomData getTelecom_data() {
        return telecom_data;
    }

    public void setTelecom_data(TelecomData telecom_data) {
        this.telecom_data = telecom_data;
    }


}
