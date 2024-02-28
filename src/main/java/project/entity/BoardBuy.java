package project.entity;

import java.time.LocalDate;

public class BoardBuy { // 경매 게시판
	private int bid; // 게시판 번호
	private String uid; // 유저 아이디
	private LocalDate applTime; // 신청 시간
	private String nickName; // 닉네임
	private String processTitle; // 제목
	private String processContent; // 내용
	private int avgPrice; // 평균 가격
	private int numOfCompany; // 업체 수
	private int process; // 진행 사항 - 3가지
	private int isDeleted;

	public BoardBuy() {
	}

	public BoardBuy(int bid, String processTitle, String processContent) {
		this.bid = bid;
		this.processTitle = processTitle;
		this.processContent = processContent;
	}

	public BoardBuy(int bid, String uid, LocalDate applTime, String nickName, String processTitle,
			String processContent) {
		this.bid = bid;
		this.uid = uid;
		this.applTime = applTime;
		this.nickName = nickName;
		this.processTitle = processTitle;
		this.processContent = processContent;
	}

	public BoardBuy(int bid, String uid, LocalDate applTime, String nickName, String processTitle,
			String processContent, int avgPrice, int numOfCompany, int process, int isDeleted) {
		this.bid = bid;
		this.uid = uid;
		this.applTime = applTime;
		this.nickName = nickName;
		this.processTitle = processTitle;
		this.processContent = processContent;
		this.avgPrice = avgPrice;
		this.numOfCompany = numOfCompany;
		this.process = process;
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "BoardAuction [bid=" + bid + ", uid=" + uid + ", applTime=" + applTime + ", nickName=" + nickName
				+ ", processTitle=" + processTitle + ", processContent=" + processContent + ", avgPrice=" + avgPrice
				+ ", numOfCompany=" + numOfCompany + ", process=" + process + ", isDeleted=" + isDeleted + "]";
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

	public LocalDate getApplTime() {
		return applTime;
	}

	public void setApplTime(LocalDate applTime) {
		this.applTime = applTime;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getProcessTitle() {
		return processTitle;
	}

	public void setProcessTitle(String processTitle) {
		this.processTitle = processTitle;
	}

	public String getProcessContent() {
		return processContent;
	}

	public void setProcessContent(String processContent) {
		this.processContent = processContent;
	}

	public int getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(int avgPrice) {
		this.avgPrice = avgPrice;
	}

	public int getNumOfCompany() {
		return numOfCompany;
	}

	public void setNumOfCompany(int numOfCompany) {
		this.numOfCompany = numOfCompany;
	}

	public int getProcess() {
		return process;
	}

	public void setProcess(int process) {
		this.process = process;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

}