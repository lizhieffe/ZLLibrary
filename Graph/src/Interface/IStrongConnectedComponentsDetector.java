package Interface;

import java.util.List;

import Graph.Graph;
import Graph.Vertex;

/*
 * Interface for Strong Connected Components Detector
 */

public interface IStrongConnectedComponentsDetector <T> {
	public List<List<Vertex<T>>> detect(Graph<T> graph);
}
