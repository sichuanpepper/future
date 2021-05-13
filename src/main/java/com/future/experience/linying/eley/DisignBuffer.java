package com.future.experience.linying.eley;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

/**
 * http://tutorials.jenkov.com/java-nio/buffers.html
 *
 * A buffer is a block of memory into which you can write data and then later read again.
 *
 * A buffer has three properties:
 *  - capacity
 *  - position
 *      When you write data into buffer, you do so at a certain position, initially the position is 0.
 *      When you flip buffer from writing mode to reading mode, the position reset back to 0.
 *  - limit
 *      In the writing mode, the limit of a buffer limits of how much data you can write, it equals to the capacity.
 *      When you flip buffer from writing mode to reading mode, limit is set to write position, means how much data you can read.
 */
public class DisignBuffer {
    private int capacity;

    private int position;

    private int limit;

    private char[] data;

    public DisignBuffer(int capacity) {
        this.capacity = capacity;
        this.position = 0;
        this.limit = this.capacity;
        this.data = new char[this.capacity];
    }

    public void put(char ch) {
        data[position++] = ch;
    }

    public char get(){
        return data[position++];
    }

    //make buffer ready for read;
    public void flip() {
        this.limit = this.position;
        this.position = 0;
    }

    public boolean hasRemaining() {
        return this.position < this.limit;
    }

    public static void main(String[] args) throws Exception {
        RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
        FileChannel inChannel = aFile.getChannel();

        //create buffer with capacity of 48 bytes
        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf); //read into buffer.
        while (bytesRead != -1) {

            buf.flip();  //make buffer ready for read

            while(buf.hasRemaining()){
                System.out.print((char) buf.get()); // read 1 byte at a time
            }

            buf.clear(); //make buffer ready for writing
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }
}
