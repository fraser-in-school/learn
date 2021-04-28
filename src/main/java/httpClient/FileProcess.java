package httpClient;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 该类的目标旨在于获取一个目录下所有文件, 通过封装函数来达到传输网络流的目的
 * @author zhanghao
 * @date 2021/4/28 10:51
 * @Version V1.0
 * @Description: TODO
 */
public class FileProcess {
    private Path myPath;

    public FileProcess(String path) {
        myPath = Paths.get(path);
    }

    private void doPost(ResponseProcess responseProcess) {

    }
}
