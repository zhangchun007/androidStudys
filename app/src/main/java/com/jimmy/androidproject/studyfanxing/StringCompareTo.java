package com.jimmy.androidproject.studyfanxing;

/**
 * @Description:
 * @Author: zhangchun
 * @CreateDate: 2022/1/19
 * @Version: 1.0
 */
class StringCompareTo implements Comparable<StringCompareTo> {

    private String mStr;

    public StringCompareTo(String string) {
        this.mStr = string;
    }

    @Override
    public boolean compareTo(StringCompareTo str) {
        if (mStr.length() > str.mStr.length()) {
            return true;
        }
        return false;
    }
}
