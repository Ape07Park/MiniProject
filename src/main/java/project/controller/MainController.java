package project.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import project.entity.BoardAuction;
import project.entity.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/mini/main/*")
public class MainController extends HttpServlet {
   private static final long serialVersionUID = 1L;
    
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      String method = request.getMethod();

      HttpSession session = request.getSession(); // session 받기

      String requestUri = request.getRequestURI(); // 입력한 주소값을 받음

      String[] uri = requestUri.split("/"); // '/' 기준으로 나누기

      String action = uri[uri.length - 1]; // url 길이의 -1 /bbs/user/register의 -1 인 "register"를 본다
      RequestDispatcher rd = null;
      
      String page_ = request.getParameter("page");
      int page = (page_ == null || page_.equals("")) ? 1 : Integer.parseInt(page_); // 입력값 받기
      session.setAttribute("currentUserPage", page);
      
      RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/main/firstPage.jsp");
       dispatcher.forward(request, response);
   
   }

}