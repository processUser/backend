package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardDAO {
    public static List<BoardVO> selBoardList(){
        List<BoardVO> list= new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from t_board order by iboard desc;";

        try {
            con = Dbutils.getCon();
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

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Dbutils.close(con,ps,rs);
        }
        return list;
    }
    public static BoardVO selBoard(BoardVO param) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select title, ctnt, writer, rdt from t_board where iboard = ?";

        try {
            con = Dbutils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIboard());
            rs = ps.executeQuery();

            if(rs.next()) {
                BoardVO vo = new BoardVO();
                vo.setIboard(param.getIboard());
                vo.setTitle(rs.getString("title"));
                vo.setCtnt(rs.getString("ctnt"));
                vo.setWriter(rs.getString("writer"));
                vo.setRdt(rs.getString("rdt"));

                return vo;
            }

        } catch (Exception e){
            e.printStackTrace();
        }finally {
            Dbutils.close(con,ps,rs);
        }
        return null;
    }
    public static List<BoardVO> searchBoard(String choice,String search){
        List<BoardVO> list = new ArrayList<>();

        /*
        초성검색
        List<String> index_list = new ArrayList<>(); index_list.add("ㄱ"); index_list.add("ㄴ"); index_list.add("ㄷ"); index_list.add("ㄹ"); index_list.add("ㅁ"); index_list.add("ㅂ"); index_list.add("ㅅ"); index_list.add("ㅇ"); index_list.add("ㅈ"); index_list.add("ㅊ"); index_list.add("ㅋ"); index_list.add("ㅌ"); index_list.add("ㅍ"); index_list.add("ㅎ");
        Map<Integer, String> index_map = new HashMap<>(); index_map.put(0, "가"); index_map.put(1, "나"); index_map.put(2, "다"); index_map.put(3, "라"); index_map.put(4, "마"); index_map.put(5, "바"); index_map.put(6, "사"); index_map.put(7, "아"); index_map.put(8, "자"); index_map.put(9, "차"); index_map.put(10, "카"); index_map.put(11, "타"); index_map.put(12, "파"); index_map.put(13, "하"); index_map.put(14, "힣");
        String index = search;
        int num = 0;
        String whereSQL = "";
        for( int i = 0; i < index_list.size(); i++ ) {
            if( index.equals(index_list.get(i)) ) {
                num = i;
                break;
            }
        }
        whereSQL = " title >= '" + index_map.get(num) + "' and title <= '" + index_map.get(num+1) +"'";

//        출처: https://nm-it-diary.tistory.com/24 [웹개발자 나나]
        */
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = null;


            switch (choice){
                case "1":
                    sql = "select * from t_board where title REGEXP '["+search+"]' OR ctnt REGEXP '["+search+"]';";
                    break;
                case "2":
//                    sql = "select * from t_board where"+ whereSQL;
                    sql = "select * from t_board where title like '%"+search+"%';";
                    break;
                case "3":
                    sql = "select * from t_board where writer like '%"+search+"%';";
                    break;
                default:
                    sql = "select * from t_board;";
                    break;
            }
        try {
            con = Dbutils.getCon();
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
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            Dbutils.close(con,ps,rs);
        }

        return list;
    }
    public static int updBoard(BoardVO param){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "update t_board set title = ?, writer = ?, ctnt = ? where iboard = ?;";

        try {
            con = Dbutils.getCon();
            ps = con.prepareStatement(sql);

            ps.setString(1, param.getTitle());
            ps.setString(2, param.getWriter());
            ps.setString(3, param.getCtnt());
            ps.setInt(4, param.getIboard());

            return ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            Dbutils.close(con,ps);
        }
        return 0;
    }
    public static int deleteBoard(String striboard){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "delete from t_board where iboard=?;";
        try{
            con = Dbutils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, striboard);
            return ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Dbutils.close(con, ps);
        }

        return 0;
    }
    public static int insertBoard(BoardVO param){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "insert into t_board(title, ctnt, writer) values(?,?,?);";

        try {
            con = Dbutils.getCon();
            ps = con.prepareStatement(sql);

            ps.setString(1,param.getTitle());
            ps.setString(2,param.getCtnt());
            ps.setString(3,param.getWriter());
            return ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            Dbutils.close(con,ps);
        }

        return 0;
    }
}
