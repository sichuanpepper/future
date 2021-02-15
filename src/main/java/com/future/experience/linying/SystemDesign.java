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
 */
public class SystemDesign {
}
