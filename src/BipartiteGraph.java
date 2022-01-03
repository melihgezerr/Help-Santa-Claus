import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class BipartiteGraph {
	Vertex source;
	Vertex sink;
	int numVertices;
	public BipartiteGraph(Vertex source, Vertex sink, int numVertices) {
		this.source = source;
		this.sink = sink;
		this.numVertices = numVertices;
		
	}
	ArrayList<Vertex> redReindeers = new ArrayList<Vertex>();
	ArrayList<Vertex> greenReindeers = new ArrayList<Vertex>();
	ArrayList<Vertex> redTrains= new ArrayList<Vertex>();
	ArrayList<Vertex> greenTrains = new ArrayList<Vertex>();

	ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	ArrayList<Edge> edges = new ArrayList<Edge>();

	public void addEdge(Vertex bag, Vertex vehicle, int capacity) {
		bag.neighborVertices.add(vehicle);
		vehicle.neighborVertices.add(bag);
		Edge myEdge = new Edge(bag, vehicle, capacity);
		Edge pairEdge = new Edge(vehicle, bag, 0);
		edges.add(pairEdge);
		edges.add(myEdge);
		bag.neighborEdges.add(myEdge);
		vehicle.neighborEdges.add(pairEdge);
		myEdge.pair = pairEdge;
		pairEdge.pair = myEdge;
	}

	public int DinicsAlgorithm(Vertex start, Vertex end) {
		if (start.equals(end))
			return -1;
		
		int maxFlow = 0;
		
		while (setLevelsOfVertices()) {
			
			int[] nextEdge = new int[vertices.size()];
			for (int i=0; i<nextEdge.length;i++)
				nextEdge[i] = 0;
			
			int floww = DepthFirstSearch(start, Integer.MAX_VALUE, end, nextEdge);
			while (floww!= 0) {
				maxFlow += floww;
				floww = DepthFirstSearch(start, Integer.MAX_VALUE, end, nextEdge);
			}
		}
		return maxFlow;
	}
	
	public int DepthFirstSearch (Vertex start, int flow, Vertex end, int[] nextEdge) {
		if (start.equals(end))
			return flow;
		for ( ; nextEdge[vertices.indexOf(start)]<start.neighborEdges.size(); nextEdge[vertices.indexOf(start)]++) {
			
			Edge edg = start.neighborEdges.get(nextEdge[vertices.indexOf(start)]);
			
			if (edg.to.level==start.level+1 && edg.flow>0) {
				int currFlow = Math.min(flow,edg.flow);
				
				int tempFlow = DepthFirstSearch(edg.to, currFlow, end, nextEdge);
				
				if (tempFlow>0) {
					edg.flow-=tempFlow;
					edg.pair.flow+=tempFlow;
					return tempFlow;
				}
			}
			
		}
		return 0;
	}
	public boolean setLevelsOfVertices() {
		Queue<Vertex> vertexQueue = new LinkedList<Vertex>();
		for (Vertex vert : vertices) {
			vert.level = -1;
		}
		source.level = 0;
		vertexQueue.add(source);
		while (!vertexQueue.isEmpty()) {
			Vertex currVertex = vertexQueue.poll();
			for (Edge edge : currVertex.neighborEdges) {
				if (edge.to.level<0 && edge.flow>0){
					edge.to.level=edge.from.level+1;
					vertexQueue.add(edge.to);
				}
			}
		}
		return !(sink.level < 0);
	}



}


