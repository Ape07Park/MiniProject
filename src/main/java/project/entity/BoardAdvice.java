package project.entity;

import java.time.LocalDate;

public class BoardAdvice {  	// 경매 게시판
	private int bid; 			// 글 번호
	private String uname; 		// 작성자
	private String title; 		// 제목
	private String content; 	// 내용
	private LocalDate modTime; 	// 작성일
	private int viewCount; 		// 조회수
	private int replyCount; 	// 댓글 개수

	public BoardAdvice(int bid, String uname, String title, String content, LocalDate modTime, int viewCount,
			int replyCount) {
		super();
		this.bid = bid;
		this.uname = uname;
		this.title = title;
		this.content = content;
		this.modTime = modTime;
		this.viewCount = viewCount;
		this.replyCount = replyCount;
	}

	public BoardAdvice() {
		super();
	}

	@Override
	public String toString() {
		return "BoardAuction [bid=" + bid + ", uname=" + uname + ", title=" + title + ", content=" + content
				+ ", modTime=" + modTime + ", viewCount=" + viewCount + ", replyCount=" + replyCount + "]";
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDate getModTime() {
		return modTime;
	}

	public void setModTime(LocalDate modTime) {
		this.modTime = modTime;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

}
