
public class Edge {
	Vertex from;
	Vertex to;
	Edge pair = null;
	int flow;
	
	public Edge(Vertex from, Vertex to, int flow) {
		this.from = from;
		this.to = to;
		this.flow = flow;
	}

	@Override
	public String toString() {
		return "Edge"+" flow= " + flow + "]";
	}

	

	
}
