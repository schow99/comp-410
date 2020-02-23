package DiGraph_A5;

import java.util.*;
import java.util.Map.Entry;

public class DiGraph implements DiGraphInterface {

	private HashMap<String, Vertex> map = new HashMap<String, Vertex>(); // key of the string label and value of vertex
//	private HashSet<Long> verticesId = new HashSet<Long>(); //list of vertex ids 
//	private HashSet<Long> edgesId = new HashSet<Long>(); //list of edge ids
	private List<Long> verticesId = new ArrayList();
	private List<Long> edgesId = new ArrayList();

	// in here go all your data and methods for the graph

	public DiGraph() { // default constructor
		// explicitly include this
		// we need to have the default constructor
		// if you then write others, this one will still be there
	}

	@Override
	public boolean addNode(long idNum, String label) {
		if (idNum < 0) { // checking cases where we can't add node 
			return false;
		}
		if (verticesId.contains(idNum)) {
			return false;
		}
		if (map.containsKey(label)) {
			return false;
		}
		if (label == null) {
			return false;
		} else {
			Vertex vertex = new Vertex(idNum, label); //creating new vertex
			map.put(label, vertex); //adding it to our list of strings and vertices 
			verticesId.add(idNum); // add id number to list of vertices id 
			return true;
		}
	}

	@Override
	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		
		if (edgesId.contains(idNum)) { //conditions to not add edge 
			return false;
		}
		if (idNum < 0) {
			return false;
		}
		if (map.get(sLabel) == null) {
			return false;
		}
		if (map.get(dLabel) == null) {
			return false;
		}
		Vertex source = map.get(sLabel); //getting source vertex 
		Vertex destination = map.get(dLabel); //getting destination vertex 
		if (source.outEdge.containsKey(dLabel) && destination.inEdge.containsKey(sLabel)) { //checking if there is an existing edge 
			return false;
		} else {
			Edge edge = new Edge(idNum, sLabel, dLabel, weight, eLabel); // adding edge 
			destination.inEdge.put(sLabel, edge); //creating inedge to destination vertex 
			source.outEdge.put(dLabel, edge); //creating outedge to source vertex 
			edgesId.add(idNum); //add new edge to list of existing edges 
			destination.degree++; //increase the destination node degree 
			return true;
		}

	}

	@Override
	public boolean delNode(String label) {
		if (!map.containsKey(label)) { //if the vertex doesn't exist 
			return false;
		}
		Vertex del = map.get(label); //vertex to delete 
		Iterator<Entry<String, Edge>> iter = del.outEdge.entrySet().iterator(); //creating iterator for node's outedges 
		while (iter.hasNext()) { //while there is a next element
			Edge remEdge = iter.next().getValue(); //edge to remove 
			map.get(remEdge.dLabel).inEdge.remove(label); //remove one edge to vertex  
			del.outEdge.remove(remEdge.dLabel); //remove destination 
			edgesId.remove(remEdge.idNum); //remove edge from list 
		}
		map.remove(label); //remove entire vertex after all edges have been deleted 
		verticesId.remove(del.idNum); //remove id 
		return true;

	}

	@Override
	public boolean delEdge(String sLabel, String dLabel) {
		if (!map.containsKey(sLabel)) { //if vertex doesn't exist 
			return false;
		}
		if (!map.containsKey(dLabel)) { 
			return false;
		} else {
			Vertex destination = map.get(dLabel); //destination vertex from edge 
			Vertex source = map.get(sLabel); //source vertex from edge 
			if (!source.outEdge.containsKey(dLabel)) {  //if they're isn't an edge 
				return false;
			}
			Edge remove = map.get(sLabel).outEdge.get(dLabel); //edge to remove 
			destination.inEdge.remove(sLabel, remove); //remove edge from destination vertex 
			source.outEdge.remove(dLabel, remove); //remove edge from source vertex
			edgesId.remove(remove.idNum); //remove edge from list 
			destination.degree--;
			return true;
		}

	}

	@Override
	public long numNodes() {
		return verticesId.size(); //number of vertices in the list 
	}

	@Override
	public long numEdges() {
		return edgesId.size(); //number of edges in the list 
	}

	
	
	
	
	
	@Override
	public ShortestPathInfo[] shortestPath(String label) {
		int count = 0; //counter 
		Vertex start = map.get(label); //start vertex based on input 
		start.distance = 0; //set distances to 0 
		ShortestPathInfo[] paths = new ShortestPathInfo[verticesId.size()]; //create array of paths 
		MinBinHeap queue = new MinBinHeap(); //use binary heap as queue
		EntryPair pairs = new EntryPair(start.label, start.distance); 
		queue.insert(pairs); //insert original entrypair into queue 
		paths[0] = new ShortestPathInfo(start.label, start.distance); //first element of array 
		while (queue.size() != 0) { //while there is something in queue
			Vertex vert = map.get(queue.getMin().getValue()); //min value 
			EntryPair queueMin = (EntryPair) queue.getMin(); //min of queue
			long distance = queueMin.getPriority(); // get the distance 
			queue.delMin(); //delete top of queue
			if (!vert.visited) { //if the vertex hasn't been seen 
				paths[count] = new ShortestPathInfo(vert.label, distance); //add new shortest path with next node/distance  
				vert.visited = true; //change it so vertex has been seen 
			} else {
				continue;
			}
			Iterator<Edge> adjacent = vert.outEdge.values().iterator(); //iterator for adjacent nodes
			while (adjacent.hasNext()) {
				Vertex next = map.get(adjacent.next().dLabel); //next vertex 
				if (!next.visited) { //if next vertex hasn't been visited
					long updateDistance = distance; 
					updateDistance +=  vert.outEdge.get(next.label).weight; //update distance 
					if (next.distance > updateDistance || next.distance == 0) { //if the new distance is less than the current 
						next.distance = (int) updateDistance; //store smaller distance
						queue.insert(new EntryPair(next.label, next.distance)); //insert into queue vertex and new shorter distance
					}
				}
			}
			count++;
		}
		if (count < verticesId.size()) { //checking all the vertices
			for (Vertex vertex : map.values()) {
				if (!vertex.visited) { //if no other vertex attached
					vertex.distance = -1; //set distance to -1
					paths[count] = new ShortestPathInfo(vertex.label, vertex.distance); //add new node with -1 distance
					count++;
				}
			}

		}

		return paths;

	}

}
