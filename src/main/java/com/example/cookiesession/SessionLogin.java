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

@WebServlet(name = "sessionLogin", urlPatterns = "/auth/login")
public class SessionLogin extends HttpServlet {

    // 로그인 페이지
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        pw.println("<html><body>");
        pw.println("<form action='/auth/login' method='post'>");
        pw.println("<p>");
        pw.println("<input type='text' name='username' placeholder='username'>");
        pw.println("</p>");

        pw.println("<p>");
        pw.println("<input type='password' name='password' placeholder='password'>");
        pw.println("</p>");

        pw.println("<p>");
        pw.println("<input type='submit'>");
        pw.println("</p>");

        pw.println("</form>");

        pw.println("</body></html>");
    }

    // 로그인 로직
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        resp.setContentType("text/html;charset=utf-8");
        String body = getBody(req);
        PrintWriter pw = resp.getWriter();

        // 로그인 정보 하드코딩
        String dbUsername = "monkeydugi";
        String dbPassword = "123";

        String[] split = body.split("&");
        String reqUsername = split[0].substring(9);
        String reqPassword = split[1].substring(9);

        // 로그인 정보가 맞으면 세션 저장하고 리다이렉트
        // 정보가 틀리다면 로그인 페이지 링크 출력
        if (dbUsername.equals(reqUsername) && dbPassword.equals(reqPassword)) {
            session.setAttribute("nickName", "dugi");
            resp.sendRedirect("/welcome");
        } else {
            pw.println("누구세요? <a href='/auth/login'>login</a>");
        }
    }

    // request body 읽기
    public static String getBody(HttpServletRequest request) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }
}
