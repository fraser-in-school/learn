package test;


import Utils.EnumUseType;
import Utils.Stock;
import Utils.UseTypeVo;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.reducing;


public class CollectorsTest {
    public static void main(String args[]) {
        Stock first = new Stock("code", 100, "");

        Stock second = new Stock("code", 200, "");

        List<Stock> stocks = new ArrayList<>();

        List<Integer> linkedList  = new LinkedList<>();

        linkedList.add(1);
        linkedList.add(2);
        ListIterator<Integer> listIterator = linkedList.listIterator();
        linkedList.add(3);
        listIterator.next();
        linkedList.add(4);


        stocks.add(first);

        stocks.add(second);

        ListIterator<Stock> iterator = stocks.listIterator();

        iterator.next();

        stocks.add(first);

        iterator.next();

        stocks.add(second);

        BinaryOperator<Stock> addOperator = new BinaryOperator<Stock>() {
            @Override
            public Stock apply(Stock first, Stock second) {
                if (first.getCode() == null) {
                    return second;
                }
                if (second.getCode() == null) {
                    return first;
                }
                first.setStockNumber(first.getStockNumber() + second.getStockNumber());
                return first;
            }
        };
        Map<String, Stock> newStocks = stocks.stream().collect(Collectors.groupingBy(Stock::getCode, Collectors.reducing(new Stock(), addOperator)));

        Map<String, String> useTypeMap = EnumUseType.toMap();
        List<UseTypeVo> list =  useTypeMap.entrySet().stream().map(e -> {
            if (e.getKey().equals("Z33")) {
                return null;
            }
            UseTypeVo vo = new UseTypeVo();
            vo.setUseTypeCode(e.getKey());
            vo.setUseTypeDesc(e.getValue());
            return vo;
        }).collect(Collectors.toList());
        System.out.println(newStocks);
//        Comparator<Person> byHeight = Comparator.comparing(Person::getHeight);
//        Map<String, Optional<Person>> tallestByCity = people.stream()
//                .collect(Collectors.groupingBy(Person::getCity, Collectors.reducing(BinaryOperator.maxBy(byHeight))));
        }
}
