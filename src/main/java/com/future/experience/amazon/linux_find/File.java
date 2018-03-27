package com.future.experience.amazon.linux_find;

/**
 * Created by xingfeiy on 3/26/18.
 */
public class File extends Path {
    @Override
    public boolean isFile() {
        return true;
    }

    @Override
    public boolean isDir() {
        return false;
    }
}
