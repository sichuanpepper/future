package com.future.round2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * It's the follow up question of problem 157.
 * In problem 157, we just call function read once, but this problem, it's possible to call read function many times.
 *
 * Created by xingfeiy on 5/11/18.
 */
public class Problem158 {
    /**
     * Analyze:
     * Think about what's the difference between one call and many calls.
     * Since the read4 API will read 4 characters, but each call may not consume all 4 characters at end.
     *
     * @param buf
     * @param n
     * @return
     */
    private Queue<Character> localBuf = new LinkedList<>();

    public int read(char[] buf, int n) {
        if(buf == null || n < 1) return 0;
        int curLength = 0;
        char[] tmpBuf = new char[4];
        //consume local buf first
        while (!localBuf.isEmpty() && curLength < n) buf[curLength++] = localBuf.poll();
        //need more
        boolean eof = false;
        while (!eof && curLength < n) {
            int count = read4(tmpBuf);
            //if the count less than 4 that means already reached the end of file.
            if(count < 4) eof = true;
            //compute the real insertion
            int insert = Math.min(count, n - curLength);
            for(int i = 0; i < insert; i++) buf[curLength++] = tmpBuf[i];
            while (insert < count) localBuf.offer(tmpBuf[insert++]);
        }
        return Math.min(curLength, n);
    }

    private int read4(char[] buf) {
        return 0;
    }
}
