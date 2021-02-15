package com.future.java.lambda.eventlisterner;

/**
 * In Java, a event listener is often defined as an interface with a single method.
 */
public interface StateChangeListener {

    void onStateChange(int oldState, int newState);

}
