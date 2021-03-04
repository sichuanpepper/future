package com.future.experience.fsbk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/
 *
 */
public class RemoveSubFoldersfromtheFilesystem {
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);
        List<String> res = new ArrayList<>();
        res.add(folder[0]);
        for(int i = 1; i < folder.length; i++) {
            String pre = res.get(res.size() - 1);
            if(!folder[i].startsWith(pre + "/")) {  //be careful, why we have to add "/", case /a/b and /a/bc/d
                res.add(folder[i]);
            }

        }
        return res;
    }
}
