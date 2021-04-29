package httpClient;

/**
 * @author zhanghao88
 * @date 2021/4/29 15:28
 * @Version V1.0
 * @Description: TODO
 */
public class MyResponse {
    private MyRequest request;
    private String response;

    public MyResponse(MyRequest request, String response) {
        this.request = request;
        this.response = response;
    }

    public MyRequest getRequest() {
        return this.request;
    }

    public String getResponse() {
        return this.response;
    }
}
