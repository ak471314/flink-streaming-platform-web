package com.flink.streaming.sql.validation.test;


import com.flink.streaming.sql.validation.SqlValidation;

import java.util.ArrayList;
import org.junit.Test;

import java.util.List;

/**
 * @author zhuhuipei
 * @Description:
 * @date 2021/1/17
 * @time 22:30
 */
public class TestSqlValidation {

    @Test
    public void checkSql(){

        List<String> list= new ArrayList<>();
        list.add("CREATE view print_table AS\n" +
                " select count(f0) AS c from source_table;");
        list.add("CREATE TABLE print_table2 (\n" +
                " c BIGINT\n" +
                ") WITH (\n" +
                " 'connector' = 'print'\n" +
                ");");
        list.add(" CREATE TABLE source_table (\n" +
                " f0 INT\n" +
                ") WITH (\n" +
                " 'connector' = 'datagen',\n" +
                " 'rows-per-second'='5'\n" +
                ");");
        list.add("INSERT INTO print_table2 select f0 FROM print_table;");
        SqlValidation.preCheckSql(list);
    }
}
