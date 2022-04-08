/*
* Date: 2021-10-23.
* File Name: MyBFS.Java
* Author: Morgan Andersson
*
*/

package ma223yd;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import graphs.DirectedGraph;
import graphs.Node;


/**
* Class Description: This is an implementation of Breadth-First Search that uses the Queue structure for traversal. 
* For every Node, its successors are added to the queue before any further traversal, which generates the breadth-first approach.
* @version 1.0, 23 oct 2021
* @author Morgan Andersson
*/

public class MyBFS<E> implements graphs.BFS<E> {
	
	HashSet<Node<E>> visited = new HashSet<Node<E>>();
	List<Node<E>> rt = new ArrayList<Node<E>>();
	int counter;

	private void clear() {
		rt.clear();
		visited.clear();
		counter = 1;
	}
	
	private void bfsTraversal(Node<E> cur) {
		Queue<Node<E>> queue = new LinkedList<>(); //set up a queue to store the nodes to be visisted
		queue.add(cur);
		visited.add(cur);
		
		while(!queue.isEmpty()) {
			Node<E> n = queue.remove();
			n.num = counter++;
			rt.add(n);
			
			Iterator<Node<E>> it = n.succsOf();
			while(it.hasNext()) {
				Node<E> next = it.next();
				if(!visited.contains(next)) {
					queue.add(next); //add next node to the queue
					visited.add(next); //mark the node as visited, preventing it from being queued several times.
				}
			}
		}
	}
	
	@Override
	public List<Node<E>> bfs(DirectedGraph<E> graph, Node<E> root) {
		clear();
		bfsTraversal(root); //do the traversal on root node.
		return rt;
	}

	@Override
	public List<Node<E>> bfs(DirectedGraph<E> graph) {
		clear();
		Iterator<Node<E>> it = graph.heads();
		while(it.hasNext()) {
			Node<E> node = (Node<E>) it.next(); //loop through all heads of the graph and call bfsTraversal on all of them.
			bfsTraversal(node);
		}
		return rt;
	}
}
