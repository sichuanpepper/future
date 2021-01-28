package com.future.experience.gugou;

/**
 * https://leetcode.com/problems/employee-importance/
 *
 *
 * 给定一个map，key是employee ID，value是array/list of direct reports. 计算对一个给定employee ID的employee scor.
 * Employee scor是direct report的个数 + indirect reports的个数 + 1.
 *
 * {
 *   “ABC” => [ “BCD”, “CDE” ],
 *   “BCD” => [ “DEF” ],
 *   “CDE” => [],
 *   “DEF” => []
 * }
 *
 * score(“DEF”) => 1
 * score(“ABC”) => 4
 *
 * Follow up:
 * 如何处理millions of employees?
 * - Store it in the database...
 * 如何处理millions of calls to score() method
 * - Pre-compute
 * 如何validate invalid input map? Lots of corner cases.
 * - It's a tree struct, only one tree.
 * - No cycle.
 */
public class EmployeeImportance {
}
