package cn.vt.jackson.annotation;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// 用于类,指定属性在序列化时 json 中的顺序
@JsonPropertyOrder({"date", "user_name"})
// 批量忽略属性，不进行序列化
@JsonIgnoreProperties(value = {"other"})
// 用于序列化与反序列化时的驼峰命名与小写字母命名转换
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class User {
    @JsonIgnore
    private Map<String, Object> other = new HashMap<>();

    // 正常case
    @JsonProperty("user_name")
    private String userName;
    // 空对象case
    private Integer age;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    // 日期转换case
    private Date date;
    // 默认值case
    private int height;

    public User() {
    }

    // 反序列化执行构造方法
    @JsonCreator
    public User(@JsonProperty("user_name") String userName) {
        System.out.println("@JsonCreator 注解使得反序列化自动执行该构造方法 " + userName);
        // 反序列化需要手动赋值
        this.userName = userName;
    }

    @JsonAnySetter
    public void set(String key, Object value) {
        other.put(key, value);
    }

    @JsonAnyGetter
    public Map<String, Object> any() {
        return other;
    }
    // 本文默认省略getter、setter方法
}