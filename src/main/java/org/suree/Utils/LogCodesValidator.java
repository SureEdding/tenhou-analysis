package org.suree.Utils;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sure on 8/14/16.
 */

@Service
public class LogCodesValidator {


    int[] table = {
            22136, 52719, 55146, 42104,
            59591, 46934, 9248, 28891,
            49597, 52974, 62844, 4015,
            18311, 50730, 43056, 17939,
            64838, 38145, 27008, 39128,
            35652, 63407, 65535, 23473,
            35164, 55230, 27536, 4386,
            64920, 29075, 42617, 17294,
            18868, 2081
    };


    public List<String> validate(List<String> logCodes) {
        List<String> codes = new ArrayList<String>();
        for (String logcode : logCodes) {
            String code = tenhouHash(logcode);
            if (!code.equals("")) {
                codes.add(code);
            }
        }
        return codes;
    }
    public String tenhouHash(String logCode) {
        //2016081723gm-0029-0000-xd203ff50b119
        try {
            String[] splits = logCode.split("-");
            if (splits.length != 4) {
                return "";
            }
            if (splits[3].startsWith("x")) {
                String code = splits[3].substring(1);
                Integer a = hex2decimal(code.substring(0,4));
                Integer b = hex2decimal(code.substring(4,8));
                Integer c = hex2decimal(code.substring(8,12));
                Integer index = 0;
                if (splits[0].compareTo("2010041111gm" )>0) {
                    Integer x = Integer.parseInt("3" + splits[0].substring(4,10));
                    Integer y = Integer.parseInt(splits[0].substring(9,10));
                    index = x % (33 - y);
                }
                Integer first = (a ^ b ^ table[index]) & 0xffff;
                Integer second = (b ^ c ^ table[index] ^ table[index + 1]) & 0xffff;
                return splits[0] + "-" + splits[1] +"-"+ splits[2] +"-"+ decimal2hex(first) + decimal2hex(second);
            }
            return logCode;
        } catch (Exception e) {
            return "";
        }
    }
    public static int hex2decimal(String s) {
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        int val = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = 16*val + d;
        }
        return val;
    }
    public static String decimal2hex(int d) {
        String digits = "0123456789abcdef";
        if (d <= 0) return "0";
        int base = 16;   // flexible to change in any base under 16
        String hex = "";
        while (d > 0) {
            int digit = d % base;              // rightmost digit
            hex = digits.charAt(digit) + hex;  // string concatenation
            d = d / base;
        }
        for (;hex.length() < 4;){
            hex = "0"+hex;
        }
        return hex;
    }
}
