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

import org.apache.tomcat.util.collections.CaseInsensitiveKeyMap;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet({ "/bbs/user/list", "/bbs/user/register", "/bbs/user/update", "/bbs/user/delete", "/bbs/user/login",
		"/bbs/user/logout" })
// Servlet을 바꿨으니 url(외부 접속 o)도 바꿈 
// jsp는 내 거니 내 폴더에 맞게 바꾸기
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

		String uid = null, pwd = null, pwd2 = null, uname = null, email = null, hashedPwd = null;
		String msg = "", url = "";
		User user = null;
		switch (action) {
		case "list":

			// 입력값 받기 (page 값을 넣을 수 있도록 함 입력값 받기)
			String page_ = request.getParameter("page");
			int page = (page_ == null || page_.equals("")) ? 1 : Integer.parseInt(page_); // 입력값 받기
			session.setAttribute("currentUserPage", page);
			List<User> userList = uSvc.getUserList(page);

			// * 내용물 안나오면 size 찍어보기
			System.out.println(userList.size());

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

		case "login":
			if (method.equals("GET")) {
				// 보여주는 거
				// RequestDispatcher: 클라이언트로부터 요청을 받고 이를 다른 리소스(서블릿, html, jsp)로 넘겨주는 역할

				// .jsp에서 클라이언트가 입력한 값들을 받아옴

				rd = request.getRequestDispatcher("/WEB-INF/view/user/login.jsp");

				// request와 response를 다른 리소스(서블릿, jsp, html)로 넘김
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
					url = "/jw/bbs/board/list?page=1";

					// 이거 작동 x
				} else if (result == uSvc.WRONG_PASSWORD) {
					msg = "패스워드가 틀림";
					url = "/jw/bbs/user/login";
				} else {
					msg = "아이디가 틀림";
					url = "/jw/bbs/user/login";
				}
				rd = request.getRequestDispatcher("/WEB-INF/view/common/alertMsg.jsp");
				request.setAttribute("msg", msg);
				request.setAttribute("url", url);
				rd.forward(request, response);
			}
			break;

		case "logout":
			// * session 지우면 로그아웃 됨
			session.invalidate(); // 세션 지우기
			response.sendRedirect("/jw/bbs/user/login");
			break;

		case "register":
			if (method.equals("GET")) {
				session.invalidate();

				rd = request.getRequestDispatcher("/WEB-INF/view/user/register.jsp");
				rd.forward(request, response);
			} else {

				uid = request.getParameter("uid");
				pwd = request.getParameter("pwd");
				pwd2 = request.getParameter("pwd2");
				uname = request.getParameter("uname");
				email = request.getParameter("email");

				// id 중복되는 경우
				if (uSvc.getUserByUid(uid) != null) {
					rd = request.getRequestDispatcher("/WEB-INF/view/common/alertMsg.jsp");
					request.setAttribute("msg", "id 중복됨");
					request.setAttribute("url", "/jw/bbs/user/register");
					rd.forward(request, response);

					// id, pwd 모두 잘 입력한 경우
				} else if (pwd.equals(pwd2)) {
					user = new User(uid, pwd, uname, email);
					uSvc.registerUser(user);
					response.sendRedirect("/jw/bbs/user/list?page=1");
				} else { // pwd 틀린 경우
					rd = request.getRequestDispatcher("/WEB-INF/view/common/alertMsg.jsp");
					request.setAttribute("msg", "패스워드가 틀립니다");
					request.setAttribute("url", "/jw/bbs/user/register");
					rd.forward(request, response);
				}
			}
			break;

		case "update":
			if (method.equals("GET")) {
				uid = request.getParameter("uid");
				user = uSvc.getUserByUid(uid);

//				
				rd = request.getRequestDispatcher("/WEB-INF/view/user/update.jsp");
				request.setAttribute("user", user);
				rd.forward(request, response);

			} else {
				uid = request.getParameter("uid");
				uname = request.getParameter("uname");
				email = request.getParameter("email");
				pwd = request.getParameter("pwd");
				pwd2 = request.getParameter("pwd2");
				// 추가로 조건문 넣기
				// 동시에 비교안됨 이유는 pwd는 암호화 안되었음
				if (pwd.equals(pwd2) && pwd != null) {
					hashedPwd = BCrypt.hashpw(pwd, BCrypt.gensalt());
				}
				user = new User(uid, hashedPwd, uname, email);
				uSvc.updateUser(user);
				response.sendRedirect("/jw/bbs/user/list?page=1");
			}
			break;

		case "delete":
			uid = request.getParameter("uid");
			uSvc.deleteUser(uid);
			response.sendRedirect("/jw/bbs/user/list?page=1");
			break;
		}
	}
}
