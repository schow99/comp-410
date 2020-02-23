package DiGraph_A5;

public class Edge {
	
	long idNum;
	String sLabel; 
	String dLabel; 
	long weight; 
	String eLabel; 
	
	public Edge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		this.idNum = idNum; 
		this.sLabel = sLabel; 
		this.dLabel = dLabel; 
		this.weight = weight; 
		this.eLabel = eLabel; 
	}
	
/*	public Edge(long idNum, String sLabel, String dLabel, String eLabel) {
		this.idNum = idNum; 
		this.sLabel = sLabel; 
		this.dLabel = dLabel; 
		this.weight = 1; 
		this.eLabel = eLabel; 
	}*/
	
	public long getIdNum() {
		return this.idNum; 
	}
	
	public String getsLabel() {
		return this.sLabel; 
	}
	
	public String getdLabel() {
		return this.dLabel; 
	}
	
	public long getWeight() {
		return this.weight; 
	}
	
	public String eLabel() {
		return this.eLabel; 
	}
	
	
}
