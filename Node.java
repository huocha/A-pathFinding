
public class Node {
	boolean open = false;
	boolean closed = false;
	int f, g, h = 0;
	int x, y = 0;
	public Node(int lx, int ly) {
		x = lx;
		y = ly;
	}
}
