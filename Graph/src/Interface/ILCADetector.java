package Interface;

import Tree.TreeNode;

/*
 * interface for Lowest Common Ancestor Detector
 */

public interface ILCADetector {
	public void setTreeRoot (TreeNode root);
	public TreeNode detect (TreeNode v1, TreeNode v2);
}
