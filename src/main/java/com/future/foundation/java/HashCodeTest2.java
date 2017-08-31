package com.future.foundation.java;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xingfeiy on 5/5/17.
 */
public class HashCodeTest2 {
    private String name;

    private int age;

    private String address;

    public HashCodeTest2(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public void changeAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HashCodeTest2 that = (HashCodeTest2) o;

        if (age != that.age) return false;
        return !(name != null ? !name.equals(that.name) : that.name != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }

    public static void main(String[] args) {
        Map<HashCodeTest2, String> maps = new HashMap<>();
        HashCodeTest2 test = new HashCodeTest2("Hello", 21, "2411 Great American PKW");
        maps.put(test, "It's a test");
        System.out.println(maps.get(test)); //Output => "It's a test"
        System.out.println(test.hashCode());  //-2137068125
        test.changeAddress("1130 Kifer Rd");
        System.out.println(maps.get(test)); //Output => "It's a test"
        System.out.println(test.hashCode());  //-2137068125
    }
}
