package com.future.experience.linying;

public interface DataSource<K, T extends Rankable> {
    T get(K key);
}
