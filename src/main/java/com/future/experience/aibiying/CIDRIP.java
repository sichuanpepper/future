package com.future.experience.aibiying;

import com.future.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 Given a start IP address ip and a number of ips we need to cover n, return a representation of the range as a list
 (of smallest possible length) of CIDR blocks.

 A CIDR block is a string consisting of an IP, followed by a slash, and then the prefix length. For example: "123.45.67.89/20".
 That prefix length "20" represents the number of common prefix bits in the specified range.

 Example 1:
 Input: ip = "255.0.0.7", n = 10
 Output: ["255.0.0.7/32","255.0.0.8/29","255.0.0.16/32"]
 Explanation:
 The initial ip address, when converted to binary, looks like this (spaces added for clarity):
 255.0.0.7 -> 11111111 00000000 00000000 00000111
 The address "255.0.0.7/32" specifies all addresses with a common prefix of 32 bits to the given address,
 ie. just this one address.

 The address "255.0.0.8/29" specifies all addresses with a common prefix of 29 bits to the given address:
 255.0.0.8 -> 11111111 00000000 00000000 00001000
 Addresses with common prefix of 29 bits are:
 11111111 00000000 00000000 00001000
 11111111 00000000 00000000 00001001
 11111111 00000000 00000000 00001010
 11111111 00000000 00000000 00001011
 11111111 00000000 00000000 00001100
 11111111 00000000 00000000 00001101
 11111111 00000000 00000000 00001110
 11111111 00000000 00000000 00001111

 The address "255.0.0.16/32" specifies all addresses with a common prefix of 32 bits to the given address,
 ie. just 11111111 00000000 00000000 00010000.

 In total, the answer specifies the range of 10 ips starting with the address 255.0.0.7 .

 There were other representations, such as:
 ["255.0.0.7/32","255.0.0.8/30", "255.0.0.12/30", "255.0.0.16/32"],
 but our answer was the shortest possible.

 Also note that a representation beginning with say, "255.0.0.7/30" would be incorrect,
 because it includes addresses like 255.0.0.4 = 11111111 00000000 00000000 00000100
 that are outside the specified range.
 Note:
 - ip will be a valid IPv4 address.
 - Every implied address ip + x (for x < n) will be a valid IPv4 address.
 - n will be an integer in the range [1, 1000].

 * Created by xingfeiy on 6/25/18.
 */
public class CIDRIP {
    private long ipToLong(String strIP) {
        long[] ip = new long[4];
        String[] ipSec = strIP.split("\\.");
        for (int k = 0; k < 4; k++) {
            ip[k] = Long.valueOf(ipSec[k]);
        }
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
    }

    private String longToIP(long longIP) {
        StringBuffer sb = new StringBuffer("");
        sb.append(String.valueOf(longIP >>> 24));
        sb.append(".");
        sb.append(String.valueOf((longIP & 0x00FFFFFF) >>> 16));
        sb.append(".");
        sb.append(String.valueOf((longIP & 0x0000FFFF) >>> 8));
        sb.append(".");
        sb.append(String.valueOf(longIP & 0x000000FF));
        return sb.toString();
    }

    public List<String> ipRange2Cidr(String startIp, int range) {
        // check parameters
        long start = ipToLong(startIp);
        long end = start + range - 1;
        List<String> res = new ArrayList<>();
        while (start <= end) {
            // identify the location of first 1's from lower bit to higher bit of start IP
            // e.g. 00000001.00000001.00000001.01101100, return 4 (100)

//            long locOfFirstOne = start & (-start);
//            int curMask = 32 - (int) (Math.log(locOfFirstOne) / Math.log(2));
            int curMask = 32;
            long tmp = start;
            while ((tmp & 1) != 1) {
                tmp = tmp >> 1;
                curMask--;
            }
            // calculate how many IP addresses between the start and end
            // e.g. between 1.1.1.111 and 1.1.1.120, there are 10 IP address
            // 3 bits to represent 8 IPs, from 1.1.1.112 to 1.1.1.119 (119 - 112 + 1 = 8)
            double currRange = Math.log(end - start + 1) / Math.log(2);
//            int currRangeMask = 32 - (int) Math.floor(currRange);
            int currRangeMask = 32 - (int)currRange;

            // why max?
            // if the currRangeMask is larger than curMask
            // which means the numbers of IPs from start to end is smaller than mask range
            // so we can't use as many as bits we want to mask the start IP to avoid exceed the end IP
            // Otherwise, if currRangeMask is smaller than curMask, which means number of IPs is larger than mask range
            // in this case we can use curMask to mask as many as IPs from start we want.
            curMask = Math.max(currRangeMask, curMask);
            // Add to results
            String ip = longToIP(start);
            res.add(ip + "/" + curMask);
            // We have already included 2^(32 - curMask) numbers of IP into result
            // So the next roundUp start must insert that number
            start += Math.pow(2, (32 - curMask));
        }
        return res;
    }

    /**
     * Analyze:
     * We are give a start ip, and a range, and try to find the CIDRs that can cover it.
     * CIDR: xxx.xxx.xxx.xxx/mask
     * mask = 32, cover one IP
     * mask = 31, cover 2 IPs
     * mask = 32, cover 4 IPs
     * ...
     * - A IP address can be cover to a binary
     * - Then we can find the first 1's from lower position, then we can know what the largest IP range that this CIDR can cover.
     *  - The largest range = start + range, that's perfect CIDR.
     *  - The largest range > start + range, that's not good
     * @param startIp
     * @param range
     * @return
     */
    public List<String> ipRange2CidrMy(String startIp, int range) {
        List<String> res = new ArrayList<>();
        long start = ipToLong(startIp);
        long end = start + range - 1;
        while (start <= end) {
            // what the range could be covered by CIDR of start
            int startMask = 32;
            long tmp = start;
            while ((tmp & 1) != 1) {
                tmp = tmp >> 1;
                startMask--;
            }

            int rangeMask = (int)(32 - Math.log(end - start + 1) / Math.log(2));

            //smaller mask, bigger range, here we have two ranges, one range is the start can cover, and other range is
            // a possible sub range between start and end. choose smaller range.
            int mask = Math.max(startMask, rangeMask);
            //find one res
            res.add(longToIP(start) + "/" + mask);
            //update start since the above CIDR already covered some IPs
            start += Math.pow(2, (32 - mask));
        }
        return res;
    }



    public static void main(String[] args) {
        CIDRIP c = new CIDRIP();
        DisplayUtils.printList(c.ipRange2Cidr("255.0.0.7", 10));
        DisplayUtils.printList(c.ipRange2CidrMy("255.0.0.7", 10));
    }
}
