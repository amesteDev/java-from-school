package ma223yd;

import graphs.GML;
import graphs.Node;

public class GraphTest {
	public static <E> void main(String[] args) {

		MyGraph<Integer> g = new MyGraph<Integer>();
		g.addEdgeFor(0, 1);
		g.addEdgeFor(1, 2);
		g.addEdgeFor(1, 3);
		g.addEdgeFor(1, 4);
		g.addEdgeFor(2, 8);
		g.addEdgeFor(7, 3);
		g.addEdgeFor(4, 6);
		g.addEdgeFor(4, 3);
		g.addEdgeFor(3, 5);
		g.addEdgeFor(6, 5);
		g.addEdgeFor(5, 7);
		g.addEdgeFor(7, 8);
		g.addEdgeFor(8, 3);
		
		GML<Integer> gml = new MyGML<Integer>(g);
		gml.dumpGML();
		
	}
}