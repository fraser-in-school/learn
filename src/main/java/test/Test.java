package test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        int smallIntValue = 1;
        int bigIntValue = 1000;
        Integer smallConstIntegerValue = 1;
        Integer bigConstIntegerValue = 1000;
        Integer newSmallIntegerValue = new Integer(1);
        Integer newBigIntegerValue = new Integer(1000);
        smallIntValue = newBigIntegerValue.intValue();
//        System.out.println(Integer.valueOf(bigConstIntegerValue).equals(smallConstIntegerValue));

        Map<Long, Long> replaceMap = new HashMap<Long, Long>();
        replaceMap.put(new Long(666), new Long(888));
        System.out.println(replaceMap.get(666));
        System.out.println(new StringBuilder().toString().equals(""));
        List<Integer> list = new ArrayList<Integer>();
        System.out.println(list);
        BigDecimal a = new BigDecimal(490);
        BigDecimal b = new BigDecimal("490.00");
        a = a.setScale(2);
        b = b.setScale(2);
        System.out.println(a.compareTo(b));
        System.out.println(new Date().getTime());
        System.out.println(b.hashCode());
    }

}
