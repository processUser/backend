package com.koreait.basic.dao;

import com.koreait.basic.DbUtils;
import com.koreait.basic.Utils;
import com.koreait.basic.board.model.BoardDTO;
import com.koreait.basic.board.model.BoardEntity;
import com.koreait.basic.board.model.BoardVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {

    //entity에 iboard값에 pk값 담기
    //return int값은 그대로.
    public static int insBoardWithPk(BoardEntity entity) {
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "INSERT INTO t_board(title, ctnt, writer)" +
                "VALUES (?, ?, ?)";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getTitle());
            ps.setString(2, entity.getCtnt());
            ps.setInt(3, entity.getWriter());
            result = ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if(rs.next()) {
                int iboard = rs.getInt(1);
                entity.setIboard(iboard);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return result;
    }

    public static int insBoard(BoardEntity entity){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "insert into t_board(title, ctnt, writer) values (?, ?, ?)";

        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, entity.getTitle());
            ps.setString(2, entity.getCtnt());
            ps.setInt(3, entity.getWriter());

            return ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }
    //
    public static int getMaxPageNum() {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "select from t_board;";

        return 0;
    }

    public static List<BoardVO> selBoardList() {
        List<BoardVO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select A.iboard, A.title, A.writer, a.hit, A.rdt, B.nm as writerNm from t_board A inner join t_user B on A.writer = B.iuser order by A.iboard desc";

        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                int iboard = rs.getInt("iboard");
                String title = rs.getString("title");
                int writer = rs.getInt("writer");
                int hit = rs.getInt("hit");
                String rdt = rs.getString("rdt");
                String writerNm = rs.getString("writerNm");
                BoardVO vo = BoardVO.builder().iboard(iboard).title(title).writer(writer).hit(hit).rdt(rdt).writerNm(writerNm).build();
                list.add(vo);
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con, ps);
        }
        return list;
    }
    public static BoardVO selBoardDetail(BoardDTO param){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select A.title, A.ctnt, A.hit, A.writer, B.nm as writerNm, A.rdt from t_board A inner join t_user B on A.writer = B.iuser where A.iboard = ?";

        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIboard());
            rs = ps.executeQuery();

            if(rs.next()){
                String title = rs.getString("title");
                String ctnt = rs.getString("ctnt");
                int hit = rs.getInt("hit");
                int writer = rs.getInt("writer");
                String rdt = rs.getString("rdt");
                String writerNm = rs.getString("writerNm");
                BoardVO vo = BoardVO.builder().iboard(param.getIboard()).title(title).ctnt(ctnt).writer(writer).hit(hit).rdt(rdt).writerNm(writerNm).build();
                return vo;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            DbUtils.close(con, ps, rs);
        }
        return null;
    }

    public static int updBoard(BoardEntity entity){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "update t_board set title = ?, ctnt = ?, mdt = now() where iboard = ? and writer = ?;";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, entity.getTitle());
            ps.setString(2, entity.getCtnt());
            ps.setInt(3, entity.getIboard());
            ps.setInt(4, entity.getWriter());
            return ps.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }

    //로그인 한 사람이 pk 값과 data에 들어있는 writer값이 다르거나,
    //로그인이 안 되어 있으면 hit값을 올려주기
    public static void updBoardHitUp(BoardDTO param){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "update t_board set hit = hit + 1 where iboard = ?;";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIboard());
            ps.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con, ps);
        }
    }

    public static int delBoard(BoardEntity entity){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "delete from t_board where iboard = ? and writer = ?";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, entity.getIboard());
            ps.setInt(2, entity.getWriter());
            return ps.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }
}
