package com.future.experience.linying.eley;

/**
 * 1. Process vs Thread
 *
 * 2. TCP vs UDP
 *
 * 3. Mutex vs Semaphore
 *
 * 4. Virtual memory, Paging, Paging fault
 *
 * 5. stack vs heap
 *
 * 6. final vs finally vs finalized
 *
 * 7. write-back cache vs write-through cache
 *
 * 8. ACID
 *
 * 9. Checked/Unchecked Exception
 * - Checked exception is an exception that are checked at compile time. If the code within a method threw a checked exception, the method has to handle the exception or continue throws.
 * - Unchecked exception is an exception that are checked at run time. In Java, all exceptions under Error and RuntimeException are unchecked exception.
 *
 *                    +-----------+
 *                    | Throwable |
 *                    +-----------+
 *                     /         \
 *            /           \
 *           +-------+          +-----------+
 *           | Error |          | Exception |
 *           +-------+          +-----------+
 *           /  |  \           / |           \
 *          \________/      \______/         \
 *                                    +------------------+
 *           unchecked     checked    | RuntimeException |
 *                                    +------------------+
 *                                      /   |    |      \
 *                                     \_________________/
 *
 *                                           unchecked
 *
 * 10. Nest-class(static, non-static(also known as inner class))
 * http://tutorials.jenkov.com/java/nested-classes.html
 *
 * public class Outer {
 *
 *   public static class Nested {
 *
 *   }
 *
 * }
 *
 * Declared: Outer.Nested instance = new Outer.Nested();
 *
 * public class Outer {
 *
 *   public class Inner {
 *   }
 *
 * }
 *
 * Inner class is associated with an instance.
 * Declared:
 * Outer outer = new Outer();
 * Outer.Inner inner = outer.new Inner();
 *
 * 11. How thread communicate to each other?
 */
public class BasicQuestion {
}
