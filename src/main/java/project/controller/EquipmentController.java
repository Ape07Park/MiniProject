package project.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import project.entity.Equipment;
import project.entity.User;
import project.service.EquipmentService;
import project.service.EquipmentServiceImpl;
import project.service.UserService;
import project.service.UserServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/mini/equipment/list")
public class EquipmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EquipmentService eSvc = new EquipmentServiceImpl();

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(); // session 받기
		String requestUri = request.getRequestURI(); // 입력한 주소값을 받음
		String[] uri = request.getRequestURI().split("/");
		String action = uri[uri.length - 1];
		String method = request.getMethod();
		RequestDispatcher rd = null;
		
		
		// db에서 장비 이름, 장비 설명 가져오기 
		
		switch (action) {
		case "list":
			String page_ = request.getParameter("page");
			int page = (page_ == null || page_.equals("")) ? 1 : Integer.parseInt(page_); // 입력값 받기
			session.setAttribute("currentUserPage", page);
			List<Equipment> equipList = eSvc.getEquipmentList(page);

			// 모델에서 가져오기: "list"에 list 값 세팅
			request.setAttribute("equipList", equipList);

			// for paginantion
			int totalUsers = eSvc.getEquipmentCount();
			int totalpages = (int) Math.ceil((totalUsers + 1) * 1.0 / eSvc.COUNT_PER_PAGE); // 소수로 만들어서 나누고 나머지는 소수점으로 있기에
																						// 올림하여 페이지 +1하기
			List<String> pageList = new ArrayList<String>();
			for (int i = 1; i <= totalpages; i++) {
				pageList.add(String.valueOf(i)); // i(정수) -> String
			}
			// 내려 보내기
			request.setAttribute("pageList", pageList); // pageList 이름(키)를 가지는 것에 pageList라는 value를 세팅(넣음)

			// viewer로 보내기

			rd = request.getRequestDispatcher("/WEB-INF/view/equipment/list.jsp");

			// ** 앞에 보여줄려면 꼭 필요
			rd.forward(request, response);
			break;
			
		}
	}
}
