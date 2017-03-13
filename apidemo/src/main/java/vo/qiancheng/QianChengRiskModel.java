package vo.qiancheng;

/**
 * 浅橙的风控查询封装vo类
 * Created by syq on 2017/3/3.
 */
public class QianChengRiskModel {


    private String token;


    private String name;


    private String mobile;


    private String id_card;


    private Integer platform = 1;


    private RiskData data;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

//    public String getData() {
//        return data;
//    }
//
//    public void setData(String data) {
//        this.data = data;
//    }


    public RiskData getData() {
        return data;
    }

    public void setData(RiskData data) {
        this.data = data;
    }
}
