package test;

public class TestSubString {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        String cardNo = "JT0202007I0203000047";
        String ans = cardNo.substring(9, cardNo.length() - 6);
//        ans = ans.substring(0, ans.length() - 6);
        System.out.println(ans);
        String msg = "激活失败：卡已被激活！";
        System.out.println(msg.indexOf("失败") != -1 );
    }
}
