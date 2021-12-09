package com.dao;

import com.DbUtils;
import com.board.model.BoardDTO;
import com.board.model.BoardEntity;
import com.board.model.BoardVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {

    public static List<BoardVO> selBoardList(){

        List<BoardVO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select A.iboard,A.title, A.writer , A.hit, A.rdt,B.nm as writerNm" +
                " from t_board A " +
                "inner join t_user B " +
                "on A.writer = B.iuser ";

        try{
            con=DbUtils.getCon();
            ps= con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()){

                int iboard = rs.getInt("iboard");
                String title = rs.getString("title");
                int writer = rs.getInt("writer");
                int hit = rs.getInt("hit");
                String rdt = rs.getString("rdt");
                String writerNm = rs.getString("writerNm");


                BoardVO vo = BoardVO.builder()
                        .iboard(iboard)
                        .title(title)
                        .writer(writer)
                        .hit(hit)
                        .rdt(rdt)
                        .writerNm(writerNm)
                        .build();
                    list.add(vo);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con,ps,rs);
        }
        return null;
    }

}
