package Other;

import java.util.Comparator;
import java.util.PriorityQueue;

import Tree.TreeNode;

public class HoffmanCode {
	public TreeNode generateHoffmanCodeTree(Object[] o, int[] count) {
		if (o == null || o.length == 0 || o.length != count.length)
			return null;
		PriorityQueue<HoffmanCodeTreeNode> pq
				= new PriorityQueue<HoffmanCodeTreeNode>(o.length, new HoffmanCodeNodeComparator());
		for (int i = 0; i < o.length; i++) {
			pq.add(new HoffmanCodeTreeNode(o[i], count[i]));
		}
		while (pq.size() > 1) {
			HoffmanCodeTreeNode n1 = pq.poll();
			HoffmanCodeTreeNode n2 = pq.poll();
			HoffmanCodeTreeNode n = new HoffmanCodeTreeNode(null
					, ((ObjNCount)n1.getUserObject()).count 
					+ ((ObjNCount)n2.getUserObject()).count);
			n1.setLeft(n1);
			n2.setLeft(n2);
			pq.add(n);
		}
		return pq.poll();
	}
}

class HoffmanCodeTreeNode extends TreeNode {
	HoffmanCodeTreeNode(Object o, int count) {
		super(new ObjNCount(o, count));
	}
}

class HoffmanCodeNodeComparator 
		implements Comparator <HoffmanCodeTreeNode> {

	@Override
	public int compare(HoffmanCodeTreeNode o1, HoffmanCodeTreeNode o2) {
		int c1 = ((ObjNCount)o1.getUserObject()).count;
		int c2 = ((ObjNCount)o2.getUserObject()).count;
		if (c1 < c2)
			return -1;
		if (c1 == c2)
			return 0;
		return 1;
	}
}

class ObjNCount {
	Object o;
	int count;
	ObjNCount(Object o, int count) {
		this.o = o;
		this.count = count;
	}
}