package com.future.experience.amazon.linux_find;

/**
 * Created by xingfeiy on 3/26/18.
 */
public abstract class Path {
    private String name = "";

    private String curAbsPath = "";

    public abstract boolean isFile();

    public abstract boolean isDir();

    private String other = "";
}
