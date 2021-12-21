package com.koreait.basic.dao;

import com.koreait.basic.DbUtils;
import com.koreait.basic.board.model.BoardCmtDTO;
import com.koreait.basic.board.model.BoardCmtEntity;
import com.koreait.basic.board.model.BoardCmtVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardCmtDAO {
    public static int insBoardCmt(BoardCmtEntity param){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "insert into t_board_cmt (iboard, ctnt, writer) values(?,?,?)";

        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIboard());
            ps.setString(2, param.getCtnt());
            ps.setInt(3, param.getWriter());
            return ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con,ps);
        }
        return 0;
    }
    public static List<BoardCmtVO> selBoardCmtList(BoardCmtDTO param){
        List<BoardCmtVO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select A.icmt, A.ctnt, A.writer, A.rdt, B.nm as writerNm from t_board_cmt A inner join t_user B on A.writer = B.iuser where A.iboard = ? order by A.icmt asc";

        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIboard());
            rs =ps.executeQuery();
            while(rs.next()){
                BoardCmtVO vo = BoardCmtVO.builder()
                        .icmt(rs.getInt("icmt"))
                        .ctnt(rs.getString("ctnt"))
                        .writer(rs.getInt("writer"))
                        .rdt(rs.getString("rdt"))
                        .writerNm(rs.getString("writerNm"))
                        .build();
                list.add(vo);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con,ps,rs);
        }
        return list;
    }
    public static int updBoardCmt(BoardCmtEntity entity){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "update t_board_cmt set ctnt = ? where icmt = ? and writer = ?";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, entity.getCtnt());
            ps.setInt(2, entity.getIcmt());
            ps.setInt(3, entity.getWriter());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }
    public static int delBoardCmt(BoardCmtEntity entity){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM t_board_cmt WHERE icmt = ? AND writer = ? ";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, entity.getIcmt());
            ps.setInt(2, entity.getWriter());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }
}
