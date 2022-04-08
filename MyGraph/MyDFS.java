/*
* Date: 2021-10-23.
* File Name: MyDFS.Java
* Author: Morgan Andersson
*
*/

package ma223yd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import graphs.DirectedGraph;
import graphs.Node;


/**
* Class Description: Implementation of DFS (Depth-First Search) to be used to traverse a Directed Graph as implemented in MyGraph.java.
* Implemented with a recursive approach.
* Also contains methods for postOrder traversal, topological sorting and a method to determine if a graph is cyclic.
* @version 1.0, 23 oct 2021
* @author Morgan Andersson
*/

public class MyDFS<E> implements graphs.DFS<E> {
	
	//Using a HashSet to store the nodes visited by DFS, since HashSet has O(1) to lookup for a specific index.
	HashSet<Node<E>> visited = new HashSet<Node<E>>();
	List<Node<E>> rt = new ArrayList<Node<E>>();
	
	int counter = 1; //counter used for the dfs-number and postOrder-number.
	
	/*
	 * Empties the list and set used for DFS-traversal.
	 */
	private void clear() {
		rt.clear();
		visited.clear();
	}
	
	private void dfsTraversal(Node<E> cur) {
	
		visited.add(cur);
		cur.num = counter++; //set the current nodes num-field to the value of counter and then increment counter.
		rt.add(cur);
		
		Iterator<Node<E>> it = cur.succsOf();
		while(it.hasNext()) {
			Node<E> n = it.next();
			if(!visited.contains(n)) {
				dfsTraversal(n);
			}
		}
	}
	
	private void psTraversal(Node<E> cur) {
		
		visited.add(cur);		
		Iterator<Node<E>> it = cur.succsOf();
		while(it.hasNext()) {
			Node<E> n = it.next();
			if(!visited.contains(n)) {
				psTraversal(n);
			}
		}
		cur.num = counter++; //setting num at the end of traversal gives post order numbering instead.
		rt.add(cur);
	}
	
	private void psTraversalWithDfsNumber(Node<E> cur) {

		visited.add(cur);	
		cur.num = counter++;
		Iterator<Node<E>> it = cur.succsOf();
		while(it.hasNext()) {
			Node<E> n = it.next();
			if(!visited.contains(n)) {
				psTraversalWithDfsNumber(n);
			}
		}
		rt.add(cur);
	}

	@Override
	public List<Node<E>> dfs(DirectedGraph<E> graph, Node<E> root) {
		clear();
		counter = 1;
		dfsTraversal(root);
		return rt;
	}

	@Override
	public List<Node<E>> dfs(DirectedGraph<E> graph) {
		clear();
		counter = 1;
		Iterator<Node<E>> it = graph.heads();
		while(it.hasNext()) {
			dfsTraversal(it.next());
		}
		return rt;
	}

	@Override
	public List<Node<E>> postOrder(DirectedGraph<E> g, Node<E> root) {
		clear();
		counter = 1;
		psTraversal(root);
		return rt;
	}

	@Override
	public List<Node<E>> postOrder(DirectedGraph<E> g) {
		clear();
		Iterator<Node<E>> it = g.heads();
		while(it.hasNext()) {
			psTraversal(it.next());
		}
		return rt;
	}

	@Override
	public List<Node<E>> postOrder(DirectedGraph<E> g, boolean attach_dfs_number) {
		clear();
		counter = 1;
		
		if(attach_dfs_number) {
			
			Iterator<Node<E>> it = g.heads();
			
			while(it.hasNext()) {
				psTraversalWithDfsNumber(it.next());
			}
			
		} else {
			Iterator<Node<E>> it = g.heads();
			
			while(it.hasNext()) {
				psTraversal(it.next());
			}
		}
		return rt;
	}

	/**
	 * To check if the graph is cyclic, i first run a postOrder-traversal of it and then check if there is a back edge.
	 *  Determining if there is a back edge is done by comparing the post order number between the current node in the graph and the next
	 *  if next node has a higher or equal (to take into account reflexive edges) number there is a back edge.
	 */
	@Override
	public boolean isCyclic(DirectedGraph<E> graph) {
			
		Iterator<Node<E>> all = graph.iterator();
		postOrder(graph);
		
		while(all.hasNext()) {
			Node<E> n = all.next();
			Iterator<Node<E>> it = n.succsOf();
			while(it.hasNext()) {
				if(n.num <= it.next().num) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Since topological order is basically a postorder traversal reversed, i just implemented this as such, using Collections.reverse.
	 */
	@Override
	public List<Node<E>> topSort(DirectedGraph<E> graph) {

		List<Node<E>> top = postOrder(graph);
		Collections.reverse(top);
		return top;
	}
}