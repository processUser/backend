package com.koreait.basic.dao;

import com.koreait.basic.DbUtils;
import com.koreait.basic.board.model.BoardVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardRankDAO {
    public static List<BoardVO> procResultSet(String sql){
        List<BoardVO> list = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                BoardVO vo = BoardVO.builder()
                        .iboard(rs.getInt("iboard"))
                        .title(rs.getString("title"))
                        .writer(rs.getInt("writer"))
                        .rdt(rs.getString("rdt"))
                        .writerNm(rs.getString("writerNm"))
                        .cnt(rs.getInt("cnt"))
                        .build();
                list.add(vo);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con, ps, rs);
        }
        return list;
    }
    //조회수 top 10
    public static List<BoardVO> selBoardHitsRankList(){
        String sql = "SELECT A.iboard, A.title, A.writer, A.rdt, B.nm AS writerNm, A.hit AS cnt " +
                "FROM t_board A " +
                "INNER JOIN t_user B " +
                "ON A.writer = B.iuser " +
                "WHERE 0 < hit " +
                "ORDER BY hit DESC, iboard DESC " +
                "LIMIT 10;";
        return procResultSet(sql);
    }

    // 댓글 top 10
    public static List<BoardVO> selBoardCntRankList(){
        String sql ="SELECT A.iboard, A.title, A.writer, A.rdt, B.nm AS writerNm, C.cnt " +
                "FROM t_board A " +
                "INNER JOIN t_user B " +
                "ON A.writer = B.iuser " +
                "INNER JOIN ( " +
                "SELECT iboard, COUNT(icmt) AS cnt " +
                "FROM t_board_cmt " +
                "GROUP BY iboard) C " +
                "ON A.iboard = C.iboard " +
                "ORDER BY C.cnt DESC " +
                "LIMIT 10;";

        return  procResultSet(sql);
    }

    // 좋아요 top 10
    public static List<BoardVO> selBoardHeartsRankList(){
        String sql = " SELECT A.iboard, A.title, A.writer, A.rdt, C.cnt " +
                " , B.nm AS writerNm " +
                " FROM t_board A " +
                " INNER JOIN t_user B " +
                " ON A.writer = B.iuser " +
                " INNER JOIN " +
                " (SELECT iboard, COUNT(iuser) AS cnt FROM t_board_heart GROUP BY iboard) C " +
                " ON A.iboard = C.iboard " +
                " ORDER BY C.cnt DESC " +
                " LIMIT 10 ";
        return  procResultSet(sql);
    }

}
