package com.future.experience.box.memorydatabase;

/**
 * Created by xingfeiy on 8/19/18.
 */
public class KVSessionFactory {
    private static KVSessionFactory factory = null;
    private KVSessionFactory() {}

    public synchronized static KVSessionFactory factory() {
        if(factory == null) {
            factory = new KVSessionFactory();
        }
        return factory;
    }

    public KVSession getSession() {
        return new KVSession(0);
    }
}
