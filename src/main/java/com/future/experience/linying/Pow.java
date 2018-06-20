package com.future.experience.linying;

/**
 *
 Implement pow(x, n), which calculates x raised to the power n (xn).

 Example 1:

 Input: 2.00000, 10
 Output: 1024.00000
 Example 2:

 Input: 2.10000, 3
 Output: 9.26100
 Example 3:

 Input: 2.00000, -2
 Output: 0.25000
 Explanation: 2-2 = 1/22 = 1/4 = 0.25

 * Created by xingfeiy on 6/17/18.
 */
public class Pow {
    public double myPow(double x, int n) {
        if(n == 0) return 1;
        if(Double.compare(x, 0.0) == 0) return 0;
        if(n < 0) {
            x = 1.0 / x;
            n = (n == Integer.MIN_VALUE) ? Integer.MAX_VALUE : -n;
        }
        return n % 2 == 0 ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
    }

    public static void main(String[] args) {
        Pow p = new Pow();
        System.out.println(Double.compare(p.myPow(2.00000, 10), 1024.00000)); //0
        System.out.println(p.myPow(2.10000, 3));
        System.out.println(Double.compare(p.myPow(2.10000, 3), 9.26100)); //0
        //if n is negative, x^n = (1/x)^(-n)
        System.out.println(p.myPow(2.00000, -2));
        System.out.println(Double.compare(p.myPow(2.00000, -2), 0.25000)); //0
        System.out.println(Double.compare(p.myPow(2.00000, 0), 1.0)); //0
        System.out.println(Double.compare(p.myPow(0.0, 10), 0.0)); //0
        System.out.println(Double.compare(p.myPow(0.0, -10), 0.0)); //0
        System.out.println(Double.compare(p.myPow(1.0, Integer.MIN_VALUE), 1.0)); //0
    }
}
