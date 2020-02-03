package vn.printgo.entities;

public class RTmpFile {
	
	private String name;
	private String nameMd;
	private String inTime;
	
	public RTmpFile() {}
	
	public RTmpFile(String name, String nameMd, String inTime) {
		this.name = name;
		this.nameMd = nameMd;
		this.inTime = inTime;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	
	public void setNameMd(String nameMd) {
		this.nameMd = nameMd;
	}
	
	public String getNameMd() {
		return this.nameMd;
	}
	
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	
	public String getInTime() {
		return this.inTime;
	}
}
