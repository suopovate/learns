import cn.vt.jackson.base.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;

import java.io.IOException;
import java.util.*;

/**
 * @author vate
 */
public class Tests {

    public static void main(String[] args) throws IOException {
        testTreeHandle();
    }

    public void testList() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CollectionType javaType = mapper.getTypeFactory().constructCollectionType(List.class, Person.class);
        // 造数据
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Person person = new Person();
            person.setName("Tom");
            person.setAge(new Random().nextInt(100));
            person.setDate(new Date());
            list.add(person);
        }
        System.out.println("序列化");
        String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        System.out.println(jsonInString);
        System.out.println("反序列化：使用 javaType");
        List<Person> personList = mapper.readValue(jsonInString, javaType);
        System.out.println(personList);
        System.out.println("反序列化：使用 TypeReference");
        List<Person> personList2 = mapper.readValue(jsonInString, new TypeReference<List<Person>>() {
        });
        System.out.println(personList2);
    }

    public void testMap() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //第二参数是 map 的 key 的类型，第三参数是 map 的 value 的类型
        MapType javaType = mapper.getTypeFactory().constructMapType(HashMap.class, String.class, Person.class);
        // 造数据
        Map<String, Person> map = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            Person person = new Person();
            person.setName("Tom");
            person.setAge(new Random().nextInt(100));
            person.setDate(new Date());
            map.put("key" + i, person);
        }
        System.out.println("序列化");
        String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
        System.out.println(jsonInString);
        System.out.println("反序列化: 使用 javaType");
        Map<String, Person> personMap = mapper.readValue(jsonInString, javaType);
        System.out.println(personMap);
        System.out.println("反序列化: 使用 TypeReference");
        Map<String, Person> personMap2 = mapper.readValue(jsonInString, new TypeReference<Map<String, Person>>() {
        });
        System.out.println(personMap2);
    }

    public static void testTreeHandle() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //构建 ObjectNode
        ObjectNode personNode = mapper.createObjectNode();
        //添加/更改属性
        personNode.put("name", "Tom");
        personNode.put("age", 40);
        ObjectNode addressNode = mapper.createObjectNode();
        addressNode.put("zip", "000000");
        addressNode.put("street", "Road NanJing");
        //设置子节点
        personNode.set("address", addressNode);
        System.out.println("构建 ObjectNode:\n" + personNode.toString());
        //通过 path 查找节点
        JsonNode searchNode = personNode.path("name");
        System.out.println("查找子节点 name:\n" + searchNode.asText());
        //删除属性
        personNode.remove("address");
        System.out.println("删除后的 ObjectNode:\n" + personNode.toString());
        //读取 json
        JsonNode rootNode = mapper.readTree(personNode.toString());
        System.out.println("Json 转 JsonNode:\n" + rootNode);
        //JsonNode 转换成 java 对象
        Person person = mapper.treeToValue(personNode, Person.class);
        System.out.println("JsonNode 转对象:\n" + person);
        //java 对象转换成 JsonNode
        JsonNode node = mapper.valueToTree(person);
        System.out.println("对象转 JsonNode:\n" + node);
    }
}
