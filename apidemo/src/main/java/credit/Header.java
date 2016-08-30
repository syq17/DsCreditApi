package credit;

/**
 * Created by syq on 2016/8/28.
 */
public class Header {

    private String apiKey;

    private String channelNo;

    private String interfaceName;

    private long timestamp;


    public Header(String apiKey, String channelNo, String interfaceName, long timestamp) {
        this(apiKey, timestamp);
        this.channelNo = channelNo;
        this.interfaceName = interfaceName;
    }

    public Header(String apiKey, long timestamp) {
        this.apiKey = apiKey;
        this.timestamp = timestamp;
    }


    public String getApiKey() {
        return apiKey;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
