package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Interface.IStrongConnectedComponentsDetecter;

public class Graph <T> {
	private List<Vertex<T>> vertice;
	private Map<Vertex<T>, List<Vertex<T>>> outwardEdges;
	private Map<Vertex<T>, List<Vertex<T>>> inwardEdges;
	
	IStrongConnectedComponentsDetecter <T> sccDetecter;
	
	public Graph () {
		this.vertice = new ArrayList<Vertex<T>>();
		this.outwardEdges = new HashMap<Vertex<T>, List<Vertex<T>>>();
		this.inwardEdges = new HashMap<Vertex<T>, List<Vertex<T>>>();
		this.sccDetecter = null;
	}
	
	public int getNumVertice() {
		return vertice.size();
	}
	
	public List<Vertex<T>> getAllVertice() {
		return this.vertice;
	}
	
	public boolean add(Vertex<T> v) {
		if (vertice.contains(v))
			return false;
		try {
			vertice.add(v);
			outwardEdges.put(v, new ArrayList<Vertex<T>>());
			inwardEdges.put(v, new ArrayList<Vertex<T>>());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean connect (Vertex <T> from, Vertex <T> to) {
		if (!vertice.contains(from) || !vertice.contains(to))
			return false;
		try {
			outwardEdges.get(from).add(to);
			inwardEdges.get(to).add(from);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public boolean contains(Vertex<T> v) {
		return vertice.contains(v);
	}
	
	public List<Vertex<T>> getInwardEdges (Vertex<T> to) {
		if (!contains(to))
			return null;
		return inwardEdges.get(to);
	}
	
	public List<Vertex<T>> getOutwardEdges (Vertex<T> from) {
		if (!contains(from))
			return null;
		return outwardEdges.get(from);
	}
	
	public boolean remove(Vertex<T> v) {
		if (!contains(v))
			return false;
		try {
			vertice.remove(v);
			
			this.outwardEdges.remove(v);
			this.inwardEdges.remove(v);
			
			Iterator<Entry<Vertex<T>, List<Vertex<T>>>> it = this.outwardEdges.entrySet().iterator();
			if (it.hasNext()) {
				List<Vertex<T>> tmp = it.next().getValue();
				if (tmp.contains(v))
					tmp.remove(v);
			}
			
			it = this.inwardEdges.entrySet().iterator();
			if (it.hasNext()) {
				List<Vertex<T>> tmp = it.next().getValue();
				if (tmp.contains(v))
					tmp.remove(v);
			}
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public void setStrongConnectedComponentsDetecter(IStrongConnectedComponentsDetecter<T> detecter) {
		this.sccDetecter = detecter;
	}
	
	public List<List<Vertex<T>>> getStrongConnectedComponents() {
		if (this.sccDetecter == null)
			return null;
		else
			return sccDetecter.detect(this);
	}
}
