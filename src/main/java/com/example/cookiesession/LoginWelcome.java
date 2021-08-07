package com.example.cookiesession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@WebServlet(name = "loginWelcome", urlPatterns = "/welcome")
public class LoginWelcome extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        HttpSession session = req.getSession();
        PrintWriter pw = resp.getWriter();

        if (session.getAttribute("nickName") != null) {
            pw.println("<h1>Hello,");
            pw.println(session.getAttribute("nickName"));
            pw.println("</h1>");
            pw.println("<a href='/auth/logout'>Logout</a>");
        } else {
            pw.println("<h1>Welcome</h1>");
            pw.println("<a href='/auth/login'>Login</a>");
        }
    }
}
