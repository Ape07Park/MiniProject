package project.entity;

public class Equipment {
	private int inum;
	private String category;
	private String eImg;
	private String ename;
	private String eContent;
	private int price;
	
	public Equipment() {}

	public Equipment(int inum, String category, String eImg, String ename, String eContent, int price) {
		super();
		this.inum = inum;
		this.category = category;
		this.eImg = eImg;
		this.ename = ename;
		this.eContent = eContent;
		this.price = price;
	}
	
	


	public Equipment(String category, String eImg, String ename, String eContent, int price) {
		super();
		this.category = category;
		this.eImg = eImg;
		this.ename = ename;
		this.eContent = eContent;
		this.price = price;
	}

	public int getInum() {
		return inum;
	}

	public void setInum(int inum) {
		this.inum = inum;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String geteImg() {
		return eImg;
	}

	public void seteImg(String eImg) {
		this.eImg = eImg;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String geteContent() {
		return eContent;
	}

	public void seteContent(String eContent) {
		this.eContent = eContent;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Equipment [inum=" + inum + ", category=" + category + ", eImg=" + eImg + ", ename=" + ename
				+ ", eContent=" + eContent + ", price=" + price + "]";
	}

	
	
}
