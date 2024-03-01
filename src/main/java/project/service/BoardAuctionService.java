package project.service;

import java.util.List;

import project.entity.BoardAdvice;
import project.entity.BoardAuction;
import project.entity.BoardBuy;

public interface BoardAuctionService {
	public static final int COUNT_PER_PAGE = 10;

	List<BoardAuction> getBoardList(int page, String field, String query, String pack);

	BoardAuction getBoard(int bid, String pack);

	List<BoardBuy> getBuyList(int page, String field, String query, String pack);

	BoardBuy getBuy(int bid, String pack);

	int getBoardCount(String field, String query, String pack);

	void insertBoard(BoardAuction boardAuc, String pack);

	void updateBoard(BoardAuction boardAuc, String pack);
	
	void insertBoard(BoardBuy boardBuy, String pack);

	void updateBoard(BoardBuy boardBuy, String pack);

	void deleteBoard(int bid, String pack);
//	
//	void increaseViewCount(int bid);
//	
//	void increaseReplyCount(int bid);

}