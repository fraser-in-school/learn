package com.zhanghao.httpClient;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fyn on 2016/11/12.
 */
public class FastjsonUtils {
    // 把JSON文本parse为JSONObject或者JSONArray
    public static final Object parse(String text){
        return JSON.parse(text);
    }
    // 把JSON文本parse成JSONObject
    public static final JSONObject parseObject(String text){
        return JSON.parseObject(text);
    }
    // 把JSON文本parse为JavaBean
    public static final <T> T parseObject(String text, Class<T> clazz){
        return JSON.parseObject(text, clazz);
    }
    // 把JSON文本parse成JSONArray
    public static final JSONArray parseArray(String text){
       return JSON.parseArray(text);
    }
    //把JSON文本parse成JavaBean集合
    public static final <T> List<T> parseArray(String text, Class<T> clazz){
        JSON.parseObject(text, new TypeReference<List<T>>() {});
       return JSON.parseArray(text, clazz);
    }
    // 将JavaBean序列化为JSON文本
    public static final String toJSONString(Object object){
        return JSON.toJSONString(object);
    }
    // 将JavaBean序列化为带格式的JSON文本
    public static final String toJSONString(Object object, boolean prettyFormat){
        return JSON.toJSONString(object, prettyFormat);
    }
    // 将JavaBean序列化为JSON文本，包括空值字段
    public static final String toJSONStringIncludeNullValue(Object object){
        return JSONObject.toJSONString(object, SerializerFeature.WriteMapNullValue);
    }
    //将JavaBean转换为JSONObject或者JSONArray
    public static final Object toJSON(Object javaObject){
        return JSON.toJSON(javaObject);
    }
    // 把JSON文本parse成Map
    public static final Map<String, String> toMap(String jsonStr){
        return JSON.parseObject(jsonStr, Map.class);
    }
    
    public static final Map<String, String> toMap(Object obj){
        return JSON.parseObject(JSON.toJSON(obj).toString(), Map.class);
    }
    
    // 把JSON文本parse成LinkedHashMap
    public static final Map<String, String> toLinkedHashMap(String jsonStr){
        return JSON.parseObject(jsonStr, LinkedHashMap.class,Feature.OrderedField);
    }
 
    // 把JSON文本parse成List
    public static final <T> List<T> toCollection(String jsonStr,Class<T> clazz){
       // JSON.parseObject(jsonStr, new TypeReference<List<T>>() {});
        return JSON.parseArray(jsonStr, clazz);
    }


    public static void main(String agrs[]){
        String jsonStr=toJSONString("{\"ret\":\"0\",\"items\":[{\"zoneId\":1638,\"employeeId\":722,\"warTeamName\":null,\"manager\":null,\"warZone\":null},{\"zoneId\":1639,\"employeeId\":193,\"warTeamName\":null,\"manager\":null,\"warZone\":null},{\"zoneId\":1640,\"employeeId\":636,\"warTeamName\":null,\"manager\":null,\"warZone\":null},{\"zoneId\":2089,\"employeeId\":737,\"warTeamName\":null,\"manager\":null,\"warZone\":null}]}");
        System.out.println("+++++++++++++++++++++" +parse(jsonStr));
        //Map map = new HashMap();
        //map.put("username", "周伯通");
        //map.put("address", "广东省仙游谷");
        //map.put("age", "198");
        //String json = JSON.toJSONString(map);
        //Map map1 = JSON.parseObject(json);
        //System.out.println("++++++++++++++"+json);
        //for (Object obj : map.entrySet()) {
        //    Map.Entry<String, String> entry = (Map.Entry<String, String>) obj;
        //    System.out.println(entry.getKey() + "--->" + entry.getValue());
        //}
        Map<String, String> tempMap = JSON.parseObject(jsonStr,Map.class);
        //for(String key : tempMap.keySet()){
        //    Object  str = tempMap.get(key);
        // //   HomePagePicModel parseObject = JSON.parseObject(str.toJSONString(), HomePagePicModel.class);
        //    System.out.println(key+":"+str);
        //}
    }

}
