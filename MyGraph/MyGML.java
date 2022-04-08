/*
* Date: 2021-10-23.
* File Name: MyGML.Java
* Author: Morgan Andersson
*
*/

package ma223yd;

import java.util.Iterator;

import graphs.DirectedGraph;
import graphs.Node;


/**
* Class to generate GML-syntax which then can be saved to a file with methods in graphs.GML.
* 
* @version 1.0, 23 oct 2021
* @author Morgan Andersson
*/

public class MyGML<E>  extends graphs.GML<E>  {
	String newLine = System.lineSeparator();

	public MyGML(DirectedGraph<E> dg) {
		super(dg);
	}

	@Override
	public String toGML() {
		String GML = "graph [ " + newLine + "directed 1" + newLine; //the start of the GML.
		

		for(E n : super.graph.allItems()) {
			GML += " node " + newLine + "[ id " + n + newLine + " label \"node " + n + "\"" + newLine + "]" + newLine; //every node in the graph
		}
		
		//loops through all the nodes and the directed edges to the GML
		for(E n : super.graph.allItems()) {
			Node<E> node = super.graph.getNodeFor(n);
					
				Iterator<Node<E>> pit = node.succsOf();
				while(pit.hasNext()) {
					String edges = "edge [" + newLine;
					String source = node.toString();
					String target = pit.next().toString();
					edges += "source " +  source + newLine + "target " + target + newLine + "]" + newLine; //smashing strings together, its very effective
					GML += edges;
				}
		}
		GML += "]"; //closing the GML-syntax
		return GML;
	}
}