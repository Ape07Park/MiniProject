package project.service;

import java.util.List;

import project.dao.BoardAdviceDao;
import project.entity.BoardAdvice;

public class BoardAdviceServiceImpl implements BoardAdviceService {
	private BoardAdviceDao bDao = new BoardAdviceDao();

	@Override
	public List<BoardAdvice> getBoardAdviceList(int page, String field, String query) {
		int offset = (page - 1) * COUNT_PER_PAGE;
		query = "%" + query + "%";
		List<BoardAdvice> list = bDao.getBoardAdviceList(field, query, COUNT_PER_PAGE, offset);
		return list;
	}

	@Override
	public BoardAdvice getBoard(int bid) {
		return bDao.getBoardAdvice(bid);
	}

	@Override
	public int getBoardCount(String field, String query) {
		return bDao.getBoardAdviceCount(field, query);
	}

	@Override
	public void insertBoard(BoardAdvice boardAdvice) {
		bDao.insertBoardAdvice(boardAdvice);
	}

	@Override
	public void updateBoard(BoardAdvice boardAdvice) {
		bDao.updateBoardAdvice(boardAdvice);
	}

	@Override
	public void deleteBoard(int bid) {
		bDao.deleteBoardAdvice(bid);
	}

	// 아래 두 함수는 boardDao의 increaeViewCount를 공유함.
	@Override
	public void increaseViewCount(int bid) {
		bDao.increaseCount("viewCount", bid);
	}

	@Override
	public void increaseReplyCount(int bid) {
		bDao.increaseCount("replyCount", bid);
	}

}