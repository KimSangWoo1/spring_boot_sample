package com.plantynet.common.util.lang;

//import org.apache.commons.lang.StringUtils;

/**
 * StringUtil
 * 
 */
public final class StringUtil
{
    
    private StringUtil()
    {
        throw new AssertionError();
    }
    
    /**
     * 문자열의 Empty or Null 체크
     * @param str
     * @return
     */
    public static boolean isEmptyorNot(String str)
    {
        //return (str == null || str.trim().equals(""));
        //수정
        return (str == null || str.length() == 0);
    }
}
