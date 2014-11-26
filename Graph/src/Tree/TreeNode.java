package Tree;

import java.util.ArrayList;
import java.util.List;

import Interface.ILCADetector;

public class TreeNode {

	private List<TreeNode> children;
	private Object userObject;
	private ILCADetector lcaDetector;
	
	public TreeNode () {
		this.children = new ArrayList<TreeNode>();
	}
	
	public TreeNode (Object o) {
		this.userObject = o;
		this.children = new ArrayList<TreeNode>();
	}
	
	public List<TreeNode>getChildren() {
		return this.children;
	}
	
	public void addChild(TreeNode c) {
		this.children.add(c);
	}
	
	public void setLeft(TreeNode c) {
		if (this.children.size() == 0)
			this.children.add(c);
		else
			this.children.set(0, c);
	}
	
	public void setRight(TreeNode c) {
		if (this.children.size() == 0) {
			this.children.add(null);
		}
		else if (this.children.size() == 1)
			this.children.add(c);
		else
			this.children.set(1, c);
	}
	
	public TreeNode getChildAt(int index) {
		if (index < 0 || index >= getChildrenCount())
			return null;
		return this.children.get(index);
	}
	
	public TreeNode getLeft() {
		return this.getChildAt(0);
	}
	
	public TreeNode getRight() {
		return this.getChildAt(1);
	}
	
	public int getChildrenCount() {
		return this.children.size();
	}
	
	public int getIndex(TreeNode node) {
		if (node == null || getChildrenCount() == 0)
			return -1;
		return this.children.indexOf(node);
	}
	
	public Object getUserObject() {
		return this.userObject;
	}
	
	public void setUserObject(Object o) {
		this.userObject = o;
	}
	
	public boolean isLeaf() {
		return this.children.size() == 0 ? true : false;
	}
	
	public List<TreeNode> getAllNodes() {
		return preOrderTraversal();
	}
	
	public List<TreeNode> preOrderTraversal() {
		List<TreeNode> result = new ArrayList<TreeNode>();
		preOrderTraversal(this, result);
		return result;
	}
	
	private void preOrderTraversal(TreeNode node, List<TreeNode> result) {
		if (node == null)
			return;
		result.add(node);
		for (int i = 0; i < node.children.size(); i++)
			preOrderTraversal(node.children.get(i), result);
	}
	
	public void setLCADetector(ILCADetector detector) {
		this.lcaDetector = detector;
	}
	
	public TreeNode findLCA(TreeNode n1, TreeNode n2) {
		this.lcaDetector.setTreeRoot(this);
		return this.lcaDetector.detect(n1, n2);
	}
	
	public static void main(String[] args) {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(6);
		TreeNode n7 = new TreeNode(7);
		n1.addChild(n2);
		n1.addChild(n3);
		n2.addChild(n4);
		n2.addChild(n5);
		n3.addChild(n6);
		n3.addChild(n7);
		n1.setLCADetector(new LCADetecterOfflineTarjanImpl());
		System.out.println(n1.findLCA(n4, n5).userObject);
	}
}
