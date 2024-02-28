package project.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import project.entity.BoardAuction;
import project.entity.BoardBuy;
import project.entity.Reply;
import project.service.BoardAuctionService;
import project.service.BoardAuctionServiceImpl;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet({ "/mini/board/listAuction", "/mini/board/insertAuction", "/mini/board/updateAuction",
		"/mini/board/deleteAuction", "/mini/board/detailAuction", "/mini/board/listBuy",
		"/mini/board/insertBuy", "/mini/board/updateBuy", "/mini/board/deleteBuy", "/mini/board/detailBuy" })
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
		String sessNickName = (String) session.getAttribute("sessNickName");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		LocalDate applTime = null;
		RequestDispatcher rd = null;
		String processTitle = null, processContent = null, nickName = null, field = null, query = null, uid = null,
				page_ = null;
		BoardAuction boardAuc = null;
		BoardBuy boardBuy = null;
		int bid = 0;
		int page = 0;
		String pack = null;

		switch (action) {
		case "listAuction": {// /mp/mini/boardAuction/list?p=1&f=title&q=검색
			pack = "boardAuction";
			page_ = request.getParameter("p");
			field = request.getParameter("f");
			query = request.getParameter("q");
			page = (page_ == null || page_.equals("")) ? 1 : Integer.parseInt(page_);
			session.setAttribute("currentBoardPage", page);
			field = (field == null || field.equals("")) ? "processTitle" : field;
			query = (query == null || query.equals("")) ? "" : query;
			session.setAttribute("field", field);
			session.setAttribute("query", query);
			List<BoardAuction> boardList = bAucSvc.getBoardList(page, field, query, pack);
			request.setAttribute("boardList", boardList);

			// for pagination
			int totalItems = bAucSvc.getBoardCount(field, query, pack);
			int totalPages = (int) Math.ceil(totalItems * 1.0 / bAucSvc.COUNT_PER_PAGE);
			List<String> pageList = new ArrayList<String>();
			for (int i = 1; i <= totalPages; i++)
				pageList.add(String.valueOf(i));
			request.setAttribute("pageList", pageList);

			rd = request.getRequestDispatcher("/WEB-INF/view/boardAuction/listAuction.jsp");
			rd.forward(request, response);
			break;
		}
		case "insertAuction": {
			pack = "boardAuction";
			if (sessUid == null || sessUid.equals("")) {
				response.sendRedirect("/mp/mini/user/login");
				break;
			}
			if (method.equals("GET")) {
				rd = request.getRequestDispatcher("/WEB-INF/view/boardAuction/insertAuction.jsp");
				rd.forward(request, response);
			} else {
				String bid_ = request.getParameter("bid");
				bid = (bid_ == null || bid_.equals("")) ? 1 : Integer.parseInt(bid_);
				applTime = LocalDate.now();
				uid = sessUid;
				nickName = sessNickName;
				processTitle = request.getParameter("processTitle");
				processContent = request.getParameter("processContent");
				boardAuc = new BoardAuction(bid, uid, applTime, sessNickName, processTitle, processContent);
				bAucSvc.insertBoard(boardAuc, pack);
				response.sendRedirect("/mp/mini/board/listAuction");
			}
			break;
		}
		case "detailAuction": {
			pack = "boardAuction";
			String bid_ = request.getParameter("bid");
			bid = (bid_ == null || bid_.equals("")) ? 1 : Integer.parseInt(bid_);
			uid = request.getParameter("uid");

			boardAuc = bAucSvc.getBoard(bid, pack);
			request.setAttribute("board", boardAuc);

			List<Reply> replyList = null;
			request.setAttribute("replyList", replyList);

			rd = request.getRequestDispatcher("/WEB-INF/view/boardAuction/detailAuction.jsp");
			rd.forward(request, response);
			break;
		}

		case "deleteAuction": {
			pack = "boardAuction";
			bid = Integer.parseInt(request.getParameter("bid"));
			bAucSvc.deleteBoard(bid, pack);
			page = (Integer) session.getAttribute("currentBoardPage");
			response.sendRedirect("/mp/mini/board/listAuction?p=" + page);
			break;
		}

		case "updateAuction": {
			pack = "boardAuction";
			if (method.equals("GET")) {
				bid = Integer.parseInt(request.getParameter("bid"));
				boardAuc = bAucSvc.getBoard(bid, pack);
				request.setAttribute("board", boardAuc);
				rd = request.getRequestDispatcher("/WEB-INF/view/boardAuction/updateAuction.jsp");
				rd.forward(request, response);
			} else {
				bid = Integer.parseInt(request.getParameter("bid"));
				uid = request.getParameter("uid");
				processTitle = request.getParameter("processTitle");
				processContent = request.getParameter("processContent");
				boardAuc = new BoardAuction(bid, processTitle, processContent);

				bAucSvc.updateBoard(boardAuc, pack);
				response.sendRedirect("/mp/mini/board/detailAuction?bid=" + bid + "&uid=" + uid);
			}
			break;
		}

		case "listBuy": {// /mp/mini/boardBuy/list?p=1&f=title&q=검색
			pack = "boardBuy";
			page_ = request.getParameter("p");
			field = request.getParameter("f");
			query = request.getParameter("q");
			page = (page_ == null || page_.equals("")) ? 1 : Integer.parseInt(page_);
			session.setAttribute("currentBoardPage", page);
			field = (field == null || field.equals("")) ? "processTitle" : field;
			query = (query == null || query.equals("")) ? "" : query;
			session.setAttribute("field", field);
			session.setAttribute("query", query);
			List<BoardBuy> boardList = bAucSvc.getBuyList(page, field, query, pack);
			request.setAttribute("boardList", boardList);

			// for pagination
			int totalItems = bAucSvc.getBoardCount(field, query, pack);
			int totalPages = (int) Math.ceil(totalItems * 1.0 / bAucSvc.COUNT_PER_PAGE);
			List<String> pageList = new ArrayList<String>();
			for (int i = 1; i <= totalPages; i++)
				pageList.add(String.valueOf(i));
			request.setAttribute("pageList", pageList);

			rd = request.getRequestDispatcher("/WEB-INF/view/boardBuy/listBuy.jsp");
			rd.forward(request, response);
			break;
		}
		case "insertBuy": {
			pack = "boardBuy";
			if (sessUid == null || sessUid.equals("")) {
				response.sendRedirect("/mp/mini/user/login");
				break;
			}
			if (method.equals("GET")) {
				rd = request.getRequestDispatcher("/WEB-INF/view/boardBuy/insertBuy.jsp");
				rd.forward(request, response);
			} else {
				String bid_ = request.getParameter("bid");
				bid = (bid_ == null || bid_.equals("")) ? 1 : Integer.parseInt(bid_);
				applTime = LocalDate.now();
				uid = sessUid;
				nickName = sessNickName;
				processTitle = request.getParameter("processTitle");
				processContent = request.getParameter("processContent");
				boardBuy = new BoardBuy(bid, uid, applTime, sessNickName, processTitle, processContent);
				bAucSvc.insertBoard(boardAuc, pack);
				response.sendRedirect("/mp/mini/board/listBuy");
			}
			break;
		}
		case "detailBuy": {
			pack = "boardBuy";
			String bid_ = request.getParameter("bid");
			bid = (bid_ == null || bid_.equals("")) ? 1 : Integer.parseInt(bid_);
			uid = request.getParameter("uid");

			boardAuc = bAucSvc.getBoard(bid, pack);
			request.setAttribute("board", boardAuc);

			List<Reply> replyList = null;
			request.setAttribute("replyList", replyList);

			rd = request.getRequestDispatcher("/WEB-INF/view/boardBuy/detailBuy.jsp");
			rd.forward(request, response);
			break;
		}

		case "deleteBuy": {
			pack = "boardBuy";
			bid = Integer.parseInt(request.getParameter("bid"));
			bAucSvc.deleteBoard(bid, pack);
			page = (Integer) session.getAttribute("currentBoardPage");
			response.sendRedirect("/mp/mini/board/listBuy?p=" + page);
			break;
		}

		case "updateBuy": {
			pack = "boardBuy";
			if (method.equals("GET")) {
				bid = Integer.parseInt(request.getParameter("bid"));
				boardAuc = bAucSvc.getBoard(bid, pack);
				request.setAttribute("board", boardAuc);
				rd = request.getRequestDispatcher("/WEB-INF/view/boardBuy/updateBuy.jsp");
				rd.forward(request, response);
			} else {
				bid = Integer.parseInt(request.getParameter("bid"));
				uid = request.getParameter("uid");
				processTitle = request.getParameter("processTitle");
				processContent = request.getParameter("processContent");
				boardBuy = new BoardBuy(bid, processTitle, processContent);

				bAucSvc.updateBoard(boardAuc, pack);
				response.sendRedirect("/mp/mini/board/detailBuy?bid=" + bid + "&uid=" + uid);
			}
			break;
		}

		}
	}
}