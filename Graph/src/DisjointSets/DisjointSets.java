package DisjointSets;

import java.util.ArrayList;
import java.util.List;

public class DisjointSets <T> {

	private List<T> nodes = new ArrayList<T>();
	private List<Integer> ancestor = new ArrayList<Integer>();
	
	public boolean makeSet(T userObject) {
		if (nodes.contains(userObject))
			return false;
		else {
			nodes.add(userObject);
			ancestor.add(nodes.size() - 1);
			return true;
		}
	}
	
	/*
	 * different from the classic method
	 * , this method will always use the root of o1 as the new root
	 */
	public boolean union (T o1, T o2) {
		if (!hasUserObject(o1) || !hasUserObject(o2) || find(o1) == find(o2))
			return false;
		T rootO1 = find(o1);
		T rootO2 = find(o2);
		this.ancestor.set(this.nodes.indexOf(rootO2), this.nodes.indexOf(rootO1));
		return true;
	}
	
	public T find(T userObject) {
		if (!nodes.contains(userObject))
			return null;
		int index = nodes.indexOf(userObject);
		int ancestorIndex = ancestor.get(index);
		if (ancestorIndex == index)
			return userObject;
		else {
			T ancestor = nodes.get(ancestorIndex);
			T newAncestor = find(ancestor);
			int newAncestorInde = nodes.indexOf(newAncestor);
			this.ancestor.set(index, newAncestorInde);
			return newAncestor;
		}
	}
	
	public boolean hasUserObject(T userObject) {
		return this.nodes.contains(userObject);
	}
}
