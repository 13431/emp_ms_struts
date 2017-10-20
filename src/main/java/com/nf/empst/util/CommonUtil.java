package com.nf.empst.util;

public class CommonUtil {

    /**
     * 判断一个字符串是否为空
     *
     * @param input 输入字符串
     * @return 如果为空 true, 否则 false
     */
    public static boolean notempty(String input) {
        return input != null && !input.isEmpty();
    }

    /**
     * 不全为空
     */
    public static boolean notallempty(String... inputs) {
        if (inputs == null)
            return false;

        boolean res = false;
        for (String input : inputs) {
            if (notempty(input)) {
                res = true;
                break;
            }
        }
        return res;
    }
}
