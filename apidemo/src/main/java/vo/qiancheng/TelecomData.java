package vo.qiancheng;

import java.util.List;

/**
 * 前程 - 运营商数据
 *
 * @author 王帅
 * @since 2017年3月3日下午3:50:34
 * CopyRight (c) 2016 RONGDU
 */
public class TelecomData {

    /**
     * 实名认证状态,0 未实名,1 已实名
     */
    private Integer real_name_status;

    /**
     * 月账单
     */
    private List<BillList> bill_list;

    /**
     * 通话记录
     */
    private List<ContactList> contact_list;

    public Integer getReal_name_status() {
        return real_name_status;
    }

    public void setReal_name_status(Integer real_name_status) {
        this.real_name_status = real_name_status;
    }

    public List<BillList> getBill_list() {
        return bill_list;
    }

    public void setBill_list(List<BillList> bill_list) {
        this.bill_list = bill_list;
    }

    public List<ContactList> getContact_list() {
        return contact_list;
    }

    public void setContact_list(List<ContactList> contact_list) {
        this.contact_list = contact_list;
    }


}
