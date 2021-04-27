package test;

import java.util.regex.Pattern;

public class RegTest {
    public static void main(String[] args){
        String pattern = "7,8 ,9";
        Boolean isMatch = Pattern.matches("^\\d+(,\\d+)*", pattern);
        System.out.println(isMatch);
    }
}
