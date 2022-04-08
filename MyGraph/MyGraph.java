/*
* Date: 2021-10-23.
* File Name: MyGraph.Java
* Author: Morgan Andersson
*
*/

package ma223yd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import graphs.Node;

/**
* MyGraph is an implementation of DirectedGraph<E>, and as such holds data for the nodes in a given graph, aswell as sets for all the heads and tails.
* It also provides functions to add nodes, remove nodes, check edges etc.
* 
* @version 1.0, 23 oct 2021
* @author Morgan Andersson
*/

public class MyGraph<E> implements graphs.DirectedGraph<E> {
	//HashMap and HashSet because the quick lookup times.
	private Map<E, Node<E>> item2node = new HashMap<E, Node<E>>();
	private HashSet<Node<E>> heads = new HashSet<Node<E>>();
	private HashSet<Node<E>> tails = new HashSet<Node<E>>();

	public MyGraph() {}
	
	@Override
	public MyNode<E> addNodeFor(E item) {
		
		if(item == null) {
			throw new RuntimeException("Recieved null as input!");
		}
		
		if(item2node.containsKey(item)) {
			return (MyNode<E>) item2node.get(item); //if the item is already represented in the graph, do nothing.
		}
		
		MyNode<E> node = new MyNode<E>(item);
		item2node.put(item, node);
		heads.add(node); //add the node to both heads and tails, since it does not have any preds or succs.
		tails.add(node);
		return node;
	}

	@Override
	public MyNode<E> getNodeFor(E item) {
		
		if(item == null) {
			throw new RuntimeException("Recieved null as input!");
		}
		
		Node<E> node = item2node.get(item);
		
		if(node == null) {
			throw new RuntimeException("No such node");
		}
				
		return (MyNode<E>) node;
	}

	@Override
	public boolean addEdgeFor(E from, E to) {
		
		if(from == null || to == null) {
			throw new RuntimeException("Null input");
		} 
		
		MyNode<E> src = addNodeFor(from);
		MyNode<E> tgt = addNodeFor(to);
		
		if(src.hasSucc(tgt)) {
			return false; //this is to prevent adding several edges between nodes.
		}
		
		else {
			src.addSucc(tgt);
			tgt.addPred(src);
			tails.remove(src); //there has been an edge added, remove nodes from tails and heads.
			heads.remove(tgt);
			
			return true;
		}
	}

	@Override
	public boolean containsNodeFor(E item) {
		if(item == null) {
			throw new RuntimeException("Null input");
		}
		return (item2node.containsKey(item));
	}

	@Override
	public int nodeCount() {
		return item2node.size();
	}

	@Override
	public Iterator<Node<E>> iterator() {
		return item2node.values().iterator();
	}

	@Override
	public Iterator<Node<E>> heads() {
		return heads.iterator();
	}

	@Override
	public int headCount() {	
		return heads.size();
	}

	@Override
	public Iterator<Node<E>> tails() {
		return tails.iterator();
	}

	@Override
	public int tailCount() {
		return tails.size();
	}

	@Override
	public List<E> allItems() {
				
		List<E> list = new ArrayList<E>(item2node.keySet());
		
		return list;
	}

	/**
	 * Sum up the outdegree of all nodes in the graph to get the number of edges.
	 */
	@Override
	public int edgeCount() {
		int edges = 0;
		
		for(Node<E> n : item2node.values()) {
			edges += n.outDegree();
		}

		return edges;
	}

	/**
	 * Remove the node from heads/tails if present, then call node.disconnect() to remove all edges to and from the node.
	 * Then remove it form the set containing all nodes.
	 */
	@Override
	public void removeNodeFor(E item) {
		MyNode<E> node = (MyNode<E>) item2node.get(item);
		
		if(heads.contains(node)) {
			heads.remove(node);
		}
		
		if(tails.contains(node)) {
			tails.remove(node);
		}

		node.disconnect();
		
		item2node.remove(item);
	}

	@Override
	public boolean containsEdgeFor(E from, E to) {
		
		if(from == null || to == null) {
			throw new RuntimeException("Null input");
		}
		
		if(item2node.containsKey(from) && item2node.containsKey(to)) {
			MyNode<E> src = (MyNode<E>) item2node.get(from);
			MyNode<E> tgt = (MyNode<E>) item2node.get(to);
			
			if(src.hasSucc(tgt) && tgt.hasPred(src)) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Removes the edge between from and to.
	 */
	@Override
	public boolean removeEdgeFor(E from, E to) {
		
		if(from == null || to == null) {
			throw new RuntimeException("Null input");
		}
	
		MyNode<E> src = (MyNode<E>) item2node.get(from);
		MyNode<E> tgt = (MyNode<E>) item2node.get(to);
		
		if(containsEdgeFor(from, to)) {
			
			src.removeSucc(tgt);
			tgt.removePred(src);
			
			if(src.isTail()) {
				tails.add(src);
			}
			
			if(tgt.isHead()) {
				heads.add(tgt);
			}

			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		String graph = "";
		for(Node<E> n : item2node.values()) {
			Iterator<Node<E>> it = n.succsOf();
			while(it.hasNext()) {
				graph += "[" + n + " -> " + it.next() + "]"; // generates a representation of two nodes and the edge as such: [0 -> 1]
			}
		}
		return graph;
	}
}