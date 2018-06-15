package com.future.experience.yama.linux_find;

/**
 * Created by xingfeiy on 3/26/18.
 */
public class Directory extends Path {
    @Override
    public boolean isFile() {
        return false;
    }

    @Override
    public boolean isDir() {
        return true;
    }
}
