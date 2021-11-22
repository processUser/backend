package com.koreait.board2.board;

import com.koreait.board2.DbUtils;
import com.koreait.board2.MyUtils;
import com.koreait.board2.model.BoardParamVO;
import com.koreait.board2.model.BoardVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    public static List<BoardVO> selBoardList(BoardParamVO param){
        List<BoardVO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select A.iboard, A.title, A.ctnt, B.nm AS writerNm, A.mdt from t_board A " +
                "inner join t_user B on A.writer = B.iuser order by A.iboard desc limit ?, ?;";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getsIdx());
            ps.setInt(2, param.getRecordCnt());
            rs = ps.executeQuery();
            while (rs.next()){
                BoardVO vo = new BoardVO();
                vo.setIboard(rs.getInt("iboard"));
//                String str = MyUtils.decodeHtml(rs.getString("title"));
//                vo.setTitle(str);
                vo.setTitle(rs.getString("title"));
                vo.setCtnt(rs.getString("ctnt"));
                vo.setWriterNm(rs.getString("writerNm"));
                vo.setMdt(rs.getString("mdt"));
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return list;
    }
    //
    public static BoardVO selBoard(BoardVO param){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select A.iboard, A.title, A.ctnt, B.nm AS writerNm, A.mdt from t_board A " +
                "inner join t_user B on A.writer = B.iuser where iboard = ?;";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIboard());
            rs = ps.executeQuery();

            if(rs.next()){
                BoardVO vo = new BoardVO();
                vo.setIboard(rs.getInt("iboard"));
//                String str = MyUtils.decodeHtml(rs.getString("title"));
//                vo.setTitle(str);
                vo.setTitle(rs.getString("title"));
                vo.setCtnt(rs.getString("ctnt"));
                vo.setWriterNm(rs.getString("writerNm"));
                vo.setMdt(rs.getString("mdt"));
                return vo;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return null;
    }
    //
    public static int insBoard(BoardVO param){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "insert into t_board(title, ctnt, writer) values (?, ?, ?)";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            String title = MyUtils.encodeHtml(param.getTitle());
            ps.setString(1, title);
            ps.setString(2, param.getCtnt());
            ps.setInt(3, param.getWriter());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }
    public static int updBoard(BoardVO param) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "update t_board set title = ?, ctnt = ? , mdt = now() where iboard = ? and writer = ?;";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            String title = MyUtils.encodeHtml(param.getTitle());
            ps.setString(1, title);
            ps.setString(2, param.getCtnt());
            ps.setInt(3, param.getIboard());
            ps.setInt(4, param.getWriter());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }
    //
    public static int delBoard(BoardVO param) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "delete table t_board where iboard = ? and writer = ?;";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIboard());
            ps.setInt(2, param.getWriter());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }
    //페이징 처리 - 게시판 총 페이지 수 구하기
    public static int selMaxPage(BoardParamVO param){
        Connection con= null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ceil(COUNT(*) / ?) FROM t_board;";
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getRecordCnt());
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }
    // 이전글 - 최신글
    public static int prevBoard(int num) {
        Connection con= null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT iboard from t_board where iboard > ? order by iboard limit 1; ";
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, num);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("iboard");
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }
    // 다음글
    public static int nextBoard(int num) {
        Connection con= null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT iboard from t_board where iboard < ? order by iboard desc limit 1; ";
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, num);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("iboard");
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }
}
