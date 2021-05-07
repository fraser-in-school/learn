package com.zhanghao.httpClient;

import java.io.File;
import java.util.Map;

/**
 * @author zhanghao88
 * @date 2021/4/29 13:57
 * @Version V1.0
 * @Description: TODO
 */

public class MyRequest {
    private String Method = "POST";
    Map<String, String> strParams;
    Map<String, File> fileParams;

    public MyRequest(Map<String, String> strParams, Map<String, File> fileParams) {
        this.strParams = strParams;
        this.fileParams = fileParams;
    }
}
