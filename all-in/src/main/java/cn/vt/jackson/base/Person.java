package cn.vt.jackson.base;

import java.util.Date;

public class Person {
    // 正常case
    private String name;
    // 空对象case
    private Integer age;
    // 日期转换case
    private Date date;
    // 默认值case
    private int height = 15;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}