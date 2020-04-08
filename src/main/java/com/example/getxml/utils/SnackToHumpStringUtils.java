package com.example.getxml.utils;

import org.springframework.util.StringUtils;

public class SnackToHumpStringUtils {
    private static final String UNDERLINE="_";
    /**
     * 下划线 转 驼峰
     * @param param
     * @return
     */
    public static String underlineToCamel(String param){
        if (StringUtils.isEmpty(param)){
            throw new RuntimeException("字符串不能为空");
        }
        String trim = param.trim();
        String[] split = trim.split(UNDERLINE);
        StringBuffer stringBuffer=new StringBuffer();
        for (String s : split) {
            char[] chars = s.toCharArray();
            if (chars.length==0){
                throw new RuntimeException("分割出现异常");
            }
            chars[0]=Character.toUpperCase(chars[0]);
            String string=new String(chars);
            stringBuffer.append(string);
        }
        return stringBuffer.toString();
    }


}
