package com.future.foundation.game;

/**
 * Created by xingfeiy on 8/1/18.
 */
public class MazeGames {
    /**
     * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down,
     * left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

     Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

     The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders
     of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

     * @param maze
     * @param start
     * @param dest
     * @return
     */
    public boolean game1(int[][] maze, int[] start, int[] dest) {
        return helper1(maze, new boolean[maze.length][maze[0].length], start, dest, 0);
    }

    /**
     *
     * @param maze
     * @param visited
     * @param curPos
     * @param dest
     * @param comeFrom 1->left,2->right,3->up,4->down
     * @return
     */
    private boolean helper1(int[][] maze, boolean[][] visited, int[] curPos, int[] dest, int comeFrom) {
        if(curPos[0] < 0 || curPos[0] >= maze.length || curPos[1] < 0 || curPos[1] >= maze[0].length) return false;
        if(maze[curPos[0]][curPos[1]] == 1) return false;
        if(curPos[0] == dest[0] && curPos[1] == dest[1]) {
            if(comeFrom == 1 && (curPos[1] == maze[0].length - 1 || maze[curPos[0]][curPos[1]+1] == 1)) return true;
            if(comeFrom == 2 && (curPos[1] == 0 || maze[curPos[0]][curPos[1] - 1] == 1)) return true;
            if(comeFrom == 3 && (curPos[0] == maze.length - 1 || maze[curPos[0] + 1][curPos[1]] == 1)) return true;
            if(comeFrom == 4 && (curPos[0] == 0 || maze[curPos[0] - 1][curPos[1]] == 1)) return true;
            return false;
        }
        if(visited[curPos[0]][curPos[1]]) return false;
        visited[curPos[0]][curPos[1]] = true;
        if(helper1(maze, visited, new int[]{curPos[0], curPos[1] + 1}, dest, 1)) return true;
        if(helper1(maze, visited, new int[]{curPos[0], curPos[1] - 1}, dest, 2)) return true;
        if(helper1(maze, visited, new int[]{curPos[0] + 1, curPos[1]}, dest, 3)) return true;
        if(helper1(maze, visited, new int[]{curPos[0] - 1, curPos[1]}, dest, 4)) return true;
        visited[curPos[0]][curPos[1]] = false;
        return false;
    }


    public static void main(String[] args) {
        MazeGames m = new MazeGames();

        int[][] maze = new int[5][];
        maze[0] = new int[]{0, 0, 1, 0, 0};
        maze[1] = new int[]{0, 0, 0, 0, 0};
        maze[2] = new int[]{0, 0, 0, 1, 0};
        maze[3] = new int[]{1, 1, 0, 1, 1};
        maze[4] = new int[]{0, 0, 0, 0, 0};

        System.out.println(m.game1(maze, new int[]{0, 4}, new int[]{4, 4}));
        System.out.println(m.game1(maze, new int[]{0, 4}, new int[]{3, 2}));
        System.out.println(m.game1(maze, new int[]{4, 4}, new int[]{0, 4}));
        System.out.println(m.game1(maze, new int[]{0, 1}, new int[]{2, 2}));
    }
}
