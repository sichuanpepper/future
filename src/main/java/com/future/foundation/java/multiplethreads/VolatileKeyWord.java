package com.future.foundation.java.multiplethreads;

/**
 * Volatile marks a variable as "being stored in main memory(Not CPU cache)"
 */
public class VolatileKeyWord {
    private int years;

    private int months;

    // a value is written to days, it written to main memory, other visible variables also be written to main memory.
    private volatile int days;


    public void update(int years, int months, int days){
        this.years  = years;
        this.months = months;
        this.days   = days;  //years and months and days are written to main memory? maybe not since reordering
    }
}
