package project.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/mini/equipment/view")
public class EquipmentController extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] uri = request.getRequestURI().split("/");
		String action = uri[uri.length - 1];
		String method = request.getMethod();
		RequestDispatcher rd = null;
		
		
		// db에서 장비 이름, 장비 설명 가져오기 
		
		switch (action) {
		case "view":
		case "register":
			if (method.equals("GET")) {
				rd = request.getRequestDispatcher("/WEB-INF/view/equipment/view.jsp");
				rd.forward(request, response);
			}

		}
	}
}
