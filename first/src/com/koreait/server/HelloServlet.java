package com.koreait.server;

import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

// 하나의 서블릿은 하나의 get, post 방식을 가질 수 있다.
@WebServlet(name = "HelloServlet", value = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getParameter return 타입은 String
        //값을 받을때는 getParameter로 받는다.
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        System.out.println("name : " +name);
        System.out.println("age : " +age);

        PrintWriter out = response.getWriter();
        TestVO vo = new TestVO();
        vo.setName("ahahah");
        vo.setAge(22);

        String result = String.format("{\"name\" : \"%s\",\"age\": %s}",vo.getName(),vo.getAge());
        System.out.println("result: " +result);

        Gson gson = new Gson();
        String result2 = gson.toJson(vo);
        System.out.println("result2: " +result2);
        out.println(result);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String data = Utils.getJson(request);

        Gson gson = new Gson();
        TestVO vo = gson.fromJson(data, TestVO.class);

        System.out.println("name : " + vo.getName());
        System.out.println("age : " +vo.getAge());
        System.out.println(data);
    }
}
