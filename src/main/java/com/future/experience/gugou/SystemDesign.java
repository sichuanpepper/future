package com.future.experience.gugou;

/**
 * 为谷歌实现一个服务，用户可以用这个服务来查询某个FB hashtag的活跃度的时间分布。可以从FB来query内容，每个内容只能query一次。
 * 这个hashtag在查询的时间区间出现多少次
 *
 * Requirements:
 * Get count:
 * - Get: /v1/hashtag/count
 *      - Paras: start, end, hashtag
 * Minimum/Maximum time range?
 * Granularity of time? in second or in minute?
 * QPS?
 * What's the consistency level? or the latency?
 *
 * Get post:
 * - Get: /facebook/post  (Assume we already have it)
 * Realtime? Streaming?
 * Quick calculation: 100M posts per day, 2 hashtags per post
 *
 * Post -> timestamp, list of hashtag -> database
 *
 *
 * 如果建造一个系统来数Wikipedia里面所有词出现的频率
 *
 * ***********************************************************************************************
 * Auto complete
 * Refer to:
 * https://medium.com/@dingdingsherrywang/system-design-autocomplete-62420021adb0
 * https://lopespm.github.io/2020/08/03/implementation-autocomplete-system-design.html
 *
 * Requirements:
 * - Give a list of query suggestions based on user input (Prefix)
 * - The query list is sorted by some ranking score
 * - Auto correction???
 *
 * Non-functional requirements:
 * - Low latency, the response time should be faster than the type speed.
 * - Result accuracy and system availability are not strictly required.
 *
 * Data structure
 * - Dictionary Trie
 * - Store common prefix in node instead of char to improve the performance.
 * - Time complexity: Let's say, user inputted a query with length L.
 *      - Find the node where the prefix ends, O(L), the worst case.
 *      - Find all complete sub-nodes, that's O(n), and Sort results by ranking score, that's O(nlog(K))
 *
 * To improve performance.
 * - Store sorted results in the node, sacrifice space to get better performance.
 * - Limit the length of query, for example, if the length is larger than 20, user may don't care about what we suggested.
 * - Cache, the rule 20% most frequent request take 80% traffic.
 *
 */
public class SystemDesign {
}
