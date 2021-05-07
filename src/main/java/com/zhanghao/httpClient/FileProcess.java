package com.zhanghao.httpClient;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 该类的目标旨在于获取一个目录下所有文件, 通过封装函数来达到传输网络流的目的
 * @author zhanghao
 * @date 2021/4/28 10:51
 * @Version V1.0
 * @Description: TODO
 */
public class FileProcess {
    private Path myPath;

    private ConcurrentLinkedQueue<MyRequest> requestQueue;

    private ConcurrentLinkedQueue<MyResponse> responseQueue;

    private URL url;

    private int sleepTime = 30;

    private OutputStreamWriter responseWriter;

    private OutputStreamWriter successWriter;


    public FileProcess(String path) throws FileNotFoundException {
        myPath = Paths.get(path);
        requestQueue = new ConcurrentLinkedQueue<>();
        responseQueue = new ConcurrentLinkedQueue<>();
        FileOutputStream fos = new FileOutputStream(new File("./response.json"));
        FileOutputStream success = new FileOutputStream(new File("./success.txt"));
        responseWriter = new OutputStreamWriter(fos);
        successWriter = new OutputStreamWriter(success);
    }

    private void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    private void loadUrl(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    private void responseHandle() throws IOException {
        while( ! this.responseQueue.isEmpty()) {
            MyResponse myResponse = responseQueue.poll();
            this.responseWriter.write(FastjsonUtils.toJSONString(myResponse) + "\n");
            if (myResponse.getResponse().contains(":200")) {
                this.successWriter.write(FastjsonUtils.toJSONString(myResponse.getRequest().strParams) + "\n");
            }
        }
    }

    private void run() throws InterruptedException, IOException {
        this.doPost();
        while( ! this.requestQueue.isEmpty()) {
            this.send(100, sleepTime);
            this.responseHandle();
        }
        this.successWriter.close();
        this.responseWriter.close();
    }


    private void makeRequest(Path file){
        Map<String, String> params = new HashMap<>();
        params.put("filePath", file.getFileName().toString());
        params.put("createdByCd", "dev-zh");
        params.put("createdName", "开发者");

        Map<String, File> fileMap = new HashMap<>();
        fileMap.put("file", new File(file.toString()));

        MyRequest request = new MyRequest(params, fileMap);
        this.requestQueue.add(request);
    }

    private void send(int reqNum, int sleepTime) throws InterruptedException {
        int sendNum = 0;
        while( ! this.requestQueue.isEmpty() && sendNum < reqNum) {
            MyRequest myRequest = requestQueue.poll();
            HttpClientUtils.postRequest(myRequest, this.url, this.responseQueue);
            sendNum ++;
            Thread.sleep(sleepTime);
        }
    }
    private void doPost() throws IOException {
        Files.walkFileTree(myPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                makeRequest(file);
                return super.visitFile(file, attrs);
            }
        });
    }


    public static void main(String[] args) throws MalformedURLException, FileNotFoundException {
        String env = "loc";
        String requestUrl = "http://127.0.0.1:8200/fileOs/uploadOldFile";
        String workPath = "D:\\MyDocuments\\zhanghao88\\Documents\\My Pictures\\壁纸";
        int sleepTime = 30;
        if (args.length > 0) {
            switch (args[0]) {
                case "dev":
                    env = "dev";
                    requestUrl = "http://10.154.140.71:8200/fileOs/uploadOldFile";
                    break;
                case "test":
                    env = "test";
                    requestUrl = "http://10.154.140.70:8200/fileOs/uploadOldFile";
                    break;
                case "trn":
                    env = "trn";
                    requestUrl = "http://10.154.140.69:8200/fileOs/uploadOldFile";
                    break;
            }
            if (args.length > 1) {
                workPath = args[1];
            }

            if (args.length > 2) {
                sleepTime = Integer.valueOf(args[2]);
            }
        }
        FileProcess fileProcess = new FileProcess(workPath);
        fileProcess.loadUrl(requestUrl);
        fileProcess.setSleepTime(sleepTime);
        try {
            fileProcess.run();
        } catch (IOException | InterruptedException e) {
            System.out.println(e);
        }
    }
}
