package com.future.experience.instacart;

/**
 * https://www.1point3acres.com/bbs/search.php?mod=forum&searchid=1350&orderby=lastpost&ascdesc=desc&searchsubmit=yes&page=3
 *
 *
 * Coding:
 * 題目都是老題了，一輪passowrd decoder, 另一輪是公式那題，自己面試前稍微練習一下就可以輕鬆應付
 * 難的是要怎麼不一次pass all test cases, 然後裝模作樣，假裝思考，debug
 * 兩輪做完都還剩二十幾分鐘，就讓我問問題了
 *
 * System Design:
 * 也是老題payment verification API. 我覺得每個面試官帶的方向都不太一樣，一個takeaway 就是自己不了解的領域千萬別自己帶出來。
 * 我過程中異想天開加了一個fraud service, dwh, ETL, 都只是看過但沒深入研究的東西，結果面試官追著問怎麼實現，data flow等等，回答地有點抖。
 *
 * Bar raiser/BQ:
 * 問了
 * - challenging project
 * - experience missing deadline
 * - non technical challenges
 * - experience dealing with stress from work
 * - why instacart
 * - why should we hire you
 * 各項都追問地很細
 *
 * 整體的面試體驗很好，每個面試官都很認真回答我的問題，希望快快出offer! 求個米
 *
 * =====================================
 * 1. System design: design a system for shopper credit card payment request. 这里就shopper其实就是司机，
 * 然后让从api到后端的架构设计一下怎么接受第三方service的payment request， 然后怎么验证这个request，然后画diagram然后解决各种follow up问题。
 * 2. Algorithm，经典的三手牌问题，地里一搜就有。
 * 3. Algorithm，地里经典的找密码问题，一样在其他帖子里都很详细了，可以自己搜一下。
 * 4. Behavoir。Behavoir的面试真的是又臭又长。。整一个小时。Facebook其实也就是30分钟，剩下还会做一道题。这家真的就是整整谈一个小时。所以至少准备好2 3个可以讲的项目。
 *
 *
 * 第一轮，国人小哥，从文件读file，print string，google了一下怎么读file，三问秒了
 * 第二轮，白人小哥，system design， shopper pickup那个原题，半小时把面试官所有问题搞定了，问无可问，提前结束了，
 * 第三轮，算法，国人小姐姐，方程式原题，三问秒了
 * 第四轮，烙印 director，bar raiser，全程高冷，爱理不理的
 *
 * 最后的反馈是，其他轮很好，第一轮反馈我对build in io method不够熟悉，日了。。。
 *
 *
 *
 1. Coding。面经解密码题，一共三小问。第一问是给一个坐标，然后给一个字符matrix，找出对应的字符。第二题是给多个这样的坐标和对应的字符matrix，
 然后告诉你每个matrix里找到的字符对应password里的某一位，找出完整的password。第三问就是有多个password，找出第一个password，其实就是改变一下loop exit的条件。注意的是输入是从一个text file读入，所以记得熟悉一下如果读取文件，当然也可以现场查。

 2. BQ + Design。本来以为只是单纯的BQ，没想到还牵涉到了一点Design。问你如果你是instacart的第一名engineer，让你现在从头设计instacart，
 主要有哪些大的模块需要考虑。现在要onboard一个新的merchant（比如costco），然后有什么最快的方法可以让这个merchant的grocery menu出现在instacart上，
 假如如果按正常流程由merchant提供的话会需要很久。答案就是直接去merchant的网站上scrap然后存下来。接下来就是正常的BQ，最近在工作上有收到什么negative的feedback，
 如果和团队里的junior和senior engineer合作，合作方法有什么不同，有没有conflict等等。

 3. Coding。Card game原题，就是找三张牌能组成一个set。这是一个真实存在的游戏，就叫Set，可以google一下。这里不需要考虑时间复杂度优化什么的，只要能做出来就行。

 4. Design。还是经典原题，设计一个payment system，当有shopper刷卡的时候，如何判断是不是要通过这个刷卡请求。虽然面试问的是设计这个小的系统，
 但我觉得面试官看中的是这个小系统在整个大的系统里的位置，以及如何和大的系统里的其他部分进行沟通。我的建议是按照整个business flow，用breadth first search的方法，从用户下单开始，
 简单讲讲都会经历哪些component，最后是怎么到达payment system，payment system又需要和哪些其他的component进行交流。面试官会按照自己的喜好来让你深入聊聊某些具体的部分，
 有可能具体聊的部分不一定是payment system本身，而是一个和payment system有联系的其他组件。总之就是先笼统的讲个bigger picture，然后跟着面试官的思路来深入，切记不要一下子就钻的太深。


 https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=742300&highlight=instacart
 https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=748435&highlight=instacart
 https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=685427&highlight=instacart

 O一共4轮

 - project background
 - coding * 2
   -  一轮太简了题目我忘了,另外一轮是给一组等式要求可以求解任意变量的值:
   - T1 = T2 + T3
   - T2 = T3
   - T3 = 5

 - System design: instacart shopper payment system. 面试官比较关心怎么处理duplicate payment. 怎么保证SLO 1s RTT 99.9%.  我提出来他不应该set RTT, 因为你没法控制网络延迟.不过面试官好像不同意.

 https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=615124&highlight=instacart
 https://leetcode.com/discuss/interview-question/554340/Instacart-or-Onsite-or-Create-Card-Hands


 第一轮：Diving into previous projects and behavior questions (tell me a project you're really proud of/tell me when you were persuaded by someone else/tell me when the resource was limited but you made it through).
 第二轮：系统设计 如何detect fraud by shoppers.
 午饭
 第三轮：coding。Get a password given input via STDIN.
 第四轮：coding。Return a valid card hand given inpout via STDIN.

 两轮coding都不是典型的LC算法题，算法本身几乎没难度，感觉考察的更多是communication和coding style。面试前可以稍微花点时间熟悉下如何从STDIN读多行的input以及basic string parsing。

 1. Coding - 给一个目标字符串，从一个段text里面找到，并且返回index。follow up - 目标字符串可以允许通配符*，代表0或者多个任意字符。比如"*A", 从文档“CDFGAGB”，返回0。比如"A**B"，返回4。用递归很好解。
 2. Coding - 解密一段密文，参照这个 - https://www.1point3acres.com/bbs ... read&tid=667022
 3. System Design - Instacart shopper payment accept/reject，参照这个 - https://www.1point3acres.com/bbs ... read&tid=662337



 https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=723258&highlight=Instacart


 */
public class readme {
}
