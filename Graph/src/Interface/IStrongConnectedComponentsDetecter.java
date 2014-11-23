package Interface;

import java.util.List;

import Graph.Graph;
import Graph.Vertex;

public interface IStrongConnectedComponentsDetecter <T> {
	public List<List<Vertex<T>>> detect(Graph<T> graph);
}
