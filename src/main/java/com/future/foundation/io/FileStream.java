package com.future.foundation.io;

import java.io.*;

/**
 * Created by someone on 10/24/17.
 */
public class FileStream {
    private static final String file_path = "/Users/someone/githup/future/src/main/resources/input.txt";

    public void test() {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(file_path);
            int size = is.available();
            System.out.println(size);

            for(int i = 0; i < size; i++) {
                System.out.println((char)is.read());
            }
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            InputStream is = new FileInputStream(file_path);
            int size = is.available();  //return the number of bytes
            System.out.println(size);

            for(int i = 0; i < size; i++) {
                System.out.println((char)is.read()); //return a byte.
            }
            is.close();


            is = new FileInputStream(file_path);
            int c;
            while ((c = is.read()) != -1) {
                System.out.println((char)c);  //return byte.
            }

            is.close();

            //BufferedInputStream
            is = new BufferedInputStream(new FileInputStream(file_path));  //default buffer size is 8192 bytes;
            while ((c = is.read()) != -1) {
                System.out.println((char)c);  //return byte.
            }
            is.close();

            System.out.println("============BufferedReader=================");
            //BufferedReader
            BufferedReader br = new BufferedReader(new FileReader(file_path));
            while ((c = br.read()) != -1) {
                System.out.println((char)c);  //return byte.
            }
            br.close();

            System.out.println("============BufferedReader reads lines=================");
            br = new BufferedReader(new FileReader(file_path), 1);
            String line = "";
            while ((line = br.readLine()) != null) {
                System.out.println(line.split(" ").length);
                System.out.println(line);
            }
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
