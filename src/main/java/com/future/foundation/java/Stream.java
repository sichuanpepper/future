package com.future.foundation.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by someone on 4/5/17.
 */
public class Stream {
    private static final List<String> myList = Arrays.asList("a2", "a1", "b1", "c2", "c1");

    public static void main(String[] args) {

        //Please notes what is intermediate operations and what is terminal operations.

        //find the elements start with 'a'
        myList.stream().filter(s -> s.startsWith("a")).forEach(System.out::println);

        //find teh elements start with 'a' and sort that
        myList.stream().filter(s -> s.startsWith("a")).sorted().forEach(System.out::println);

        //find the elements start with 'a' and covert all elements to upper case, then sort them
        myList.stream().filter(s -> s.startsWith("a")).map(s1 -> s1.toUpperCase()).sorted().forEach(System.out::println);


        //find the elements start with 'a' and sort it desc
        //please notes that, b.compareTo(a) is different to a.compareTo(a)
        myList.stream().filter(s -> s.startsWith("a")).sorted((a, b) -> b.compareTo(a)).forEach(System.out::println);

        //sort an integer array.
        IntStream.of(3, 5, 8, 2, 7).sorted().forEach(System.out::print);

        //get average
        IntStream.of(3, 5, 8, 2, 7).average().ifPresent(System.out::println);

        IntStream.range(1, 10).average().ifPresent(System.out::println);

        double avg = IntStream.range(1, 10).average().getAsDouble();


        //There's nothing will be printed on console, the intermediate operations will
        // only be executed when a terminal operation is present.
        myList.stream().filter(
                s -> {
                    s.startsWith("a");
                    System.out.print("hello world");
                    return true;
                });


        //Question, how to return the sorted array or average?


        //convert the average to integer, todo, i don't how to do that yet.

        //find the max
        IntStream.of(3, 5, 8, 2, 7).max().ifPresent(v -> System.out.printf("max value is %s.", v));

        //now let's do a "big" job, there is a string array, try to find all the elements which meet the regex "l\\d+",
        //and then convert l1 to country, l2 to state, l3 to county and l4 to city.
//        Arrays.asList("l1", "c3", "l2", "l4", "l3", "d2").stream().


        //obj to obj
        //step 1, create a list of person
        List<Person> persons = new ArrayList<>();
        IntStream.range(0, 3).forEach(i -> persons.add(new Person("p" + i, 10 + i)));

        List<Superman> supermans = persons.stream().
                map(f -> new Superman(f.name)).collect(Collectors.toList());

        supermans.forEach(f -> f.setSuperPowers(Arrays.asList(f.name + " power")));
        supermans.forEach(System.out::print);

        persons.stream().forEach(System.out::println);

        supermans = persons.stream().map(Stream::toSuperman).collect(Collectors.toList());
        supermans.stream().forEach(System.out::println);

//        supermans = persons.stream().map(p -> {
//            System.out.println();
//            Superman superman = new Superman(p.name + " super");
//            superman.setSuperPowers(Arrays.asList("Fly " + p.age, "Sing " + p.age));
//        });
    }

    public static Superman toSuperman(Person p) {
        Superman superman = new Superman(p.name + " super");
        superman.setSuperPowers(Arrays.asList("Fly " + p.age, "Sing " + p.age));
        return superman;
    }

    private static class Person {
        private String name;

        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    private static class Superman {
        private String name;

        private List<String> superPowers = new ArrayList<>();

        public Superman(String name) {
            this.name = name;
        }

        public List<String> getSuperPowers() {
            return superPowers;
        }

        public void setSuperPowers(List<String> superPowers) {
            this.superPowers = superPowers;
        }

        @Override
        public String toString() {
            return "Superman{" +
                    "name='" + name + '\'' +
                    ", superPowers=" + superPowers +
                    '}';
        }
    }
}