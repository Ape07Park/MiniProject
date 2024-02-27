package project.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import project.entity.BoardAuction;
import project.service.BoardAuctionService;
import project.service.BoardAuctionServiceImpl;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet({ "/1.mini/board/listAuction", "/1.mini/board/insertAuction", "/1.mini/board/updateAuction",
		"/1.mini/board/deleteAuction", "/1.mini/board/detailAuction", "/1.mini/board/listBuy",
		"/1.mini/board/insertBuy", "/1.mini/board/updateBuy", "/1.mini/board/deleteBuy", "/1.mini/board/detailBuy" })
public class BoardAuctionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardAuctionService bAucSvc = new BoardAuctionServiceImpl();

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] uri = request.getRequestURI().split("/");
		String action = uri[uri.length - 1];
		String method = request.getMethod();
		HttpSession session = request.getSession();
		String sessUid = (String) session.getAttribute("sessUid");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		LocalDate applTime = null; 
		RequestDispatcher rd = null;
		String processTitle = null, processContent = null, nickName = null, field = null, query = null, uid = null,
				page_ = null;
		BoardAuction boardAuc = null;
		int bid = 0;
		int page = 0;

		switch (action) {
		case "listAuction": {// /mp/1.mini/boardAuction/list?p=1&f=title&q=검색
			page_ = request.getParameter("p");
			field = request.getParameter("f");
			query = request.getParameter("q");
			page = (page_ == null || page_.equals("")) ? 1 : Integer.parseInt(page_);
			session.setAttribute("currentBoardPage", page);
			field = (field == null || field.equals("")) ? "processTitle" : field;
			query = (query == null || query.equals("")) ? "" : query;
			session.setAttribute("field", field);
			session.setAttribute("query", query);
			List<BoardAuction> boardList = bAucSvc.getBoardList(page, field, query);
			request.setAttribute("boardList", boardList);

//			// for pagination
//			int totalItems = bAucSvc.getBoardCount(field, query);
//			int totalPages = (int) Math.ceil(totalItems * 1.0 / bAucSvc.COUNT_PER_PAGE);
//			List<String> pageList = new ArrayList<String>();
//			for (int i = 1; i <= totalPages; i++)
//				pageList.add(String.valueOf(i));
//			request.setAttribute("pageList", pageList);

			rd = request.getRequestDispatcher("/WEB-INF/view/boardAuction/listAuction.jsp");
			rd.forward(request, response);
			break;
		}
		case "insertAuction": {
			if (sessUid == null || sessUid.equals("")) {
				response.sendRedirect("/mp/1.mini/user/login");
				break;
			}
			if (method.equals("GET")) {
				rd = request.getRequestDispatcher("/WEB-INF/view/boardAuction/insertAuction.jsp");
				rd.forward(request, response);
			} else {
				applTime = LocalDate.now();
				processTitle = request.getParameter("processTitle");
				processContent = request.getParameter("processContent");
				nickName = request.getParameter("nickName");
				boardAuc = new BoardAuction(applTime, processTitle, processContent, nickName);
				bAucSvc.insertBoard(boardAuc);
				response.sendRedirect("/mp/1.mini/board/listAuction");
				System.out.println(boardAuc);
			}
			break;
		}
//		case "detailAuction": {
//		bid = Integer.parseInt(request.getParameter("bid"));
//		uid = request.getParameter("uid");
//		if (!uid.equals(sessUid))
//			bAucSvc.increaseViewCount(bid);
//
//		boardAuc = bAucSvc.getBoard(bid);
//		request.setAttribute("board", boardAuc);
//
//		List<Reply> replyList = null; // 댓글 목록 필요 - 2024 02 22 현재 미구현
//		request.setAttribute("replyList", replyList);
//
//		rd = request.getRequestDispatcher("/WEB-INF/view/board/detail.jsp");
//		rd.forward(request, response);
//		break;
//	}
		
////		
//	case "deleteAuction": {
//		bid = Integer.parseInt(request.getParameter("bid"));
//		bAucSvc.deleteBoard(bid);
//		page = (Integer) session.getAttribute("currentBoardPage");
//		field = (String) session.getAttribute("field");
//		query = (String) session.getAttribute("query");
//		query = URLEncoder.encode(query, "utf-8");
//		response.sendRedirect("/mp/1.mini/board/list?p=" + page + "&f=" + field + "&q=" + query);
//		break;
//	}
//		
//	case "updateAuction": {
//		if (method.equals("GET")) {
//			bid = Integer.parseInt(request.getParameter("bid"));
//			board3 = bAucSvc.getBoard(bid);
//			request.setAttribute("board", board3);
//			rd = request.getRequestDispatcher("/WEB-INF/view/board/update.jsp");
//			rd.forward(request, response);
//		} else {
//			bid = Integer.parseInt(request.getParameter("bid"));
//			uid = request.getParameter("uid");
//			title = request.getParameter("title");
//			content = request.getParameter("content");
//			boardAuc = new BoardAuction(bid, title, content);
//
//			bAucSvc.updateBoard(boardAuc);
//			response.sendRedirect("/mp/1.mini/board/detail?bid=" + bid + "&uid=" + uid);
//		}
//		break;
//	}

//	case "listBuy": {// /mp/1.mini/board/list?p=1&f=title&q=검색
//		page_ = request.getParameter("p");
//		field = request.getParameter("f");
//		query = request.getParameter("q");
//		page = (page_ == null || page_.equals("")) ? 1 : Integer.parseInt(page_);
//		session.setAttribute("currentBoardPage", page);
//		field = (field == null || field.equals("")) ? "title" : field;
//		query = (query == null || query.equals("")) ? "" : query;
//		session.setAttribute("field", field);
//		session.setAttribute("query", query);
//		List<BoardAuction> boardList = bAucSvc.getBoardList(page, field, query);
//		request.setAttribute("boardList", boardList);
////
////		// for pagination
////		int totalItems = bAucSvc.getBoardCount(field, query);
////		int totalPages = (int) Math.ceil(totalItems * 1.0 / bAucSvc.COUNT_PER_PAGE);
////		List<String> pageList = new ArrayList<String>();
////		for (int i = 1; i <= totalPages; i++)
////			pageList.add(String.valueOf(i));
////		request.setAttribute("pageList", pageList);
////
//		rd = request.getRequestDispatcher("/WEB-INF/view/boardBuy/listBuy.jsp");
//		rd.forward(request, response);
//		break;
////	}
		//
//		case "insertBuy": {
//
//			if (sessUid == null || sessUid.equals("")) {
//				response.sendRedirect("/mp/1.mini/user/login");
//				// 이것만 있으면 오류 코드 - sendRedirect와 forward 중 한번만 쓸 수 있음.
//				break; // 이후에 forward 를 할 수 없게 함.
//			}
//			if (method.equals("GET")) {
//				rd = request.getRequestDispatcher("/WEB-INF/view/board/insert.jsp");
//				rd.forward(request, response);
//			} else {
//				title = request.getParameter("title");
//				content = request.getParameter("content");
//				boardAuc = new BoardAuction(title, content, sessUid);
//				bAucSvc.insertBoard(boardAuc);
//				response.sendRedirect("/mp/1.mini/board/list?p=1");
//			}
//			break;
//		}
//		
//
//		case "detailBuy": {
//			bid = Integer.parseInt(request.getParameter("bid"));
//			uid = request.getParameter("uid");
//			if (!uid.equals(sessUid))
//				bAucSvc.increaseViewCount(bid);
//
//			boardAuc = bAucSvc.getBoard(bid);
//			request.setAttribute("board", boardAuc);
//
//			List<Reply> replyList = null; // 댓글 목록 필요 - 2024 02 22 현재 미구현
//			request.setAttribute("replyList", replyList);
//
//			rd = request.getRequestDispatcher("/WEB-INF/view/board/detail.jsp");
//			rd.forward(request, response);
//			break;
//		}
//		
//		
//		case "deleteBuy": {
//			bid = Integer.parseInt(request.getParameter("bid"));
//			bAucSvc.deleteBoard(bid);
//			page = (Integer) session.getAttribute("currentBoardPage");
//			field = (String) session.getAttribute("field");
//			query = (String) session.getAttribute("query");
//			query = URLEncoder.encode(query, "utf-8");
//			response.sendRedirect("/mp/1.mini/board/list?p=" + page + "&f=" + field + "&q=" + query);
//			break;
//		}
//		
//
//		case "updateBuy": {
//			if (method.equals("GET")) {
//				bid = Integer.parseInt(request.getParameter("bid"));
//				boardAuc = bAucSvc.getBoard(bid);
//				request.setAttribute("board", boardAuc);
//				rd = request.getRequestDispatcher("/WEB-INF/view/board/update.jsp");
//				rd.forward(request, response);
//			} else {
//				bid = Integer.parseInt(request.getParameter("bid"));
//				uid = request.getParameter("uid");
//				title = request.getParameter("title");
//				content = request.getParameter("content");
//				boardAuc = new BoardAuction(bid, title, content);
//
//				bAucSvc.updateBoard(boardAuc);
//				response.sendRedirect("/mp/1.mini/board/detail?bid=" + bid + "&uid=" + uid);
//			}
//			break;
//		}
//	
//
		}
	}
}