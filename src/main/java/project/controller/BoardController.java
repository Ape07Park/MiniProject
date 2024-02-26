package project.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import project.entity.Board;
import project.entity.Reply;
import project.service.BoardService;
import project.service.BoardServiceImpl;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@WebServlet({ "/bbs/board/list", "/bbs/board/insert", "/bbs/board/update", "/bbs/board/delete", "/bbs/board/detail" })
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService bSvc = new BoardServiceImpl();

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod();

		HttpSession session = request.getSession(); // session 받기

		String requestUri = request.getRequestURI(); // 입력한 주소값을 받음

		String[] uri = requestUri.split("/"); // '/' 기준으로 나누기

		String action = uri[uri.length - 1]; // url 길이의 -1 /bbs/user/register의 -1 인 "register"를 본다

		// 반복된 변수 세팅
		RequestDispatcher rd = null;
		String title = "", content = "", field = "", query = "", page_ = "", uid="";
		Board board = null;
		int bid = 0, page = 0;
		String sessUid = (String) session.getAttribute("sessUid");
		
		// 인코딩 작업
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html; charset=utf-8");

		switch (action) {
		case "list": // /jw/bbs/board/list?p=1&f=title&q=검색
			page_ = request.getParameter("p");
			field = request.getParameter("f");
			query = request.getParameter("q");

			page = (page_ == null || page_.equals("")) ? 1 : Integer.parseInt(page_);
			session.setAttribute("currentBoardPage", page);

			field = (field == null || field.equals("")) ? "title" : field;
			query = (query == null || query.equals("")) ? "" : query;

			session.setAttribute("field", field);
			session.setAttribute("query", query);

			session.setAttribute("currentBoardPage", page);
			List<Board> boardList = bSvc.getBoardList(page, field, query);
			request.setAttribute("boardList", boardList);

			// for paginantion
			int totalItems = bSvc.getBoardCount(field, query);
			int totalpages = (int) Math.ceil(totalItems * 1.0 / bSvc.COUNT_PER_PAGE); // 소수로 만들어서 나누고 나머지는 소수점으로 있기에
																						// 올림하여 페이지 +1하기
			List<String> pageList = new ArrayList<String>();
			for (int i = 1; i <= totalpages; i++) {
				pageList.add(String.valueOf(i)); // i(정수) -> String
			}

			request.setAttribute("pageList", pageList); // pageList 이름(키)를 가지는 것에 pageList라는 value를 세팅(넣음)

			rd = request.getRequestDispatcher("/WEB-INF/view/board/list.jsp");
			rd.forward(request, response);
			break;

		case "insert":
			// * sendRedirect나 getRequestDispatcher나 둘중 하나만 가능
			if (sessUid == null || sessUid.equals("")) {
				// 로그인이 안되어 있으면 login으로 돌려보냄
				response.sendRedirect("/jw/bbs/user/login");
				break;
			}

			if (method.equals("GET")) {
				rd = request.getRequestDispatcher("/WEB-INF/view/board/insert.jsp");
				System.out.println(rd);
				rd.forward(request, response);
			} else {
				title = request.getParameter("title");
				content = request.getParameter("content");

				board = new Board(title, content, sessUid);
				bSvc.insertBoard(board);
				response.sendRedirect("/jw/bbs/board/list?p=1");
			}
			break;

		case "detail":
			// 뭐 보낼지 세팅 후 jsp에 보내기
			bid = Integer.parseInt(request.getParameter("bid"));
			uid = request.getParameter("uid");
			
			// uid가 sessUid와 일치 x면 조회수 상승 x
			if(! uid.equals(sessUid)) {
				bSvc.increaseViewCount(bid);
			}

			board = bSvc.getBoard(bid);

			// 뭐 보낼지 세팅 후 jsp에 보내기
			request.setAttribute("board", board);

			List<Reply> replyList = null; // 댓글 목록 필요!!
			request.setAttribute("replyList", replyList);

			rd = request.getRequestDispatcher("/WEB-INF/view/board/detail.jsp");
			rd.forward(request, response);
			break;
			
		case "update":
			if(method.equals("GET")) {
				
				// bid로 받아오기
				bid = Integer.parseInt(request.getParameter("bid"));
				
				// board 를 bid로 받아와서 변수에 저장
				board = bSvc.getBoard(bid);
				
				// jsp "board"에 board 보내기
				request.setAttribute("board", board);
				
				// 보낼 주소
				rd = request.getRequestDispatcher("/WEB-INF/view/board/update.jsp");
				
				// 화면에 띄우기
				rd.forward(request, response);
			} else {
				// 파라미터 받아오기
				bid = Integer.parseInt(request.getParameter("bid"));
				uid = request.getParameter("uid");
				title = request.getParameter("title");
				content = request.getParameter("content");
				
				board = new Board(bid, title, content);
				
				bSvc.updateBoard(board);
				response.sendRedirect("/jw/bbs/board/detail?bid=" + bid + "&uid=" + uid);
				
			}
			break;
			
		case "delete":
			bid = Integer.parseInt(request.getParameter("bid"));
			bSvc.deleteBoard(bid);
			page = (Integer) session.getAttribute("currentBoardPage");
			field = (String) session.getAttribute("field");
			query = (String) session.getAttribute("query");
			// 작성자로 입력시 글 깨짐 방지 위해 
			query = URLEncoder.encode(query, "utf-8");
			response.sendRedirect("/jw/bbs/board/list?p=" + page + "&f=" + field + "&q=" + query);
			break;
		}

	}

}