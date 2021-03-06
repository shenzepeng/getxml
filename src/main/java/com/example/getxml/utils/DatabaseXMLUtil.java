package com.example.getxml.utils;

import com.example.getxml.context.BottomConstant;
import com.example.getxml.context.GenHeaderContent;
import com.example.getxml.context.MybatisXmlConstant;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class DatabaseXMLUtil {
    /**
     * 连接数据库的路径
     */
    private final static String driver = "com.mysql.jdbc.Driver";
    /**
     * 数据连接
     */
    private final static String url = "jdbc:mysql://101.132.33.149:3306/saas_shop?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    /**
     * 账号
     */
    private final static String username = "root";
    /**
     * 密码
     */
    private final static String password = "123456";

    private final static String mapperUrl="com.kxg.cremb.provider.mapper";

    private final static String localJdbcJar="/Users/mac/.m2/repository/mysql/mysql-connector-java/5.1.27/mysql-connector-java-5.1.27.jar";

    private final static String commonMapper="com.kxg.cremb.provider.mapper.CommonMapper";

    private final static String pojoUrl="com.kxg.cremb.provider.pojo";
    public static Map getAllTableColumn(String driver, String url, String username, String password) throws SQLException, ClassNotFoundException {

        Map<String, Object> tableNameMap = new HashMap<>();
        //加载驱动
        Class.forName(driver);
        //获得数据库连接
        Connection connection = DriverManager.getConnection(url, username, password);
        //获得元数据
        DatabaseMetaData metaData = connection.getMetaData();
        //获得表信息
        ResultSet tables = metaData.getTables(null, null, null, new String[]{"TABLE"});
        while (tables.next()) {
            Map<String, String> columnNameMap = new HashMap<>(); //保存字段名

            //获得表名
            String table_name = tables.getString("TABLE_NAME");
            //通过表名获得所有字段名
            ResultSet columns = metaData.getColumns(null, null, table_name, "%");
            //获得所有字段名
            while (columns.next()) {
                //获得字段名
                String column_name = columns.getString("COLUMN_NAME");
                //获得字段类型
                String type_name = columns.getString("TYPE_NAME");
//获得字段注释   注意： 对于此列，SQL Server 始终会返回 Null。
                String remarks = columns.getString("REMARKS");
//   https://docs.microsoft.com/zh-cn/sql/connect/jdbc/reference/getcolumns-method-sqlserverdatabasemetadata?view=sql-server-2017
 //               System.out.println(table_name+":"+column_name+":"+remarks);
                columnNameMap.put(column_name, type_name);
            }

            tableNameMap.put(table_name, columnNameMap);
            System.out.println("当前在内存中以获取:"+tableNameMap.size()+"张表，当前表是: "+table_name);
        }

        return tableNameMap;
    }

    private static void fileWrite(String content, String filePath) {
        try {
            FileOutputStream fileOutputStream = null;
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(content.getBytes("gbk"));
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        /**
         * 逆向xml中表的配置
         */
        HashMap<String, Object> computer_mall = (HashMap<String, Object>) getAllTableColumn(driver,url,username,password);
        TreeMap<String, Object> sortTreeMap = new TreeMap<>(computer_mall);
        Set<String> tableNames = sortTreeMap.keySet();
        StringBuffer xmlList = new StringBuffer();
        System.out.println("一共有表：" + tableNames.size() + "张");
        for (String tableName : tableNames) {
            String rule = MybatisXmlConstant.TABLES;
            String toHumpString = SnackToHumpStringUtils.underlineToCamel(tableName);
            System.out.println(toHumpString);
            /**
             * 类名---直接通过表名生成的类名
             */
            String pojoClassName = SnackToHumpStringUtils.underlineToCamel(tableName);
            /**
             * 将类名转化,类名是通过表直接生成的，
             * 但是不方便开发
             * 需更换为自己想要的
             */
            String newClassName = pojoClassName.replace("Eb", "Kxg");

            String xml = String.format(rule, tableName, SnackToHumpStringUtils.underlineToCamel(newClassName));
            xmlList.append(xml);
        }
        /**
         *
         */
        GenHeaderContent genHeaderContent=new GenHeaderContent(localJdbcJar,commonMapper,driver,url,username,password,pojoUrl,mapperUrl);
        String headerContent = genHeaderContent.getHeaderContent();
        /************************************************************/
        /**
         * 逆向xml中的头
         */
        String header=headerContent;
        /*l
         * 逆向xml中表的配置
         */
        String tableContent=xmlList.toString();
        /**
         * 逆向xm;中的低
         */
        String bottom= BottomConstant.BOTTOM;

        //构建内容
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(header);
        stringBuffer.append(tableContent);
        stringBuffer.append(bottom);

        //写文件
        fileWrite(stringBuffer.toString(), "/Users/mac/Desktop/gen.xml");

    }



}
