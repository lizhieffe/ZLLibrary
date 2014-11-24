package Interface;

import Tree.TreeNode;

/*
 * interface for Lowest Common Ancestor Detector
 */

public interface ILCADetector <T> {
	public void setTreeRoot (TreeNode<T> root);
	public TreeNode<T> detect (TreeNode<T> v1, TreeNode<T> v2);
}
