package com.future.experience.fsbk;

/**
 * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=698494&page=1&ex
 *
 * 设计听音乐的top 10 每一个用户有一个最爱的10首音乐
 *
 * 设计1，设计live comment
 *
 * 设计2，按经纬度query附近的Points of Interest
 * (我当时面的时候就是spatial index in single db + geohash作db sharding。一些有意思的问题是怎么解决hotspot，怎么解决large range query，怎么搞caching。)
 *
 * Notify users there are new feeds. Cover the cases that user is online, offline or facebook is running in background
 *
 *
 这段时间面试了脸书的码工职位，整理了一下最近地里和朋友那里打听出来的系统设计题目，分享出来换大米，换大米~~~
 - Push notification
 - Search status，或者叫twitter search，一般要求real time，仅限text post。可以参考 https://blog.twitter.com/enginee ... rch-experience.html
 - Aggregation system，一般会考虑到fast和slow两种cases
 - Design Yelp，经典题目，quadtree或者grid，geohash我自己没多看，觉着重点不在这里
 - Translation syste，两种思路，一个是google translate这种，你可以assume已经有一个现成可用的translation service，然后你要设计一个系统满足三高。另外一个思路可以借鉴一下airbnb的翻译系统 https://medium.com/airbnb-engine ... atform-45cf0104b63c
 - News feed
 - Design Netflix
 - i18n，参见上面说的airbnb的翻译系统
 - Collaborative doc editing，就是设计个google doc
 - Subscription system，比如说youtube的subscription
 - Hashtag trend，类似于topK，YouTube上有个视频讲的挺好 https://www.youtube.com/watch?v=kx-XDoPjoHw&t=53s

 system design面得烂，非常规题，设计一个html parser， 要跑在Botnet上，怪自己没听好题目，botnet关键词到喷了一半才想起来。。。

 system design：design search status，要求尽量做到realtime。只考虑text的post。之前没准备过原题，但是感觉跟twitter search差不多，重点谈论了如何shard index。

 Design: realtime aggregation system。假设我们要统计汇总每个广告的点击量以及其他的数据。输入是从client来的很多log，每个log包括(ad_id, user_id)，输出是给dashboard提供汇总数据。一些基本的要求
 存储2年的数据
 每天会有200B个log，并且会有peak
 有50M不同的ad_id
 可以接受30s的latency


 已知一个点和半径，设计一个service返回此点和半径构成的圆内的所有点。


高频算法题
 https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=698494&page=1&ex

 */
public class SystemDesign {
    /**
     * Live Aggregators
     */
}
