package com.future.experience.aibiying;

import java.util.HashMap;
import java.util.Map;

/**
 * Analyze:
 * The first data structure for file system in my mind is tree.
 *
 * Created by xingfeiy on 6/13/18.
 */
public class FileSystem {
    private Map<String, Integer> pathMap = new HashMap<>();

    private Map<String, Runnable> callBackMap = new HashMap<>();

    public boolean create(String path, int value) {
        if(pathMap.containsKey(path)) return false;
        //check if parents already existed
        if(!pathMap.containsKey(path.substring(0, path.lastIndexOf("/")))) return false;
        pathMap.put(path, value);
        return true;
    }

    public boolean set(String path, int value) {
        if(!pathMap.containsKey(path)) return false;
        pathMap.put(path, value);
        while (path.length() > 0) {
            if(callBackMap.containsKey(path)) callBackMap.get(path).run();
            path = path.substring(0, path.lastIndexOf("/"));
        }
        return true;
    }

    public int get(String path) {
        return pathMap.getOrDefault(path, Integer.MIN_VALUE);
    }

    public boolean watch(String path, Runnable callback) {
        if(!pathMap.containsKey(path)) return false;
        callBackMap.put(path, callback);
        return true;
    }
}
