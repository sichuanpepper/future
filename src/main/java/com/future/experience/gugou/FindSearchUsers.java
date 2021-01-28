package com.future.experience.gugou;

import com.future.utils.Point;

import java.util.*;

/**
 * 找出所有可能的第n个搜索用户
 * 给出了一组用户登录时间段
 * u1: [0, 100], u2: [10, 20], u3: [30, 50]
 * [0,100]的意思指user1的在线时间是[0, 100]，但他可以在0到100任意一个时间提交搜索请求，同理user2和user3
 * 让你找出所有可能的第n个搜索用户
 *
 * 1st：u1, u2
 * 2nd:  u1, u2, u3
 * 3rd: u1, u3
 *
 * 这个题刚开始没看懂，不知道怎么来的，后来面试官解释说想象成u1在[0,100]里面随机时间进行搜索，u2在[10,20]的范围随机时间进行搜索，这样第一个搜索用户只可能是u1和u2
 * （u3不可能，因为u3的开始时间大于u2的结束时间，所以u3必须在u2之后开始搜索，因此u3不可能是第一个搜索用户。我事后回想起来这个题的时候还把我自己给绕进去了。。。）
 * 同理，第二个搜索用户可能是u1, u2, u3
 * 第三个搜索用户只可能是u1和u3, 之所以u2不在list里面因为u2结束时间小于u3的开始时间，所以u3开始的时候u2必然已经结束
 */
public class FindSearchUsers {
    private class PointNode{
        public int time;

        public int userId;

        public boolean isStart;

        public PointNode(int time, int userId, boolean isStart) {
            this.time = time;
            this.userId = userId;
            this.isStart = isStart;
        }

        public int getTime() {
            return time;
        }

        @Override
        public String toString() {
            return "PointNode{" +
                    "time=" + time +
                    ", userId=" + userId +
                    ", isStart=" + isStart +
                    '}';
        }
    }
    /**
     * given a time frames represent as 2D array where the index of row is the user id, each row contains 2 column, start time and end time.
     * @param timeFrames
     * @return
     */
    public Map<Integer, Set<Integer>> solution(int[][] timeFrames) {
        List<PointNode> pointNodes = new ArrayList<>();
        for(int i = 0; i < timeFrames.length; i++) {
            int[] time = timeFrames[i];
            pointNodes.add(new PointNode(time[0], i, true));
            pointNodes.add(new PointNode(time[1], i, false));
        }

        //key: index of pos, value: possible users.
        Map<Integer, Set<Integer>> resMap = new HashMap<>();
        Collections.sort(pointNodes, Comparator.comparingInt(PointNode::getTime));
        Set<Integer> curUsers = new HashSet<>();

        int startPos = 1;
        for(PointNode node : pointNodes) {
            if(node.isStart) {
                curUsers.add(node.userId);
            } else {
                for(int user : curUsers) {
                    for(int i = 0; i < curUsers.size(); i++) {
                        Set<Integer> userSet = resMap.getOrDefault(i + startPos, new HashSet<>());
                        userSet.add(user);
                        resMap.put(i + startPos, userSet);
                    }
                }
                startPos++;
                curUsers.remove(node.userId);
            }
        }
        return resMap;

    }


    public static void main(String[] args) {
        FindSearchUsers searchUsers = new FindSearchUsers();
        int[][] timeFrames = new int[][]{new int[]{0, 100}, new int[]{10, 20}, new int[]{50, 80}};
        System.out.println(searchUsers.solution(timeFrames));
    }
}
