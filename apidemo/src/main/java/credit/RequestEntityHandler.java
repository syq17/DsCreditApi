package credit;

import org.apache.commons.httpclient.methods.RequestEntity;

/**
 * Created by syq on 2016/8/30.
 */
public interface RequestEntityHandler {

    RequestEntity handle() throws Exception;

}
