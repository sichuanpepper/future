package com.future.experience.gugou;

import com.future.utils.DisplayUtils;

/**
 * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=668674&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%255B3046%255D%255Bvalue%255D%3D1%26searchoption%255B3046%255D%255Btype%255D%3Dradio%26orderby%3Ddateline&page=1
 * 让我写一段代码来返回一个迷宫，没了。其他基本啥也没给，不是他不给信息，而是本身就不给什么限制，自由发挥。和面试官讨论能获得好的方向，比如，Input是什么, 迷宫要怎么表示，这个迷宫有几个解等等。
 *                input: 迷宫的宽，迷宫的高，起点，终点
 *                output: 一个设计好了的迷宫
 *
 *  https://zhuanlan.zhihu.com/p/27381213
 */

public class Maze {
    public int[][] createMaze(int size) {
        int[][] maze = new int[size + 2][size + 2];
        for(int i = 1; i < maze.length; i += 2) {
            for(int j = 1; j < maze.length; j += 2) {
                maze[i][j] = 1;
            }
        }
        DisplayUtils.printTwoDimensionsArray(maze);


        return maze;
    }


    private void helper(int[][] maze, boolean[][] visited, int row, int col) {}



    public static void main(String[] args) {
        new Maze().createMaze(5);
    }
}
