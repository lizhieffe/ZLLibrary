package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Interface.IStrongConnectedComponentsDetector;

public class StrongConnectedComponentsDetectorTarjanImpl <T> implements IStrongConnectedComponentsDetector <T> {

	List<Vertex<T>> vertice;
	int[] color;
	int[] low;
	int[] dfn;
	Graph <T> graph;
	LinkedList<Integer> stack;
	List<List<Vertex<T>>> result;
	
	@Override
	public List<List<Vertex<T>>> detect (Graph <T> graph) {
		result = new ArrayList<List<Vertex<T>>>();
		
		if (graph == null || graph.getNumVertice() == 0)
			return result;
		this.graph = graph;
		this.vertice = graph.getAllVertice();
		this.color = new int[vertice.size()];
		this.low = new int[vertice.size()];
		this.dfn = new int[vertice.size()];
		for (int i = 0; i < low.length; i++) {
			low[i] = i + 1;
			dfn[i] = i + 1;
		}
		stack = new LinkedList<Integer>();
		for (int i = 0; i < color.length; i++) {
			if (color[i] == 0) {
				tarjan(i);
			}
		}
		return this.result;
	}
	
	private void tarjan(Integer k) {
		color[k] = 1;
		stack.add(k);
		
		List<Vertex<T>> neighbors = graph.getOutwardEdges(vertice.get(k));
		for (int i = 0; i < neighbors.size(); i++) {
			Vertex<T> neighbor = neighbors.get(i);
			int neighborId = vertice.indexOf(neighbor);
			if (color[neighborId] == 0) {
				tarjan(neighborId);
				low[k] = Math.min(low[k], low[neighborId]);
			}
			else if (stack.contains(neighborId))
				low[k] = Math.min(low[k], low[neighborId]);
		}
		if (low[k] == dfn[k]) {
			List<Vertex<T>> solution = new ArrayList<Vertex<T>>();
			int tmp = -1;
			do {
				tmp = stack.removeLast();
				solution.add(this.vertice.get(tmp));
				
			} while (tmp != k);
			result.add(solution);
		}
	}
	
	public static void main(String[] args) {
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
		Vertex<Integer> v5 = new Vertex<Integer>(5);
		Vertex<Integer> v6 = new Vertex<Integer>(6);
		Graph<Integer> graph = new Graph<Integer>();
		graph.add(v1);
		graph.add(v2);
		graph.add(v3);
		graph.add(v4);
		graph.add(v5);
		graph.add(v6);
		graph.connect(v1, v2);
		graph.connect(v1, v3);
		graph.connect(v2, v4);
		graph.connect(v3, v4);
		graph.connect(v3, v5);
		graph.connect(v4, v1);
		graph.connect(v4, v6);
		graph.connect(v5, v6);
		graph.setStrongConnectedComponentsDetecter(new StrongConnectedComponentsDetectorTarjanImpl<Integer>());
		graph.getStrongConnectedComponents();
	}
}
