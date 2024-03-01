package project.entity;

public class Equipment {
	private int inum;
	private String category;
	private String ename;
	private String eContent;
	
	
	public Equipment() {}


	public Equipment(int inum, String category, String ename, String eContent) {
		super();
		this.inum = inum;
		this.category = category;
		this.ename = ename;
		this.eContent = eContent;
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


	@Override
	public String toString() {
		return "Equipment [inum=" + inum + ", category=" + category + ", ename=" + ename + ", eContent=" + eContent
				+ "]";
	}

	
	


	
	
}
