//package project.service;
//
//import java.util.List;
//
//import project.entity.Board3;
//
//
//public interface BoardAdviceService {
//	public static final int COUNT_PER_PAGE = 10;
//
//	List<Board3> getBoardList(int page, String field, String query);
//	
//	Board3 getBoard(int bid);
//	
//	int getBoardCount(String field, String query);
//	
//	void insertBoard(Board3 board3);
//	
//	void updateBoard(Board3 board3);
//	
//	void deleteBoard(int bid);
//	
//	void increaseViewCount(int bid);
//	
//	void increaseReplyCount(int bid);
//	
//}