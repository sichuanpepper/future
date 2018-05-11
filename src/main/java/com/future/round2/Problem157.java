package com.future.round2;

/**
 *
 The API: int read4(char *buf) reads 4 characters at a time from a file.
 The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
 By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
 Note: The read function will only be called once for each test case.

 * Created by xingfeiy on 5/11/18.
 */
public class Problem157 {
    private int read4(char[] buf) {
        return 0;
    }

    /**
     * The problem is read n characters into buf, and API read4 will read 4 characters each time and write it into buf.
     * - there may not have n characters in file.
     * @param buf
     * @param n
     * @return
     */
    public int read(char[] buf, int n) {
        if(buf == null || n < 1) return 0;
        int curLength = 0;
        char[] tmp = new char[4];
        int index = 0;
        while (curLength + 4 <= n) {
            int get = read4(tmp);
            for(int i = 0; i < get; i++) buf[index++] = tmp[i];
            //if there's no n characters in file, return the actually number we read.
            if(get < 4) return curLength + get;
            curLength += get;
        }
        //
        int get = read4(tmp);
        for(int i = 0; i < Math.min(get, n - curLength); i++) buf[index++] = tmp[i];
        return curLength + Math.min(get, n - curLength);
    }

    public int read2(char[] buf, int n) {
        if(buf == null || n < 1) return 0;
        int curLength = 0, index = 0;
        boolean eof = false;
        char[] tmpBuf = new char[4];
        //break loop if reached end of file or got enough characters.
        while (!eof && curLength < n) {
            int count = read4(tmpBuf);
            //count < 4, reached end of file
            if(count < 4) eof = true;

            //copy the characters in tmp into buf
            // - the tmp may has more characters than required
            count = Math.min(count, n - curLength);
            for(int i = 0; i < count; i++) buf[index++] = tmpBuf[i];
            curLength += count;
        }
        return curLength;
    }
}
