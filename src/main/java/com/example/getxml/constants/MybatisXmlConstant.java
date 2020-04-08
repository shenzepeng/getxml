package com.example.getxml.constants;

public class MybatisXmlConstant {
    /**
     * tableName 表名
     * domainObjectName 实体类名
     * column 主键列名  id
     * identity是否自增 true false
     */
    public final static String TABLES=" <table tableName=\"%s\" domainObjectName=\"%s\">\n" +
            "    <generatedKey column=\"id\" sqlStatement=\"MySql\" identity=\"true\"/>\n" +
            "</table>\n";
}
