package credit;

import com.alibaba.fastjson.JSON;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by syq on 2016/8/28.
 */
public class CreditRequest extends DsCreditRequest {

    public CreditRequest(String host, Header header, Map<String, Object> payload) {
        super(host, header, payload);
    }

    public CreditRequest(String host, Header header) {
        super(host, header);
    }

    @Override
    public RequestEntity handle() throws UnsupportedEncodingException {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("apiKey", this.header.getApiKey());
        paramMap.put("channelNo", this.header.getChannelNo());
        paramMap.put("interfaceName", this.header.getInterfaceName());
        paramMap.put("timestamp", this.header.getTimestamp());
        paramMap.put("sign", this.sign);
        paramMap.put("payload", this.payload);
        String body = JSON.toJSONString(paramMap);
        return new StringRequestEntity(body, "application/json", "utf-8");
    }
}
