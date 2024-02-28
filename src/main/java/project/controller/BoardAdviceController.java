//package project.controller;
//
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import project.entity.Board3;
//import project.entity.BoardAuction;
//import project.service.BoardAuctionService;
//import project.service.BoardAdviceService;
//import project.service.BoardService3;
//import project.service.BoardAuctionServiceImpl;
//import project.service.BoardAdviceServiceImpl;
//import project.service.BoardServiceImpl3;
//import project.나중에.Reply;
//
//import java.io.IOException;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.List;
//
//@WebServlet({ "/mini/board/listBoardAuction", "/mini/board/insertBoardAuction", "/mini/board/updateBoardAuction",
//		"/mini/board/deleteBoardAuction", "/mini/board/detailBoardAuction", "/mini/board/listBoardBuy",
//		"/mini/board/insertBoardBuy", "/mini/board/updateBoardBuy", "/mini/board/deleteBoardBuy",
//		"/mini/board/detailBoardBuy"})
//public class BoardAdviceController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	private BoardAuctionService b1Svc = new BoardAuctionServiceImpl();
//
//	protected void service(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String[] uri = request.getRequestURI().split("/");
//		String action = uri[uri.length - 1];
//		String method = request.getMethod();
//		HttpSession session = request.getSession();
//		String sessUid = (String) session.getAttribute("sessUid");
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html; charset=utf-8");
//
//		RequestDispatcher rd = null;
//		String title = null, content = null, field = null, query = null, uid = null, page_ = null;
//		BoardAuction boardAuc = null;
//		int bid = 0;
//		int page = 0;
//
//		switch (action) {
//		case "listBoardAuction": {// /mp/mini/board/list?p=1&f=title&q=검색
//			page_ = request.getParameter("p");
//			field = request.getParameter("f");
//			query = request.getParameter("q");
//			page = (page_ == null || page_.equals("")) ? 1 : Integer.parseInt(page_);
//			session.setAttribute("currentBoardPage", page);
//			field = (field == null || field.equals("")) ? "title" : field;
//			query = (query == null || query.equals("")) ? "" : query;
//			session.setAttribute("field", field);
//			session.setAttribute("query", query);
//			List<Board3> boardList = b1Svc.getBoardList(page, field, query);
//			request.setAttribute("boardList", boardList);
//
//			// for pagination
//			int totalItems = b1Svc.getBoardCount(field, query);
//			int totalPages = (int) Math.ceil(totalItems * 1.0 / b1Svc.COUNT_PER_PAGE);
//			List<String> pageList = new ArrayList<String>();
//			for (int i = 1; i <= totalPages; i++)
//				pageList.add(String.valueOf(i));
//			request.setAttribute("pageList", pageList);
//
//			rd = request.getRequestDispatcher("/WEB-INF/view/board/list.jsp");
//			rd.forward(request, response);
//			break;
//		}
//		case "listBoardBuy": {// /mp/mini/board/list?p=1&f=title&q=검색
//			page_ = request.getParameter("p");
//			field = request.getParameter("f");
//			query = request.getParameter("q");
//			page = (page_ == null || page_.equals("")) ? 1 : Integer.parseInt(page_);
//			session.setAttribute("currentBoardPage", page);
//			field = (field == null || field.equals("")) ? "title" : field;
//			query = (query == null || query.equals("")) ? "" : query;
//			session.setAttribute("field", field);
//			session.setAttribute("query", query);
//			List<Board3> boardList = b2Svc.getBoardList(page, field, query);
//			request.setAttribute("boardList", boardList);
//
//			// for pagination
//			int totalItems = b2Svc.getBoardCount(field, query);
//			int totalPages = (int) Math.ceil(totalItems * 1.0 / b2Svc.COUNT_PER_PAGE);
//			List<String> pageList = new ArrayList<String>();
//			for (int i = 1; i <= totalPages; i++)
//				pageList.add(String.valueOf(i));
//			request.setAttribute("pageList", pageList);
//
//			rd = request.getRequestDispatcher("/WEB-INF/view/board/list.jsp");
//			rd.forward(request, response);
//			break;
//		}
//		
//	
//		case "insertBoardAuction": {
//
//			if (sessUid == null || sessUid.equals("")) {
//				response.sendRedirect("/mp/mini/user/login");
//				// 이것만 있으면 오류 코드 - sendRedirect와 forward 중 한번만 쓸 수 있음.
//				break; // 이후에 forward 를 할 수 없게 함.
//			}
//			if (method.equals("GET")) {
//				rd = request.getRequestDispatcher("/WEB-INF/view/board/insert.jsp");
//				rd.forward(request, response);
//			} else {
//				title = request.getParameter("title");
//				content = request.getParameter("content");
//				board3 = new Board3(title, content, sessUid);
//				b1Svc.insertBoard(board3);
//				response.sendRedirect("/mp/mini/board/list?p=1");
//			}
//			break;
//		}
//		case "insertBoardBuy": {
//
//			if (sessUid == null || sessUid.equals("")) {
//				response.sendRedirect("/mp/mini/user/login");
//				// 이것만 있으면 오류 코드 - sendRedirect와 forward 중 한번만 쓸 수 있음.
//				break; // 이후에 forward 를 할 수 없게 함.
//			}
//			if (method.equals("GET")) {
//				rd = request.getRequestDispatcher("/WEB-INF/view/board/insert.jsp");
//				rd.forward(request, response);
//			} else {
//				title = request.getParameter("title");
//				content = request.getParameter("content");
//				board3 = new Board3(title, content, sessUid);
//				b2Svc.insertBoard(board3);
//				response.sendRedirect("/mp/mini/board/list?p=1");
//			}
//			break;
//		}
//		
//		case "detailBoardAuction": {
//			bid = Integer.parseInt(request.getParameter("bid"));
//			uid = request.getParameter("uid");
//			if (!uid.equals(sessUid))
//				b1Svc.increaseViewCount(bid);
//
//			board3 = b1Svc.getBoard(bid);
//			request.setAttribute("board", board3);
//
//			List<Reply> replyList = null; // 댓글 목록 필요 - 2024 02 22 현재 미구현
//			request.setAttribute("replyList", replyList);
//
//			rd = request.getRequestDispatcher("/WEB-INF/view/board/detail.jsp");
//			rd.forward(request, response);
//			break;
//		}
//		case "detailBoardBuy": {
//			bid = Integer.parseInt(request.getParameter("bid"));
//			uid = request.getParameter("uid");
//			if (!uid.equals(sessUid))
//				b2Svc.increaseViewCount(bid);
//
//			board3 = b2Svc.getBoard(bid);
//			request.setAttribute("board", board3);
//
//			List<Reply> replyList = null; // 댓글 목록 필요 - 2024 02 22 현재 미구현
//			request.setAttribute("replyList", replyList);
//
//			rd = request.getRequestDispatcher("/WEB-INF/view/board/detail.jsp");
//			rd.forward(request, response);
//			break;
//		}
//		
//		case "deleteBoardAuction": {
//			bid = Integer.parseInt(request.getParameter("bid"));
//			b1Svc.deleteBoard(bid);
//			page = (Integer) session.getAttribute("currentBoardPage");
//			field = (String) session.getAttribute("field");
//			query = (String) session.getAttribute("query");
//			query = URLEncoder.encode(query, "utf-8");
//			response.sendRedirect("/mp/mini/board/list?p=" + page + "&f=" + field + "&q=" + query);
//			break;
//		}
//		case "deleteBoardBuy": {
//			bid = Integer.parseInt(request.getParameter("bid"));
//			b2Svc.deleteBoard(bid);
//			page = (Integer) session.getAttribute("currentBoardPage");
//			field = (String) session.getAttribute("field");
//			query = (String) session.getAttribute("query");
//			query = URLEncoder.encode(query, "utf-8");
//			response.sendRedirect("/mp/mini/board/list?p=" + page + "&f=" + field + "&q=" + query);
//			break;
//		}
//		
//		case "updateBoardAuction": {
//			if (method.equals("GET")) {
//				bid = Integer.parseInt(request.getParameter("bid"));
//				board3 = b1Svc.getBoard(bid);
//				request.setAttribute("board", board3);
//				rd = request.getRequestDispatcher("/WEB-INF/view/board/update.jsp");
//				rd.forward(request, response);
//			} else {
//				bid = Integer.parseInt(request.getParameter("bid"));
//				uid = request.getParameter("uid");
//				title = request.getParameter("title");
//				content = request.getParameter("content");
//				board3 = new Board3(bid, title, content);
//
//				b1Svc.updateBoard(board3);
//				response.sendRedirect("/mp/mini/board/detail?bid=" + bid + "&uid=" + uid);
//			}
//			break;
//		}
//		case "updateBoardBuy": {
//			if (method.equals("GET")) {
//				bid = Integer.parseInt(request.getParameter("bid"));
//				board3 = b2Svc.getBoard(bid);
//				request.setAttribute("board", board3);
//				rd = request.getRequestDispatcher("/WEB-INF/view/board/update.jsp");
//				rd.forward(request, response);
//			} else {
//				bid = Integer.parseInt(request.getParameter("bid"));
//				uid = request.getParameter("uid");
//				title = request.getParameter("title");
//				content = request.getParameter("content");
//				board3 = new Board3(bid, title, content);
//
//				b2Svc.updateBoard(board3);
//				response.sendRedirect("/mp/mini/board/detail?bid=" + bid + "&uid=" + uid);
//			}
//			break;
//		}
//	
//
//		}
//	}
//}
package project.controller;

