//package project.service;
//
//import java.util.List;
//
//import project.dao.Board3Dao;
//import project.entity.Board3;
//
//public class BoardAdviceServiceImpl implements BoardAdviceService {
//	private Board3Dao bDao = new Board3Dao();
//
//	@Override
//	public List<Board3> getBoardList(int page, String field, String query) {
//		int offset = (page - 1) * COUNT_PER_PAGE;
//		query = "%" + query + "%";
//		List<Board3> list = bDao.getBoardList(field, query, COUNT_PER_PAGE, offset);
//		return list;
//	}
//
//	@Override
//	public Board3 getBoard(int bid) {
//		return bDao.getBoard(bid);
//	}
//
//	@Override
//	public int getBoardCount(String field, String query) {
//		return bDao.getBoardCount(field, query);
//	}
//
//	@Override
//	public void insertBoard(Board3 board3) {
//		bDao.insertBoard(board3);
//	}
//
//	@Override
//	public void updateBoard(Board3 board3) {
//		bDao.updateBoard(board3);
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
//
//}