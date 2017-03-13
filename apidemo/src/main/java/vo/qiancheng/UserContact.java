package vo.qiancheng;

/**
 * Created by syq on 2017/3/7.
 */
public class UserContact {

    /**
     * 手机号 （必填）
     */
    private String mobile;


    /**
     * 姓名 （必填）
     */
    private String name;


    /**
     * 关系 0:父亲,1:母亲, 2:配偶,3:朋友,4:其他
     */
    private Integer relation;


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRelation() {
        return relation;
    }

    public void setRelation(Integer relation) {
        this.relation = relation;
    }
}
