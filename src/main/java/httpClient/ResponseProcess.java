package httpClient;

import javax.xml.ws.Response;

/**
 * 对于批量处理的文件, 传输至网络流之后, 会产生一系列的 response, 所以需要处理这些 reponse
 * @author zhanghao
 * @date 2021/4/28 10:59
 * @Version V1.0
 * @Description: TODO
 */
public interface ResponseProcess {
    void processResponse(Response response);
}
