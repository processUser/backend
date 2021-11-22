package com.koreait.board2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyUtils {
    /*
    RequestDispatcher 사용시
    get -> get 으로만 적용
    post -> post 로만 적용
    주소값 변화 없다.
     */
    public static void urlForward(HttpServletRequest req, HttpServletResponse res, String url) throws ServletException, IOException {
        String path = "/WEB-INF" + url + ".jsp";
        req.getRequestDispatcher(path).forward(req, res);
    }

    //
    public static int intParameter(HttpServletRequest req, String str) {
        return StringToInt(req.getParameter(str));
    }
    public static int intParameter(HttpServletRequest req, String str, int def) {
        return StringToInt(req.getParameter(str), def);
    }
    //인트 변환
    public static int StringToInt(String str){
        return StringToInt(str, 0);
    }

    public static int StringToInt(String str, int def){
        try {
            return Integer.parseInt(str);
        } catch (Exception e){

        }
        return def;
    }

    // 문자열 치환
    public static String encodeHtml(String str){
        String result = str;
//        final String[] expressionCharacter ={"!","\"","#","$","%","&","'","\\(","\\)","\\*","\\+",",","-",".","/",":",";","<","=",">","\\?","@","\\[","\\\\","]","\\^","_","`","\\{","\\|","}","~"};
//        final String[] numberExpression ={"&#33;","&#34;","&#35;","&#36;","&#37;","&#38;","&#39;","&#40;","&#41;","&#42;","&#43;","&#44;","&#45;","&#46;","&#47;","&#58;","&#59;","&#60;","&#61;","&#62;","&#63;","&#64;","&#91;","&#92;","&#93;","&#94;","&#95;","&#96;","&#123;","&#124;","&#125;","&#126;"};
        final String[] expressionCharacter ={"<",">","\"","'","\\\\"};
        final String[] numberExpression ={"&#60;","&#62;","&#34;","&#039","&#092"};
        for (int i = 0; i< numberExpression.length; i++){
            if(result == null) break;
            result = result.replaceAll(expressionCharacter[i], numberExpression[i]);
        }
        return result;
    }
    public static String decodeHtml(String str) {
        String result = str;
        final String[] expressionCharacter = {"<", ">", "\"", "'", "\\\\"};
        final String[] numberExpression = {"&#60;", "&#62;", "&#34;", "&#039", "&#092"};
        for (int i = 0; i < numberExpression.length; i++) {
            if (result == null) break;
            result = result.replaceAll(numberExpression[i], expressionCharacter[i]);
        }
        return result;
    }
}
