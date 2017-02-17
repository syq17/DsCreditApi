package credit;

import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * linkface接口请求
 * 特殊接口，需上传图片文件
 * multipart/form-data 格式提交
 * Created by syq on 2016/8/30.
 */
public class LinkfaceHsRequest extends DsCreditRequest {

    /**
     * 自拍图片文件需jpg格式
     */
    private File livingImg;

    /**
     * 身份证头像
     */
    private File ocrImg;

    private String idCard;

    private String name;

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkfaceHsRequest(String host, Header header) {
        super(host, header);
    }

    public void setLivingImg(File livingImg) {
        this.livingImg = livingImg;
    }

    public void setOcrImg(File ocrImg) {
        this.ocrImg = ocrImg;
    }

    @Override
    public RequestEntity handle() throws Exception {
        if (livingImg == null) {
            System.out.println("缺少livingImg图片！");
            return null;
        }
        if (ocrImg == null) {
            System.out.println("缺少ocrImg图片！");
            return null;
        }
        Part[] parts = {
                new StringPart("apiKey", this.header.getApiKey()),
                new StringPart("timestamp", String.valueOf(this.header.getTimestamp())),
                new StringPart("sign", this.sign),
                new FilePart("livingImg", livingImg),
                new FilePart("ocrImg", ocrImg),
                new StringPart("idCard", this.idCard),
                new StringPart("name", this.name, "utf-8")
        };
        HttpMethodParams httpMethodParams = new HttpMethodParams();
        RequestEntity requestEntity = new MultipartRequestEntity(parts, httpMethodParams);
        return requestEntity;
    }


    @Override
    public void signByKey(String secretKey) {
        Map<String, Object> payload = new HashMap<>();
//        String base64LivingImgStr = Base64.encodeBase64String(getBytes(livingImg));
//        base64LivingImgStr = base64LivingImgStr.substring(0, 20);
//
//        String base64OcrImgStr = Base64.encodeBase64String(getBytes(ocrImg));
//        base64OcrImgStr = base64OcrImgStr.substring(0, 20);

//        payload.put("livingImgBase64", base64LivingImgStr);
//        payload.put("ocrImgBase64", base64OcrImgStr);

        payload.put("idCard", idCard);

        payload.put("name", name);

        this.sign = ApiSignUtil.sign(header.getApiKey(), header.getChannelNo(), header.getInterfaceName(), header.getTimestamp(), secretKey, payload);
    }


}
