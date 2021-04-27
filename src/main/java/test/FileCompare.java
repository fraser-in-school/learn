package test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class FileCompare {

    public static void main(String[] args) throws IOException {
        fileCompare("C:\\Users\\zhanghao88\\Downloads\\EasyConnectInstaller.exe", "C:\\Users\\zhanghao88\\Downloads\\EasyConnectInstaller (1).exe");
    }
    public static void fileCompare(String firstFilePath, String secondFilePath) throws IOException {
        FileInputStream oldFile = new FileInputStream(firstFilePath);
        FileInputStream newFile = new FileInputStream(secondFilePath);

        InputStreamReader oldStream = new InputStreamReader(oldFile);
        InputStreamReader newStream = new InputStreamReader(newFile);

        BufferedReader oldBuffer = new BufferedReader(oldStream);
        BufferedReader newBuffer = new BufferedReader(newStream);

        byte[] oldBytes = new byte[1024];
        byte[] newBytes = new byte[1024];
        long count = 0;

        ArrayList<byte[]> differenceOld = new ArrayList<>();
        ArrayList<byte[]> differenceNew = new ArrayList<>();
        ArrayList<Long> indexList = new ArrayList<>();
        while(true) {
            int ifNewEnd = newFile.read(newBytes);
            int ifOldEnd = oldFile.read(oldBytes);
            if (ifOldEnd == -1 || ifNewEnd == -1) break;
            if ( ! Arrays.equals(oldBytes, newBytes)) {
                differenceOld.add(oldBytes);
                differenceNew.add(newBytes);
                indexList.add(count ++);
            }

        }
        Writer out = new OutputStreamWriter(System.out);
        System.out.println(indexList.size());
        for (int i = 0; i < indexList.size(); i ++) {
            out.write(indexList.get(i).intValue());
            out.write(differenceOld.get(i).toString());
            System.out.println(differenceNew.get(i));
        }
    }
}
