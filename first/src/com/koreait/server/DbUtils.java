package com.koreait.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbUtils {
    public static void main(String[] args) { // 테스트용도.
        DbUtils dbUtils = DbUtils.getInstance();
        dbUtils.getCon();
    }
    public static DbUtils dbUtils;

    private DbUtils() {}

    public static DbUtils getInstance() {
        if(dbUtils == null) {
            dbUtils = new DbUtils();
        }
        return dbUtils;
    }

    public Connection getCon() {
        final String URL = "jdbc:mysql://mypj.iptime.org:3306/test01";
        final String USER_NAME = "test01";
        final String USER_PW = "test01";
        Connection con = null;


        try {
            con = DriverManager.getConnection(URL, USER_NAME, USER_PW);
            System.out.println("DB 연결 성공!");
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("DB 연결 실패!");
        }

        return con;
    }

    public void close(Connection con, PreparedStatement ps){
        close(con, ps, null);
    }

    public void close(Connection con, PreparedStatement ps, ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        if (con != null) {
            try {
                con.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
