package com.koreait.basic.dao;

import com.koreait.basic.DbUtils;
import com.koreait.basic.board.model.BoardHeartEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BoardHeartDAO {

    // 리턴: 좋아요 했다면 1, 아니면 2, 에러 0 ||||| 글번호 iboard, 로그인한 사람의 loginUserPk
    // select 파라미터는 DTO (FM)
    public static int selIsHeart(BoardHeartEntity entity) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT iuser FROM t_board_heart " +
                " WHERE iuser = ? AND iboard = ? ";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, entity.getIuser());
            ps.setInt(2, entity.getIboard());
            rs = ps.executeQuery();
            if(rs.next()) {
                return 1;
            } else {
                return 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return 0;
    }

    // 좋아요 처리
    public static int insBoardHeart(BoardHeartEntity param) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " INSERT INTO t_board_heart ( iuser, iboard ) " +
                " VALUES ( ?, ? )";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIuser());
            ps.setInt(2, param.getIboard());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }

    // 좋아요 취소 처리
    public static int delBoardHeart(BoardHeartEntity param) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " DELETE FROM t_board_heart " +
                " WHERE iuser = ? AND iboard = ? ";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIuser());
            ps.setInt(2, param.getIboard());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }
}
