import java.util.ArrayList;

public class Vertex{
	int level = -1;
	ArrayList<Vertex> neighborVertices = new ArrayList<Vertex>();
	ArrayList<Edge> neighborEdges = new ArrayList<Edge>();
	@Override
	public String toString() {
		return "Vertex [neighborEdges=" + neighborEdges + "]";
	}
}
