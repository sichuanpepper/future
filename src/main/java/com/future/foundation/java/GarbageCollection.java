package com.future.foundation.java;

/**
 * Garbage Collector:
 * - Free heap memory by destroy unreachable objects(or island of isolation).
 *
 * Four ways to make an object eligible for garbage collection.
 * - Nullifying the reference.
 * - Re-assigning the reference.
 * - Object create inside method.
 * - Island of isolation
 * Created by xingfeiy on 5/30/18.
 */
public class GarbageCollection {

    /**
     * The finalize method will be invoked by garbage collector(NOT JVM) before destroying an object(this object).
     * The finalize method is never invoked more than once for any given object.
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
