package com.koreait.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO { // DAO - 데이터 접근 객체
    public static void main(String[] args){ // 테스트용도.
        StudentVO param = new StudentVO();
        param.setSno(4);
        StudentVO vo = selStudent(param);

        System.out.println("sno " + vo.getSno());
        System.out.println("nm " + vo.getNm());
        System.out.println("age " + vo.getAge());
        System.out.println("addr " + vo.getAddr());

    }
    public static DbUtils dbUtils = DbUtils.getInstance();

    // 레코드 INSERT 담당 메소드
    public static int insStudent(StudentVO vo) {
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO t_student2" +
                "(nm, age, addr)" +
                "VALUES" +
                "(?, ?, ?)";
        try {
            con = dbUtils.getCon(); // 커넥션 얻어오기
            ps = con.prepareStatement(sql); //

            ps.setString(1, vo.getNm());
            ps.setInt(2, vo.getAge());
            ps.setString(3, vo.getAddr());
            // 쿼리문 실행 실행
            result = ps.executeUpdate(); // 영향을 미친 행 수를 리턴 해준다.

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            dbUtils.close(con, ps);
        }
       return result;
    }

    // SELECT 담당 메소드
    public static List<StudentVO> selStudentList(){
        List<StudentVO> list = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;


        String sql = "SELECT sno, nm FROM t_student34";

        try {
            con = dbUtils.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){ // next() 는 줄(라인)을 가리킨다, 값이 있으면 true 리턴
                StudentVO vo = new StudentVO();
                int sno = rs.getInt("sno");
                String nm = rs.getString("nm");
                vo.setSno(sno);
                vo.setNm(nm);
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbUtils.close(con, ps, rs);
        }
        return list;
    }

    public static StudentVO selStudent(StudentVO vo) {
        StudentVO result = null;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM t_student34 WHERE sno = ?";
        try {
            con = dbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, vo.getSno());
            rs = ps.executeQuery();

            if(rs.next()){
                result = new StudentVO();
                result.setSno(rs.getInt("sno"));
                result.setNm(rs.getString("nm"));
                result.setAge(rs.getInt("age"));
                result.setAddr(rs.getString("addr"));
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbUtils.close(con, ps, rs);
        }
        return result;
    }

    // 레코드 UPDATE 담당 메소드
    public static int updStudent(StudentVO vo) {
        Connection con = null;
        PreparedStatement ps =null; // 쿼리문 실행 담당 (+ 쿼리문 완성)
        String sql = "UPDATE t_student2 SET nm = ?, age = ?, addr = ? WHERE sno = ? ";
        try{
            con = dbUtils.getCon();
            ps = con.prepareStatement(sql);

            ps.setString(1,vo.getNm());
            ps.setInt(2,vo.getAge());
            ps.setString(3,vo.getAddr());
            ps.setInt(4, vo.getSno());

            return ps.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            dbUtils.close(con, ps);
        }
        return 0;
    }

    // 레코드 DELETE 담당 메소드
    public static int delStudent(StudentVO vo) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM t_student2 WHERE sno = ?";

        try {
            con = dbUtils.getCon();
            ps = con.prepareStatement(sql);

            ps.setInt(1, vo.getSno());

            return ps.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            dbUtils.close(con, ps);
        }
        return 0;
    }
}
