package vo.qiancheng;

/**
 * Created by syq on 2017/3/3.
 */
public class UserProofMateria {


    /**
     * 照片 url  (可能是活体照片，身份证背面，身份证反面)   (必填)
     */
    private String url;


    /**
     * 类型 0：活体照片， 1：身 份证正面， 2：身份证反面  (必填)
     */
    private Integer type;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
