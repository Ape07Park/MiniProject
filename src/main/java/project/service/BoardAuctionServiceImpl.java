package project.service;

import java.util.List; 

import project.dao.BoardAuctionDao;
import project.entity.BoardAuction;

public class BoardAuctionServiceImpl implements BoardAuctionService {
	private BoardAuctionDao bDao = new BoardAuctionDao();

	@Override
	public List<BoardAuction> getBoardList(int page, String field, String query) {
		int offset = (page - 1) * COUNT_PER_PAGE;
		query = "%" + query + "%";
		List<BoardAuction> list = bDao.getBoardList(field, query, COUNT_PER_PAGE);
		return list;
	}

	@Override
	public void insertBoard(BoardAuction boardAuc) {
		bDao.insertBoard(boardAuc);
	}

//	@Override
//	public BoardAuction getBoard(int bid) {
//		return bDao.getBoard(bid);
//	}
//
//	@Override
//	public int getBoardCount(String field, String query) {
//		return bDao.getBoardCount(field, query);
//	}
//
//	@Override
//	public void insertBoard(BoardAuction boardAuction) {
//		bDao.insertBoard(boardAuction);
//	}
//
//	@Override
//	public void updateBoard(BoardAuction boardAuction) {
//		bDao.updateBoard(boardAuction);
//	}
//
//	@Override
//	public void deleteBoard(int bid) {
//		bDao.deleteBoard(bid);
//	}
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