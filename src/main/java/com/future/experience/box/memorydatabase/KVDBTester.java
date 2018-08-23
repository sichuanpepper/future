package com.future.experience.box.memorydatabase;

/**
 * Created by xingfeiy on 8/20/18.
 */
public class KVDBTester {
    public static void main(String[] args) {
        KVDatabase<Integer, String> database = new KVDatabase(10000000, 100);
        KVSession session = database.getSession();
        session.set(1, "One");
        System.out.println(session.get(1));
        session.set(1, "A");
        System.out.println(session.get(1));
        session.beginTransaction();
        session.set(2, "Two");
        session.set(3, "Three");
        System.out.println(session.get(2));
        session.commit();

        session.beginTransaction();
        session.set(2, "B");
        session.rollback();
        System.out.println(session.get(2));
    }
}
