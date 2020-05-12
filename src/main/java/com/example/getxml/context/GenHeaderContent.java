package com.example.getxml.context;

import lombok.Data;

/**
 * 逆向生成xml的header
 */
@Data
public class GenHeaderContent {
    private String jdbcLocationJarPath;
    private String commonMapper;
    private String jdbcDriver;
    private String jdbcConnectionURL;
    private String mysqlUserName;
    private String mysqlPassword;
    private String pojoPath;
    private String mapperPath;

    public String  getHeaderContent() {
         String content="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE generatorConfiguration\n" +
                "        PUBLIC \"-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN\"\n" +
                "        \"http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd\">\n" +
                "<generatorConfiguration>\n" +
                "    <!--导入属性配置 -->\n" +
                "    <!--<properties resource=\"generator.properties\"></properties>-->\n" +
                "\n" +
                "    <!--指定特定数据库的jdbc驱动jar包的位置 -->\n" +
                "    <classPathEntry\n" +
                "            location=\""+this.jdbcLocationJarPath+"\"/>\n" +
                "\n" +
                "    <context id=\"MySql\" targetRuntime=\"MyBatis3Simple\" defaultModelType=\"flat\">\n" +
                "       <!--公共mapper要提前手动建好-->\n" +
                "        <plugin type=\"tk.mybatis.mapper.generator.MapperPlugin\">\n" +
                "            <property name=\"mappers\" value=\""+this.commonMapper+"\"/>\n" +
                "            <!-- caseSensitive默认false，当数据库表名区分大小写时，可以将该属性设置为true -->\n" +
                "            <property name=\"caseSensitive\" value=\"true\"/>\n" +
                "        </plugin>\n" +
                "        <!-- optional，旨在创建class时，对注释进行控制 -->\n" +
                "        <commentGenerator>\n" +
                "            <property name=\"suppressDate\" value=\"true\"/>\n" +
                "            <property name=\"suppressAllComments\" value=\"true\"/>\n" +
                "        </commentGenerator>\n" +
                "        <!--jdbc的数据库连接 -->\n" +
                "        <jdbcConnection driverClass=\""+this.jdbcDriver+"\"\n" +
                "                        connectionURL=\""+this.jdbcConnectionURL+"?useSSL=false\"\n" +
                "                        userId=\""+this.mysqlUserName+"\"\n" +
                "                        password=\""+this.mysqlPassword+"\">\n" +
                "        </jdbcConnection>\n" +
                "        <!-- 非必需，类型处理器，在数据库类型和java类型之间的转换控制-->\n" +
                "        <javaTypeResolver>\n" +
                "            <property name=\"forceBigDecimals\" value=\"false\"/>\n" +
                "        </javaTypeResolver>\n" +
                "        <!-- Model模型生成器,用来生成含有主键key的类，记录类 以及查询Example类\n" +
                "            targetPackage     指定生成的model生成所在的包名\n" +
                "            targetProject     指定在该项目下所在的路径\n" +
                "        -->\n" +
                "        <javaModelGenerator targetPackage=\""+this.pojoPath+"\" targetProject=\"src/main/java\">\n" +
                "            <!-- 是否对model添加 构造函数 -->\n" +
                "            <!--<property name=\"constructorBased\" value=\"true\"/>-->\n" +
                "            <!-- 是否允许子包，即targetPackage.schemaName.tableName -->\n" +
                "            <property name=\"enableSubPackages\" value=\"false\"/>\n" +
                "            <!-- 建立的Model对象是否 不可改变  即生成的Model对象不会有 setter方法，只有构造方法 -->\n" +
                "            <property name=\"immutable\" value=\"false\"/>\n" +
                "            <!-- 给Model添加一个父类 -->\n" +
                "            <!--<property name=\"rootClass\" value=\"com.\"/>-->\n" +
                "            <!-- 是否对类CHAR类型的列的数据进行trim操作 -->\n" +
                "            <property name=\"trimStrings\" value=\"true\"/>\n" +
                "        </javaModelGenerator>\n" +
                "        <sqlMapGenerator targetPackage=\"mapper\" targetProject=\"src/main/resources\"/>\n" +
                "        <javaClientGenerator targetPackage=\""+this.mapperPath+"\" targetProject=\"src/main/java\"\n" +
                "                             type=\"XMLMAPPER\">\n" +
                "            <!--<property name=\"enableSubPackages\" value=\"\"/>-->\n" +
                "            <!--\n" +
                "                    定义Maper.java 源代码中的ByExample() 方法的可视性，可选的值有：\n" +
                "                    public;\n" +
                "                    private;\n" +
                "                    protected;\n" +
                "                    default\n" +
                "                    注意：如果 targetRuntime=\"MyBatis3\",此参数被忽略\n" +
                "             -->\n" +
                "            <!--<property name=\"exampleMethodVisibility\" value=\"\"/>-->\n" +
                "            <!--\n" +
                "                  方法名计数器\n" +
                "              Important note: this property is ignored if the target runtime is MyBatis3.\n" +
                "             -->\n" +
                "            <!--<property name=\"methodNameCalculator\" value=\"\"/>-->\n" +
                "            <!--\n" +
                "                  为生成的接口添加父接口\n" +
                "             -->\n" +
                "        </javaClientGenerator>";
            return content;
    }



    public static void main(String[] args) {
        GenHeaderContent headerContent=new GenHeaderContent("fuck","fuck","fuck","fuck","fuck","fuck","fuck","fuck");
        System.out.println(headerContent.getHeaderContent());
        System.out.println(headerContent.jdbcLocationJarPath);
    }

    public GenHeaderContent(String jdbcLocationJarPath, String commonMapper, String jdbcDriver, String jdbcConnectionURL, String mysqlUserName, String mysqlPassword, String pojoPath, String mapperPath) {
        this.jdbcLocationJarPath = jdbcLocationJarPath;
        this.commonMapper = commonMapper;
        this.jdbcDriver = jdbcDriver;
        this.jdbcConnectionURL = jdbcConnectionURL;
        this.mysqlUserName = mysqlUserName;
        this.mysqlPassword = mysqlPassword;
        this.pojoPath = pojoPath;
        this.mapperPath = mapperPath;
    }
}
