package vo.qiancheng;

import java.util.List;

/**
 * Created by syq on 2017/3/3.
 */
public class BasicInfo {

    /**
     * 常住地址
     */
    private String cur_address;


    /**
     * 户籍地址
     */
    private String perm_address;


    /**
     * 公司名
     */
    private String company_name;


    /**
     * 公司电话
     */
    private String company_phone;


    /**
     * 公司地址
     */
    private String company_address;


    /**
     * 紧急联系人,最好填2个以上联系人 (必填)
     */
    private List<UserContact> user_contact;



    public String getCur_address() {
        return cur_address;
    }

    public void setCur_address(String cur_address) {
        this.cur_address = cur_address;
    }

    public String getPerm_address() {
        return perm_address;
    }

    public void setPerm_address(String perm_address) {
        this.perm_address = perm_address;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_phone() {
        return company_phone;
    }

    public void setCompany_phone(String company_phone) {
        this.company_phone = company_phone;
    }

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }

    public List<UserContact> getUser_contact() {
        return user_contact;
    }

    public void setUser_contact(List<UserContact> user_contact) {
        this.user_contact = user_contact;
    }
}
