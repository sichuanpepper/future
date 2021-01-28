package com.future.experience.gugou;

/**
 * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=667648&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%255B3046%255D%255Bvalue%255D%3D1%26searchoption%255B3046%255D%255Btype%255D%3Dradio%26orderby%3Ddateline&page=1
 *
 * 找出string中连续出现次数大于等于3次的字母，返回他们的起始点和中点
 * 例如heeelloooo 返回 [[1, 3], [6, 9]]
 * 这个题用liner scan就可以解决，记得考虑一些corner case，例如指针出界，或者字符串为null或者为空的情况
 *
 * Followup：给你一个字典，你可以查出某个string是不是字典里面的单词
 * 类似于boolean valid(String str)
 * valid("hello") --> true
 * valid("heeellooo") --> false
 *
 * 让你写一个function, 验证之前的词可否通过减少重复出现3次以上的字符数量来匹配字典里的词？
 * 我的想法是dedup的思想，如果对于连续出现3次以上的字符，不断减少字数数量，看是否和字典里的词匹配
 * boolean isValidExtension(String str) {}
 * isValidExtension("heeelloooo") --> true
 */
public class FindContinuousDuplicatedChar {

    public boolean isAbleToConvert(String word) {
        return false;
    }

    private boolean valid(String word) {
        return true;
    }
}
