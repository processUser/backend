package com.koreait.board2.user;

import com.koreait.board2.DbUtils;
import com.koreait.board2.model.UserVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    //
    public static int insUser(UserVO param) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "insert into t_user(uid, upw, nm, gender) values (?, ?, ?, ?);";
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, param.getUid());
            ps.setString(2, param.getUpw());
            ps.setString(3, param.getNm());
            ps.setInt(4, param.getGender());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }
    //
    public static UserVO selUser(UserVO param) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from t_user where uid = ?;";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, param.getUid());
            rs = ps.executeQuery();

            if (rs.next()){
                String upw = rs.getString("upw");
                UserVO vo = new UserVO();
                if (upw.equals(param.getUpw())) {
                    vo.setIuser(rs.getInt("iuser"));
                    vo.setUid(rs.getString("uid"));
                    vo.setNm(rs.getString("nm"));
                    return vo;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return null;
    }
}
