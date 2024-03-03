package project.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import project.entity.User;
import project.service.UserService;
import project.service.UserServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

@WebServlet({ "/mini/user/list", "/mini/user/register", "/mini/user/update", "/mini/user/delete", "/mini/user/login",
		"/mini/user/logout" })

public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService uSvc = new UserServiceImpl();

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod();

		HttpSession session = request.getSession(); // session 받기

		String requestUri = request.getRequestURI(); // 입력한 주소값을 받음

		String[] uri = requestUri.split("/"); // '/' 기준으로 나누기

		String action = uri[uri.length - 1]; // url 길이의 -1 /bbs/user/register의 -1 인 "register"를 본다
		RequestDispatcher rd = null;

		/*
		 * switch문에서 중복되는 것들 미리 선언해놓음
		 */
		String uid = null, pwd = null, pwd2 = null, uname = null, email = null, hashedPwd = null;
		String msg = "", url = "", nickName = "";
		User user = null;

		switch (action) {
		/*
		 * 
		 */
		case "list":

			String page_ = request.getParameter("page");
			int page = (page_ == null || page_.equals("")) ? 1 : Integer.parseInt(page_); // 입력값 받기
			session.setAttribute("currentUserPage", page);
			List<User> userList = uSvc.getUserList(page);

			// 모델에서 가져오기: "list"에 list 값 세팅
			request.setAttribute("userList", userList);

			// for paginantion
			int totalUsers = uSvc.getUserCount();
			int totalpages = (int) Math.ceil(totalUsers * 1.0 / uSvc.COUNT_PER_PAGE); // 소수로 만들어서 나누고 나머지는 소수점으로 있기에
																						// 올림하여 페이지 +1하기
			List<String> pageList = new ArrayList<String>();
			for (int i = 1; i <= totalpages; i++) {
				pageList.add(String.valueOf(i)); // i(정수) -> String
			}
			// 내려 보내기
			request.setAttribute("pageList", pageList); // pageList 이름(키)를 가지는 것에 pageList라는 value를 세팅(넣음)

			// viewer로 보내기

			rd = request.getRequestDispatcher("/WEB-INF/view/user/list.jsp");

			// ** 앞에 보여줄려면 꼭 필요
			rd.forward(request, response);
			break;

		/*
		 * 
		 */

		case "login":
			if (method.equals("GET")) {

				rd = request.getRequestDispatcher("/WEB-INF/view/user/login.jsp");
				rd.forward(request, response);

			} else {
				// 보여주는 거에서 파라미터 가져오기
				uid = request.getParameter("uid");
				pwd = request.getParameter("pwd");

				int result = uSvc.login(uid, pwd);

				// * 재대로 된 결과(correct) 나오면 session에다가 값 세팅함
				if (result == uSvc.CORRECT_LOGIN) {
					user = uSvc.getUserByUid(uid);
					session.setAttribute("sessUid", uid);
					session.setAttribute("sessUname", user.getUname());

					msg = user.getUname() + "님 환영합니다";
					// list 화면으로 감
					url = "/mp/mini/main";

					// 이거 작동 x
				} else if (result == uSvc.WRONG_PASSWORD) {
					msg = "패스워드가 틀립니다";
					url = "/mp/mini/user/login";
				} else {
					msg = "아이디가 틀립니다";
					url = "/mp/mini/user/login";
				}
				rd = request.getRequestDispatcher("/WEB-INF/view/common/alertMsg.jsp");
				request.setAttribute("msg", msg);
				request.setAttribute("url", url);
				rd.forward(request, response);
			}
			break;

		/*
		 * 
		 */
		case "logout":
			// * session 지우면 로그아웃 됨
			session.invalidate(); // 세션 지우기
			response.sendRedirect("/mp/mini/user/login");
			break;

		/*
		 * 
		 */
		case "register":
			if (method.equals("GET")) {
				session.invalidate();

				rd = request.getRequestDispatcher("/WEB-INF/view/user/register.jsp");
				rd.forward(request, response);
			} else {

				uid = request.getParameter("uid");
				pwd = request.getParameter("pwd");
				pwd2 = request.getParameter("pwd2");
				nickName = request.getParameter("nickName");
				uname = request.getParameter("uname");
				email = request.getParameter("email");

				// id 중복되는 경우
				if (uSvc.getUserByUid(uid) != null) {
					rd = request.getRequestDispatcher("/WEB-INF/view/common/alertMsg.jsp");
					request.setAttribute("msg", "id 중복됨");
					request.setAttribute("url", "/mp/mini/user/register");
					rd.forward(request, response);

					// id, pwd 모두 잘 입력한 경우
				} else if (pwd.equals(pwd2)) {
					user = new User(uid, pwd, uname, nickName, email);
					uSvc.registerUser(user);
					response.sendRedirect("/mp/mini/user/list?page=1");
				} else { // pwd 틀린 경우
					rd = request.getRequestDispatcher("/WEB-INF/view/common/alertMsg.jsp");
					request.setAttribute("msg", "패스워드가 틀립니다");
					request.setAttribute("url", "/mp/mini/user/register");
					rd.forward(request, response);
				}
			}
			break;

		/*
		 * 
		 */
		case "update":
			if (method.equals("GET")) {
				uid = request.getParameter("uid");
				user = uSvc.getUserByUid(uid);

				rd = request.getRequestDispatcher("/WEB-INF/view/user/update.jsp");
				request.setAttribute("user", user);
				rd.forward(request, response);

			} else {
				uid = request.getParameter("uid");
				uname = request.getParameter("uname");
				nickName = request.getParameter("nickName");
				email = request.getParameter("email");

				pwd = request.getParameter("pwd");
				pwd2 = request.getParameter("pwd2");

				if (!pwd.equals(pwd2) || pwd.equals("") || pwd2.equals("")) {

					rd = request.getRequestDispatcher("/WEB-INF/view/common/alertMsg.jsp");
					request.setAttribute("msg", "패스워드가 틀립니다");
					request.setAttribute("url", "/mp/mini/user/update?uid=" + uid);
					rd.forward(request, response);
				}

				else if (pwd != null && pwd.equals(pwd2)) {
					hashedPwd = BCrypt.hashpw(pwd, BCrypt.gensalt());
				}

				user = new User(uid, hashedPwd, uname, nickName, email);
				uSvc.updateUser(user);
				response.sendRedirect("/mp/mini/user/list?page=1");
			}
			break;

		case "delete":
			uid = request.getParameter("uid");
			uSvc.deleteUser(uid);
			response.sendRedirect("/mp/mini/user/list?page=1");
			break;
		}
	}
}
