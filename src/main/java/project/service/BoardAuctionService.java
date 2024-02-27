package project.service;

import java.util.List;

import project.entity.BoardAuction;


public interface BoardAuctionService {
	public static final int COUNT_PER_PAGE = 10;

	List<BoardAuction> getBoardList(int page, String field, String query);

	void insertBoard(BoardAuction boardAuc);
	
//	BoardAuction getBoard(int bid);
//	
//	int getBoardCount(String field, String query);
//	
//	void insertBoard(BoardAuction boardAuc);
//	
//	void updateBoard(BoardAuction boardAuc);
//	
//	void deleteBoard(int bid);
//	
//	void increaseViewCount(int bid);
//	
//	void increaseReplyCount(int bid);
	
}