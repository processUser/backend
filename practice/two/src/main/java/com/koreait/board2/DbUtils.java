package com.koreait.board2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbUtils {
    public static Connection getCon() throws Exception {
//        final String URL = "jdbc:mysql://localhost:3308/board2";
//        final String DBNAME = "root";
//        final String DBPASSWORD = "koreait";
        final String URL = "jdbc:mysql://mypj.iptime.org:3306/test01";
        final String DBNAME = "test01";
        final String DBPASSWORD = "test01";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, DBNAME, DBPASSWORD);
        return con;
    }

    public static void close(Connection con, PreparedStatement ps) {
        close(con, ps, null);
    }
    public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
        if(rs != null) {
            try { rs.close(); }
            catch (Exception e) { e.printStackTrace(); }
        }
        if(ps != null) {
            try { ps.close(); }
            catch (Exception e) { e.printStackTrace(); }
        }
        if(con != null) {
            try { con.close(); }
            catch (Exception e) { e.printStackTrace(); }
        }
    }
}
