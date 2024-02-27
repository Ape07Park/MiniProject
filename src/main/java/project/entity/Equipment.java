package project.entity;

public class Equipment {
	private int inum;
	private String ename;
	
	public Equipment() {}
		
	public Equipment(int inum, String ename) {
		super();
		this.inum = inum;
		this.ename = ename;
	}

	public int getInum() {
		return inum;
	}

	public void setInum(int inum) {
		this.inum = inum;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	@Override
	public String toString() {
		return "Equipment [inum=" + inum + ", ename=" + ename + "]";
	}
	
	
}
