package Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DisjointSets.DisjointSets;
import Interface.ILCADetector;

public class LCADetecterOfflineTarjanImpl<T> implements ILCADetector<T>{

	TreeNode<T> root;
	private boolean preprocessed = false;
	Map<Integer, Map<Integer, Integer>> LCARelations 
			= new HashMap<Integer, Map<Integer, Integer>>();
	List<TreeNode<T>> treeNodes = null;
	List<Boolean> visit = null;
	DisjointSets<Integer> disjointSets = new DisjointSets<Integer>();
	
	@Override
	public void setTreeRoot(TreeNode<T> graph) {
		this.root = graph;
		this.preprocessed = false;
		LCARelations.clear();
	}

	@Override
	public TreeNode<T> detect(TreeNode<T> v1, TreeNode<T> v2) {
		if (root == null)
			return null;
		if (!this.preprocessed)
			preprocess();
		int iv1 = this.root.getAllNodes().indexOf(v1);
		int iv2 = this.root.getAllNodes().indexOf(v2);
		int iAncestor = getLCA(iv1, iv2);
		if (iAncestor == -1)
			return null;
		return this.root.getAllNodes().get(iAncestor);
	}
	
	private void preprocess() {
		if (root == null || this.preprocessed)
			return;
		this.treeNodes = root.getAllNodes();
		this.visit = new ArrayList<Boolean>(this.treeNodes.size());
		for (int i = 0; i < treeNodes.size(); i++)
			visit.add(false);
		tarjan(treeNodes.indexOf(root));
	}
	
	private void tarjan(int index) {
		this.disjointSets.makeSet(index);
		List<TreeNode<T>> children = this.treeNodes.get(index).getChildren();
		for (TreeNode<T> child : children) {
			int childIndex = this.treeNodes.indexOf(child);
			tarjan(childIndex);
			this.disjointSets.union(index, childIndex);
		}
		this.visit.set(index, true);
		for (TreeNode<T> child : this.treeNodes) {
			int childIndex = this.treeNodes.indexOf(child);
			if (this.visit.get(childIndex) == true)
				setLCA(index, childIndex, this.disjointSets.find(childIndex));
		}
	}
	
	/*
	 * this function is only used by pre-process to record the LCA results
	 */
	private void setLCA(int v1, int v2, int ancestor) {
		if (v1 > v2) {
			int tmp = v1;
			v1 = v2;
			v2 = tmp;
		}
		if (!this.LCARelations.containsKey(v1))
			this.LCARelations.put(v1, new HashMap<Integer, Integer>());
		this.LCARelations.get(v1).put(v2, ancestor);
	}
	
	private int getLCA(int v1, int v2) {
		if (v1 > v2) {
			int tmp = v1;
			v1 = v2;
			v2 = tmp;
		}
		if (!this.LCARelations.containsKey(v1)
				|| !this.LCARelations.get(v1).containsKey(v2))
			return -1;
		return this.LCARelations.get(v1).get(v2);	
	}
}
