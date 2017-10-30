package com.future.foundation.stack;

/**
 * Created by someone on 9/20/17.
 */
public class StackArray<T> {
    private T[] array;

    private int size = 0;

    public StackArray() {
        array = (T[]) new Object[2];
    }

    private void resize(int capacity) {
        if(capacity < 0) {
            return;
        }
        T[] tmp = (T[]) new Object[capacity];
        System.arraycopy(array, 0, tmp, 0, size);
        array = tmp;
    }

    public void push(T t) {
        if(size == array.length) {
            resize(array.length * 2);
        }
        array[size++] = t;
    }

    public T pop() {
        if(size == 0) {
            return null;
        }
        T res = array[--size];
        array[size] = null;
        return res;
    }

}
