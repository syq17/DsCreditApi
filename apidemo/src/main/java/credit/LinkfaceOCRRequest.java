package credit;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * linkface OCR 接口请求
 * 特殊接口，需上传图片文件
 * multipart/form-data 格式提交
 * Created by syq on 2016/8/30.
 */
public class LinkfaceOCRRequest extends DsCreditRequest {

    /**
     * 自拍图片文件需jpg格式
     */
    private File IdCardImg;


    public LinkfaceOCRRequest(String host, Header header) {
        super(host, header);
    }

    public void setIdCardImg(File IdCardImg) {
        this.IdCardImg = IdCardImg;
    }


    @Override
    public RequestEntity handle() throws Exception {
        if (IdCardImg == null) {
            System.out.println("缺少IdCardImg参数！");
            return null;
        }
        Part[] parts = {
                new StringPart("apiKey", this.header.getApiKey()),
                new StringPart("timestamp", String.valueOf(this.header.getTimestamp())),
                new StringPart("sign", this.sign),
                new FilePart("IdCardImg", IdCardImg)
        };
        HttpMethodParams httpMethodParams = new HttpMethodParams();
        RequestEntity requestEntity = new MultipartRequestEntity(parts, httpMethodParams);
        return requestEntity;
    }

    /**
     * 重写签名方法
     *
     * @param secretKey
     */
    @Override
    public void signByKey(String secretKey) {
        Map<String, Object> payload = new HashMap<>();
        String base64ImgStr = Base64.encodeBase64String(getBytes());
        base64ImgStr = base64ImgStr.substring(0, 20);
        payload.put("IdCardImgBase64", base64ImgStr);
        this.sign = ApiSignUtil.sign(header.getApiKey(), header.getChannelNo(), header.getInterfaceName(), header.getTimestamp(), secretKey, payload);
    }

    private byte[] getBytes() {
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(this.IdCardImg);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }
}
