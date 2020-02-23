package DiGraph_A5;

import java.util.HashMap;

public class Vertex {
	
	long idNum; 
	String label;
	HashMap<String, Edge> inEdge; 
	HashMap<String, Edge> outEdge;
	public int degree; 
	public int distance; 
	public boolean visited; 
	
	public Vertex(long idNum, String label) {
		this.idNum = idNum; 
		this.label = label; 
		inEdge = new HashMap<String, Edge>(); 
		outEdge = new HashMap<String, Edge>(); 
		degree = 0; 
		//distance = 0; 
		visited = false; 
	}
	
	public long getidNum() {
		return this.idNum; 
	}
	
	public String getLabel() {
		return this.label; 
	}

	

}
