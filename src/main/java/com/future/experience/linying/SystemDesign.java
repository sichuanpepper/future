package com.future.experience.linying;

/**
 * complex system:
 * 基本上叫你设计inverted index。只用管存储和读取不用设计写入。嘴壶基本就是聊各种patition的方法说了consistent hash
 * 外加 prefix based partition。聊了一下virtual node vs autosplit vs move node啥的来处理hot spot。
 * 最后聊到按照inverted index存储的文件id来parition。 这样每个query都变成全partition都要被扫一遍。
 * 简直就是hashmap按照value set 来检索。我都不知道这样的话还干嘛要建倒排索引。
 *
 * 一轮system要我去design columnar db。
 *
 * 设计一个类似微博热搜的系统
 *
 * https://www.youtube.com/watch?v=kx-XDoPjoHw
 *
 * 系统设计，assuming you have a library to store lots of books, design a system to support two queries: "and" to return
 * the collection of book ids which contain word1 and word2; "not" to return the book ids which don't contain a specific word
 *
 *
 * Technical communication。Assume面试官是一个入职你组里的新人，向他介绍一个你们组的service/project。需要注意的是你要assume
 * 这个面试官Know nothing about your team and your product as well as the component(用到的技术比如AWS web service, KAFKA这类)。条理清楚地表达好就可以。
 */
public class SystemDesign {
}
