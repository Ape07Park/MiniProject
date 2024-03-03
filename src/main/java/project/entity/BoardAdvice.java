package project.entity;

import java.time.LocalDate;

public class BoardAdvice { // 경매 게시판
	private int bid; // 글 번호
	private String uid; // 작성자
	private String title; // 제목
	private String content; // 내용
	private LocalDate modTime; // 작성일
	private int isDeleted;
	private int viewCount; // 조회수
	private int replyCount; // 댓글 개수

	public BoardAdvice(int bid, String uid, String title, String content, LocalDate modTime, int isDeleted,
			int viewCount, int replyCount) {
		this.bid = bid;
		this.uid = uid;
		this.title = title;
		this.content = content;
		this.modTime = modTime;
		this.isDeleted = isDeleted;
		this.viewCount = viewCount;
		this.replyCount = replyCount;
	}

	public BoardAdvice() {
	}

	public BoardAdvice(int bid, String title, String content) {
		super();
		this.bid = bid;
		this.title = title;
		this.content = content;
	}

	public BoardAdvice(String uid, String title, String content) {
		this.uid = uid;
		this.title = title;
		this.content = content;
	}

	@Override
	public String toString() {
		return "BoardAdvice [bid=" + bid + ", uid=" + uid + ", title=" + title + ", content=" + content + ", modTime="
				+ modTime + ", isDeleted=" + isDeleted + ", viewCount=" + viewCount + ", replyCount=" + replyCount
				+ "]";
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
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

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
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