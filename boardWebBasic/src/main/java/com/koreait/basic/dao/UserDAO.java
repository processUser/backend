package com.koreait.basic.dao;

import com.koreait.basic.DbUtils;
import com.koreait.basic.user.model.LoginResult;
import com.koreait.basic.user.model.UserEntity;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    public static int join(UserEntity param){
        Connection con = null;
        PreparedStatement ps = null;
        String sql ="insert into t_user(uid, upw, nm, gender) values(?, ?, ?, ?)";
//        String sql ="insert into t_user2(uid, upw, nm, gender) values(?, ?, ?, ?)";

        try {
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

    //무조건 LoginResult 객체 주소값 리턴
    // result 값 0 : 실패, 1: 로그인 성공, 2: 아이디 없음, 3: 비밀번호 틀릭
    // result가 1이었을 때만 loginUser에 로그인 한 유저의 iuser, id, nm, gender값을 저장한 객체를 담는다.
    public static LoginResult login(UserEntity entity){
        int result = 0;
        UserEntity loginUser = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select iuser, upw, nm, gender from t_user where uid = ?";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, entity.getUid());
            rs = ps.executeQuery();
            if(rs.next()){
                String dbPw = rs.getString("upw");
                if(BCrypt.checkpw(entity.getUpw(), dbPw)){
                    // true 비밀번호 맞음.
                    loginUser = new UserEntity();
                    loginUser.setIuser(rs.getInt("iuser"));
                    loginUser.setUid(entity.getUid());
                    loginUser.setNm(rs.getString("nm"));
                    loginUser.setGender(rs.getInt("gender"));
                    result = 1;
                } else {
                    result = 3;
                }
            }else {
                result = 2;
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con,ps,rs);
        }
        return new LoginResult(result, loginUser);
    }
}
