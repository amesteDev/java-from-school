/*
* Date: 2021-10-23.
* File Name: MyTransitiveClosure.Java
* Author: Morgan Andersson
*
*/

package ma223yd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import graphs.DirectedGraph;
import graphs.Node;

/**
* Returns Map containing Collections of Nodes representing the Transitive Closure for a graph. A map of what each node is able to reach.
* This is simply done by looping through all nodes and calling bfs on them, and then adding the result to the map as a List.
* 
* @version 1.0, 23 oct 2021
* @author Morgan Andersson
*/
public class MyTransitiveClosure<E> implements graphs.TransitiveClosure<E>{

	@Override
	public Map<Node<E>, Collection<Node<E>>> computeClosure(DirectedGraph<E> dg) {
		MyDFS<E> dfs = new MyDFS<E>();
		Map<Node<E>, Collection<Node<E>>> transClose = new HashMap<Node<E>, Collection<Node<E>>>();
		HashSet<Node<E>> visited = new HashSet<Node<E>>();
		
		List<E> allItems = dg.allItems();

		for(E node : allItems) {

			Node<E> n = dg.getNodeFor(node);
			ArrayList<Node<E>> l = new ArrayList<Node<E>>(dfs.dfs(dg, n));
			visited.addAll(l);
			transClose.put(n, l);				
		}
		return transClose;
	}
}