package com.koreait.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    public static int insBoard(BoardVO param){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "insert into t_board(title,ctnt,writer) values(?,?,?)";

        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);

            ps.setString(1, param.getTitle());
            ps.setString(2, param.getCtnt());
            ps.setString(3, param.getWriter());


            return ps.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            DbUtils.close(con,ps);
        }
        return 0;
    }
    // select 전체 조회
    public static List<BoardVO> selBoardList(){
        List<BoardVO> list = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from t_board";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()){
                BoardVO vo = new BoardVO();
                vo.setIboard(rs.getInt("iboard"));
                vo.setTitle(rs.getString("title"));
                vo.setCtnt(rs.getString("ctnt"));
                vo.setWriter(rs.getString("writer"));
                vo.setRdt(rs.getString("rdt"));
                list.add(vo);
            }
            return list;
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con,ps,rs);
        }
        return null;
    }

    public static BoardVO selBoard(BoardVO parm) {
        BoardVO vo = null;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from t_board where iboard = ?";

        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, parm.getIboard());
            rs = ps.executeQuery();

            if(rs.next()){
                vo = new BoardVO();
                vo.setIboard(rs.getInt("iboard"));
                vo.setTitle(rs.getString("title"));
                vo.setCtnt(rs.getString("ctnt"));
                vo.setWriter(rs.getString("writer"));
                vo.setRdt(rs.getString("rdt"));

            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con,ps,rs);
        }
        return vo;
    }
}
