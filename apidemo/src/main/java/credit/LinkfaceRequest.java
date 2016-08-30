package credit;

import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.File;
import java.net.URLEncoder;

/**
 * linkface接口请求
 * 特殊接口，需上传图片文件
 * multipart/form-data 格式提交
 * Created by syq on 2016/8/30.
 */
public class LinkfaceRequest extends DsCreditRequest {

    /**
     * 自拍图片文件需jpg格式
     */
    private File livingImg;


    public LinkfaceRequest(String host, Header header) {
        super(host, header);
    }

    public void setLivingImg(File livingImg) {
        this.livingImg = livingImg;
    }


    @Override
    public RequestEntity handle() throws Exception {
        if (livingImg == null) {
            System.out.println("缺少livingImg参数！");
            return null;
        }
        if (this.payload.get("name") == null) {
            System.out.println("缺少name参数！");
            return null;
        }
        if (this.payload.get("idCard") == null) {
            System.out.println("缺少idCard参数！");
            return null;
        }
        Part[] parts = {
                new StringPart("apiKey", this.header.getApiKey()),
                new StringPart("interfaceName", this.header.getInterfaceName()),
                new StringPart("channelNo", this.header.getChannelNo()),
                new StringPart("timestamp", String.valueOf(this.header.getTimestamp())),
                new StringPart("sign", this.sign),
                new StringPart("name", URLEncoder.encode((String) this.payload.get("name"),"utf-8")),
                new StringPart("idCard", (String) this.payload.get("idCard")),
                new FilePart("livingImg", livingImg)
        };
        HttpMethodParams httpMethodParams = new HttpMethodParams();
        RequestEntity requestEntity = new MultipartRequestEntity(parts, httpMethodParams);
        return requestEntity;
    }
}
