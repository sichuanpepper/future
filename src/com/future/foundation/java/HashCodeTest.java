package com.future.foundation.java;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xingfeiy on 5/5/17.
 */
public class HashCodeTest {
    private String name;

    private int age;

    private String address;

    public HashCodeTest(String name, int age, String address) {
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

        HashCodeTest that = (HashCodeTest) o;

        if (age != that.age) return false;
        if (!name.equals(that.name)) return false;
        return !(address != null ? !address.equals(that.address) : that.address != null);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + age;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    public static void main(String[] args) {
        Map<HashCodeTest, String> maps = new HashMap<>();
        HashCodeTest test = new HashCodeTest("Hello", 21, "2411 Great American PKW");
        maps.put(test, "It's a test");
        System.out.println(maps.get(test)); //Output => "It's a test"
        System.out.println(test.hashCode());  //-112799638
        test.changeAddress("1130 Kifer Rd");
        System.out.println(maps.get(test)); //Output => null
        System.out.println(test.hashCode()); //-2006008835
    }
}
