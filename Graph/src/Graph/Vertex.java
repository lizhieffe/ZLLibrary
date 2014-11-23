package Graph;

public class Vertex <T> {
	private T val;
	
	public Vertex (T val) {
		this.val = val;
	}
	
	public void setValue (T val) {
		this.val = val;
	}
	public T getValue () {
		return this.val;
	}
}
