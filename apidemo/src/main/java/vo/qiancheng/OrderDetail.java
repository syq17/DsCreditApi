package vo.qiancheng;

/**
 * Created by syq on 2017/3/3.
 */
public class OrderDetail {


    /**
     * 自身平台的订单号 (必填)
     */
    private Long order_id;


    /**
     * 借款金额，精确到分，如：200 表示 2 元钱 (必填)
     */
    private Integer amount;


    /**
     * 借款期限(天) (必填)
     */
    private Integer loan_term;


    /**
     * 借款时间(yyyy-mm-dd hh:ii:ss)  (必填)
     */
    private String order_time;


    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getLoan_term() {
        return loan_term;
    }

    public void setLoan_term(Integer loan_term) {
        this.loan_term = loan_term;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }
}
