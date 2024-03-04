package project.service;

import java.util.List;

import project.entity.BoardAdvice;

public interface BoardAdviceService {
	public static final int COUNT_PER_PAGE = 10;

	List<BoardAdvice> getBoardAdviceList(int page, String field, String query);

	BoardAdvice getBoardAdvice(int bid);

	int getBoardAdviceCount(String field, String query);

	void insertBoard(BoardAdvice boardAdvice);

	void updateBoard(BoardAdvice boardAdvice);

	void deleteBoard(int bid);

	void increaseViewCount(int bid);

	void increaseReplyCount(int bid);

//	BoardAdvice getBoardAdvice(int bid);

}