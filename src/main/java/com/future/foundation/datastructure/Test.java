package com.future.foundation.datastructure;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by xingfeiy on 3/22/18.
 */
public class Test {
    private static class MyObj {
        public int val1;
        public int val2;

        public MyObj(int val1, int val2) {
            this.val1 = val1;
            this.val2 = val2;
        }

        public int getVal1() {
            return val1;
        }

        public int getVal2() {
            return val2;
        }

        @Override
        public String toString() {
            return "MyObj{" +
                    "val1=" + val1 +
                    ", val2=" + val2 +
                    '}';
        }
    }
    public static void main(String[] args) {
        List<MyObj> list = Arrays.asList(new MyObj[]{new MyObj(3, 5), new MyObj(2, 2), new MyObj(3, 1), new MyObj(3, 3)});
        list.forEach(obj -> obj.val1 = obj.val1 * 10);
//        list = list.stream().sorted(Comparator.comparingInt(MyObj::getVal2)).collect(Collectors.toList());
        list.sort(Comparator.comparingInt(MyObj::getVal2));
        list.forEach(System.out::println);

        Map<Integer, List<MyObj>> map = list.stream().collect(Collectors.groupingBy(MyObj::getVal1));
        for(Map.Entry<Integer, List<MyObj>> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}
