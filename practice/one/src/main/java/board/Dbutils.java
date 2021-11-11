package board;

import java.sql.*;

public class Dbutils {
    public static Connection getCon() throws SQLException, ClassNotFoundException {
//        final String URL = "jdbc:mysql://mypj.iptime.org:3306/test01";
//        final String DBNAME = "test01";
//        final String DBPASSWORD = "test01";
        final String URL = "jdbc:mysql://localhost:3308/test";
        final String DBNAME = "root";
        final String DBPASSWORD = "koreait";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, DBNAME, DBPASSWORD);

        return con;
    }
    public static void close(Connection con, PreparedStatement ps){
        close(con,ps, null);
    }

    public static void close(Connection con, PreparedStatement ps, ResultSet rs){
        if (rs != null){
            try {
                rs.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        if(ps != null) {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(con != null) {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
