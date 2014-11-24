package Tree;

import java.util.ArrayList;
import java.util.List;

import Interface.ILCADetector;

public class TreeNode <T> {

	private List<TreeNode<T>> children;
	private T userObject;
	private ILCADetector <T> lcaDetector;
	
	public TreeNode () {
		this.children = new ArrayList<TreeNode<T>>();
	}
	
	public TreeNode (T o) {
		this.userObject = o;
		this.children = new ArrayList<TreeNode<T>>();
	}
	
	public List<TreeNode<T>>getChildren() {
		return this.children;
	}
	
	public void addChild(TreeNode<T> c) {
		this.children.add(c);
	}
	
	public TreeNode<T> getChildAt(int index) {
		if (index < 0 || index >= getChildrenCount())
			return null;
		return this.children.get(index);
	}
	
	public int getChildrenCount() {
		return this.children.size();
	}
	
	public int getIndex(TreeNode<T> node) {
		if (node == null || getChildrenCount() == 0)
			return -1;
		return this.children.indexOf(node);
	}
	
	public T getUserObject() {
		return this.userObject;
	}
	
	public void setUserObject(T o) {
		this.userObject = o;
	}
	
	public boolean isLeaf() {
		return this.children.size() == 0 ? true : false;
	}
	
	public List<TreeNode<T>> getAllNodes() {
		return preOrderTraversal();
	}
	
	public List<TreeNode<T>> preOrderTraversal() {
		List<TreeNode<T>> result = new ArrayList<TreeNode<T>>();
		preOrderTraversal(this, result);
		return result;
	}
	
	private void preOrderTraversal(TreeNode<T> node, List<TreeNode<T>> result) {
		if (node == null)
			return;
		result.add(node);
		for (int i = 0; i < node.children.size(); i++)
			preOrderTraversal(node.children.get(i), result);
	}
	
	public void setLCADetector(ILCADetector<T> detector) {
		this.lcaDetector = detector;
	}
	
	public TreeNode <T> findLCA(TreeNode<T> n1, TreeNode<T> n2) {
		this.lcaDetector.setTreeRoot(this);
		return this.lcaDetector.detect(n1, n2);
	}
	
	public static void main(String[] args) {
		TreeNode<Integer> n1 = new TreeNode<Integer>(1);
		TreeNode<Integer> n2 = new TreeNode<Integer>(2);
		TreeNode<Integer> n3 = new TreeNode<Integer>(3);
		TreeNode<Integer> n4 = new TreeNode<Integer>(4);
		TreeNode<Integer> n5 = new TreeNode<Integer>(5);
		TreeNode<Integer> n6 = new TreeNode<Integer>(6);
		TreeNode<Integer> n7 = new TreeNode<Integer>(7);
		n1.addChild(n2);
		n1.addChild(n3);
		n2.addChild(n4);
		n2.addChild(n5);
		n3.addChild(n6);
		n3.addChild(n7);
		n1.setLCADetector(new LCADetecterOfflineTarjanImpl<Integer>());
		System.out.println(n1.findLCA(n4, n5).userObject);
	}
}
