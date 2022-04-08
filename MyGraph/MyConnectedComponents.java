/*
* Date: 2021-10-23.
* File Name: MyConnectedComponents.Java
* Author: Morgan Andersson
*
*/

package ma223yd;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import graphs.DirectedGraph;
import graphs.Node;

/**
* Class Description: An implementation for the interface ConnectededComponenets<E> that returns
* a collection of connected components in a directed graph.
* @version 1.0, 23 oct 2021
* @author Morgan Andersson
*/

public class MyConnectedComponents<E> implements graphs.ConnectedComponents<E> {
	
	List<Node<E>> dfs;
	
	HashSet<Node<E>> visited = new HashSet<Node<E>>();
	
	int counter = 1;
	
	/**
	 * This function makes use of the dfsUni(Node<E> node) which essentially is a DFS that transforms the graph into a bi-directional graph.
	 * The idea is: for every node, if that node has not been visited, run dfsUni on it. Which for each dfsUni-recursion-run results in a
	 * LinkedList of connected components.
	 */
	@Override
	public Collection<Collection<Node<E>>> computeComponents(DirectedGraph<E> dg) {	
		
		Collection<Collection<Node<E>>> components = new LinkedList<Collection<Node<E>>>();
		visited.clear();
		
		counter = 1;
		for(E n : dg.allItems()) {
			Node<E> node = dg.getNodeFor(n);
			if(!visited.contains(node)) {
				dfs = new LinkedList<Node<E>>();
				if(!visited.contains(node)) {
					dfsUni(node);
					components.add(dfs);
				}
			}
		}
		return components;
	}
	
	private void dfsUni(Node<E> node) {
		visited.add(node);
		dfs.add(node);
		
		Iterator<Node<E>> succs = node.succsOf();
		Iterator<Node<E>> preds = node.predsOf();
		
		while(succs.hasNext()) {
			Node<E> n = succs.next();
			if(!visited.contains(n)) {
				dfsUni(n);
			}
		}
		while(preds.hasNext()) {
			Node<E> n = preds.next();
			if(!visited.contains(n)) {
				dfsUni(n);
			}
		}
	}
}