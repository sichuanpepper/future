package com.future.experience.gugou;

/**
 * 给两个string, 叫他们old和new吧，比较他们，已知old是通过改变了一个substring变成new的，分析出old是怎么变成new的。
 *               return三个东西，变化的种类（增加，减少，替换），变化的开始位置（some index in the old string）, 变化的部分是什么（从什么变成什么）
 *
 *               例子1： old: "aaabb"    new: "aaaccbb"
 *                            old通过在index = 3 的位置增加了一个"cc"变成new
 *               例子2： old: "aaaccbb"   new: "aaaddbb"
 *                            old通过在index = 3的位置把"cc"替换为"dd"变成new
 *               例子3： old: "aaaccbb"   new: "aaac1234"
 *                            old通过在index = 4的位置把"cbb"替换为"1234"变成new
 */
public class StringTransformation {
}
