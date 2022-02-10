package cn.vt.jackson.annotation;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vate
 */
public class Tests {
    public void test3() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // 造数据
        Map<String, Object> map = new HashMap<>();
        map.put("user_name", "Tom");
        map.put("date", "2020-07-26 19:28:44");
        map.put("age", 100);
        map.put("demoKey", "demoValue");
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
        System.out.println(jsonString);
        System.out.println("反序列化");
        User user = mapper.readValue(jsonString, User.class);
        System.out.println(user);
        System.out.println("序列化");
        jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
        System.out.println(jsonString);
    }
}
