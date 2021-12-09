package com.dao;

import com.DbUtils;

import com.user.model.LoginResult;
import com.user.model.UserEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public static LoginResult login(UserEntity entity) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select iuser, upw, nm, gender from t_user where uid = ?";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, entity.getUid());
            rs = ps.executeQuery();

//rs.getString("upw") == entity.getUpw()
            if (rs.next()) {
                UserEntity vo = new UserEntity();
                if (rs.getString("upw").equals(entity.getUpw())) {
                    vo.setIuser(rs.getInt("iuser"));
                    vo.setNm(rs.getString("nm"));
                    vo.setUid(entity.getUid());
                    return new LoginResult(1, vo);
                } else {
                    return new LoginResult(2, null);
                }


            } else {
                return new LoginResult(3, null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }

        return null;
    }

    public static int join(UserEntity entity) {

        Connection con = null;
        PreparedStatement ps = null;

        String sql = "insert into t_user (uid,upw,nm,gender) values (?,?,?,?)";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, entity.getUid());
            ps.setString(2, entity.getUpw());
            ps.setString(3, entity.getNm());
            ps.setInt(4, entity.getGender());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }

        return 0;
    }
}
