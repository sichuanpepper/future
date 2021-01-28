package com.future.experience.gugou;

/**
 * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=667648&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%255B3046%255D%255Bvalue%255D%3D1%26searchoption%255B3046%255D%255Btype%255D%3Dradio%26orderby%3Ddateline&page=1
 *
 * replace字符串
 * Example 1.
 * "X" --> 123
 * "Y" --> 456
 *
 * "%X%_%Y%" --> 123_456
 *
 * Example 2.
 * "usr" --> "google"
 * "HOME" --> "/usr/%usr%"
 *
 * "%HOME%" --> "/usr/google"
 *
 * 我的想法是，替代两个%中间的字符串，但是在第二个例子中，需要用到recursion的方法解决“如果结果中仍然含有可以替换的字符串”的问题
 * 这个问题需要前期跟面试官做好clarification，例如如何判断%里面包含的是要替换的字符，如果替换的字符不在字典里面怎么办？还有%XY%这种情况要返回nothing还是返回%XY%, 诸如此类的问题
 */
public class StringReplacement {
}
