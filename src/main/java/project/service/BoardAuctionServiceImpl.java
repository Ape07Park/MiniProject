package project.service;

import java.util.List;

import project.dao.BoardAuctionDao;
import project.entity.BoardAdvice;
import project.entity.BoardAuction;
import project.entity.BoardBuy;

public class BoardAuctionServiceImpl implements BoardAuctionService {
	private BoardAuctionDao bDao = new BoardAuctionDao();

	@Override
	public List<BoardAuction> getBoardList(int page, String field, String query, String pack) {
		int offset = (page - 1) * COUNT_PER_PAGE;
		query = "%" + query + "%";
		List<BoardAuction> list = bDao.getBoardList(field, query, COUNT_PER_PAGE, offset, pack);
		return list;
	}

	@Override
	public List<BoardBuy> getBuyList(int page, String field, String query, String pack) {
		int offset = (page - 1) * COUNT_PER_PAGE;
		query = "%" + query + "%";
		List<BoardBuy> list = bDao.getBuyList(field, query, COUNT_PER_PAGE, offset, pack);
		return list;
	}

	@Override
	public BoardBuy getBuy(int bid, String pack) {
		return bDao.getBuy(bid, pack);
	}

	@Override
	public BoardAuction getBoard(int bid, String pack) {
		return bDao.getBoard(bid, pack);
	}

	@Override
	public int getBoardCount(String field, String query, String pack) {
		return bDao.getBoardCount(field, query, pack);
	}

	@Override
	public void insertBoard(BoardAuction boardAuc, String pack) {
		bDao.insertBoard(boardAuc, pack);
	}

	@Override
	public void updateBoard(BoardAuction boardAuc, String pack) {
		bDao.updateBoard(boardAuc, pack);
	}

	@Override
	public void insertBoard(BoardBuy boardBuy, String pack) {
		bDao.insertBoard(boardBuy, pack);
	}

	@Override
	public void updateBoard(BoardBuy boardBuy, String pack) {
		bDao.updateBoard(boardBuy, pack);
	}
	
	@Override
	public void deleteBoard(int bid, String pack) {
		bDao.deleteBoard(bid, pack);
	}
//
//	// 아래 두 함수는 boardDao의 increaeViewCount를 공유함.
//	@Override
//	public void increaseViewCount(int bid) {
//		bDao.increaseCount("viewCount", bid);
//	}
//
//	@Override
//	public void increaseReplyCount(int bid) {
//		bDao.increaseCount("replyCount", bid);
//	}



}