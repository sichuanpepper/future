package com.future.experience.aibiying;

import com.future.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xingfeiy on 7/15/18.
 */
public class CIDRIPMY {
    public List<String> ipRange2CidrMy(String startIp, int range) {
        List<String> res = new ArrayList<>();
        long start =ipToLong(startIp), end = start + range - 1;
        while (start <= end) {
            //the max range can be covered by start ip
            int pos = 0;
            long tmp = start;
            while ((tmp & 1) != 1) {
                pos++;
                tmp = tmp >>> 1;
            }
            int mask = 32 - pos;
            int maskRange = 32 - (int)(Math.log(end - start + 1) / Math.log(2));
            mask = Math.max(mask, maskRange);
            res.add(longToIP(start) + "/" + mask);
            start += Math.pow(2, 32 - mask);
        }
        return res;
    }

    private long ipToLong(String ip) {
        String[] tokens = ip.split("\\.");
        long res = 0;
        res += Long.parseLong(tokens[0]) << 24;
        res += Long.parseLong(tokens[1]) << 16;
        res += Long.parseLong(tokens[2]) << 8;
        res += Long.parseLong(tokens[3]);
        return res;
    }

    private String longToIP(long lip) {
        String res = "";
        res += Long.toString(lip & 0X000000FF);
        res = Long.toString((lip >>> 8) & 0X000000FF) + "." + res;
        res = Long.toString((lip >>> 16) & 0X000000FF) + "." + res;
        res = Long.toString((lip >>> 24) & 0X000000FF) + "." + res;
        return res;
    }


    public static void main(String[] args) {
        CIDRIPMY c = new CIDRIPMY();
        //255.0.0.7/32 255.0.0.8/29 255.0.0.16/32
        DisplayUtils.printList(c.ipRange2CidrMy("255.0.0.7", 10));
    }
}
