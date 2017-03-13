package credit;

import com.alibaba.fastjson.JSON;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import vo.qiancheng.RiskData;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by syq on 2017/3/10.
 */
public class QianchengRiskRequest extends DsCreditRequest {


    private String name;

    private String mobile;

    private String idCard;

    private RiskData data;

    private String telecomOrderNo;


    public QianchengRiskRequest(String host, Header header) {
        super(host, header);
    }


    @Override
    RequestEntity handle() throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", this.getName());
        paramMap.put("mobile", this.getMobile());
        paramMap.put("idCard", this.getIdCard());
        paramMap.put("telecomOrderNo", this.getTelecomOrderNo());
        paramMap.put("data", this.getData());
        paramMap.put("apiKey", this.header.getApiKey());
        paramMap.put("timestamp", this.header.getTimestamp());
        paramMap.put("sign", this.sign);
        String body = JSON.toJSONString(paramMap);
        return new StringRequestEntity(body, "application/json", "utf-8");
    }

    @Override
    public void signByKey(String secretKey) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", this.getName());

        payload.put("mobile", this.getMobile());

        payload.put("idCard", this.getIdCard());

        this.sign = ApiSignUtil.sign(header.getApiKey(), header.getChannelNo(), header.getInterfaceName(), header.getTimestamp(), secretKey, payload);
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

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public RiskData getData() {
        return data;
    }

    public void setData(RiskData data) {
        this.data = data;
    }

    public String getTelecomOrderNo() {
        return telecomOrderNo;
    }

    public void setTelecomOrderNo(String telecomOrderNo) {
        this.telecomOrderNo = telecomOrderNo;
    }
}
