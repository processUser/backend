package com.koreait.board2.board;

import com.koreait.board2.DbUtils;
import com.koreait.board2.model.BoardParamVO;
import com.koreait.board2.model.BoardVO;
import com.koreait.board2.model.UserVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    public static int insBoard(BoardVO param) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "insert into t_board(title, ctnt, writer) values(?, ?, ?);";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, param.getTitle());
            ps.setString(2, param.getCtnt());
            ps.setInt(3, param.getWriter());

            return ps.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }

    // Board list 전체
    public static List<BoardVO> selList(BoardParamVO paramVO) {
        List<BoardVO> list = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select A.iboard, A.title, B.nm as writerNm, A.rdt from t_board A " +
                "inner join t_user B on A.writer = B.iuser order by iboard desc limit ?, ?;";
        try{
            con=DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, paramVO.getsIdx());
            ps.setInt(2, paramVO.getRecordCnt());
            rs = ps.executeQuery();

            while (rs.next()){
                BoardVO param = new BoardVO();

                param.setIboard(rs.getInt("iboard"));
                param.setTitle(rs.getString("title"));
                param.setWriterNm(rs.getString("writerNm"));
                param.setRdt(rs.getString("rdt"));
                list.add(param);
            }
            return list;

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return null;
    }
    public static BoardVO selDetail(BoardVO param){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select A.title, A.ctnt, A.writer, B.nm as writerNm, A.mdt from t_board A " +
                "inner join t_user B on A.writer = B.iuser where iboard = ?;";
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIboard());
            rs = ps.executeQuery();
            if(rs.next()){
                BoardVO vo = new BoardVO();
                vo.setIboard(param.getIboard());
                vo.setTitle(rs.getString("title"));
                vo.setCtnt(rs.getString("ctnt"));
                vo.setWriter(rs.getInt("writer"));
                vo.setWriterNm(rs.getString("writerNm"));
                vo.setMdt(rs.getString("mdt"));
                return vo;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con,ps,rs);
        }
        return null;
    }
    // 삭제
    public static int delBoard(BoardVO bVo){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "delete from t_board where iboard = ? and writer = ?;";

        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, bVo.getIboard());
            ps.setInt(2, bVo.getWriter());
            return ps.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con,ps);
        }
        return 0;
    }

    public static int updBoard(BoardVO param){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "update t_board set title = ?, ctnt = ?, mdt = now() where iboard = ? and writer = ?;";

        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, param.getTitle());
            ps.setString(2, param.getCtnt());
            ps.setInt(3, param.getIboard());
            ps.setInt(4, param.getWriter());
            return ps.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }

    // 페이징 처리할때 사용하는 내용들
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
            return ps.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }


    // 페이징 처리 작업 끝
}
