package com.koreait.basic.dao;

import com.koreait.basic.DbUtils;
import com.koreait.basic.board.model.BoardVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardRankDAO {
    public static List<BoardVO> selBoardHitsRankList(){
        List<BoardVO> list = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT A.iboard, A.title, A.writer, A.hit, A.rdt, B.nm AS writerNm " +
                "FROM t_board A " +
                "INNER JOIN t_user B " +
                "ON A.writer = B.iuser " +
                "WHERE 0 < hit " +
                "ORDER BY hit DESC, iboard DESC " +
                "LIMIT 10;";
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                BoardVO vo = BoardVO.builder()
                        .iboard(rs.getInt("iboard"))
                        .title(rs.getString("title"))
                        .hit(rs.getInt("hit"))
                        .writerNm(rs.getString("writerNm"))
                        .rdt(rs.getString("rdt"))
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
}
