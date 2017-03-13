package vo.qiancheng;

/**
 * Created by syq on 2017/3/3.
 */
public class CardInfo {


    /**
     * 卡号
     */
    private String card_no;


    /**
     * 类型（ 1 信用卡， 2 储蓄卡， 3 未知）
     */
    private Integer type;


    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
