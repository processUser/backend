package com;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyUtils {
    public static void playView(String url, String title, HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("url", url);
        request.setAttribute("title", title);
        MyUtils.forward(request, response, "layout");
    }
    public static void forward(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/"+url+".jsp").forward(request,response);
    }

//   인트 변경
    public static int parameterInt(HttpServletRequest request, String s){
        return StringToInt(request.getParameter(s), 0);
    }
    public static int StringToInt(String s, int def) {
        try{
            return Integer.parseInt(s);
        }catch (Exception e){
            return def;
        }
    }
}
