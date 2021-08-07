package com.example.cookiesession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/count")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Integer count = (Integer)session.getAttribute("count");

        // count라는 세션이 없으면 count : 1로 세션 생성 있이면 +1을 증가시켜 다시 셋팅
        if (session.getAttribute("count") == null) {
            session.setAttribute("count", 1);
        } else {
            session.setAttribute("count", count + 1);
        }

        PrintWriter pw = resp.getWriter();
        pw.println("count : " + session.getAttribute("count"));
    }
}
