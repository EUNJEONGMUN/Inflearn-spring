package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // [status-line]
        response.setStatus(HttpServletResponse.SC_OK);

        // [response-headers]
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setHeader("Cache-control", "no-cache, no-store, must-revalidate"); // 캐시 완전히 무효화
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header", "hello"); // 나만의 헤더 세팅 가능

        // [Header 편의 메서드]
        content(response);
        cookie(response);
        redirect(response);

        // [message body]
        PrintWriter writer = response.getWriter();
        writer.println("ok");

    }
    private void content(HttpServletResponse response) {
//      Content-Type: text/plain;charset=utf-8
//      Content-Length: 2
//      response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
//      response.setContentLength(2); //(생략시 자동 생성)
    }

    private void cookie(HttpServletResponse response) {
//      Set-Cookie: myCookie=good; Max-Age=600; // -> 직접 쿠키 넣기, 600초 동안 유효한 쿠키
//      response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        // 쿠키 객체 이용하면 편리함.
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
//      Status Code 302
//      Location: /basic/hello-form.html

//      response.setStatus(HttpServletResponse.SC_FOUND); //302
//      response.setHeader("Location", "/basic/hello-form.html");
        // 간편하게 한줄로 가능
        response.sendRedirect("/basic/hello-form.html");
    }

}
