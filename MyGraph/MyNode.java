/*
* Date: 2021-10-23.
* File Name: MyNode.Java
* Author: Morgan Andersson
*
*/

package ma223yd;

import java.util.HashSet;
import java.util.Iterator;

import graphs.Node;

/**
* This class represents the Nodes in a directed graph, that knows of its predecessors and successors in the graph.
* 
* @version 1.0, 23 oct 2021
* @author Morgan Andersson
*/

public class MyNode<E> extends graphs.Node<E> {

	private HashSet<Node<E>> preds = new HashSet<Node<E>>();
	private HashSet<Node<E>> succs = new HashSet<Node<E>>();
	
	
	protected MyNode(E item) {
		super(item);
		
	}

	@Override
	public boolean hasSucc(Node<E> node) {
		return (succs.contains(node));
	}

	@Override
	public int outDegree() {
		return succs.size();
	}

	@Override
	public Iterator<Node<E>> succsOf() {
		return succs.iterator();
	}

	@Override
	public boolean hasPred(Node<E> node) {
		return (preds.contains(node));
	}

	@Override
	public int inDegree() {
		return preds.size();
	}

	@Override
	public Iterator<Node<E>> predsOf() {
		return preds.iterator();
	}

	@Override
	protected void addSucc(Node<E> succ) {
		succs.add(succ);
	}

	@Override
	protected void removeSucc(Node<E> succ) {
		succs.remove(succ);
	}

	@Override
	protected void addPred(Node<E> pred) {
		preds.add(pred);
	}

	@Override
	protected void removePred(Node<E> pred) {
		preds.remove(pred);
	}

	/**
	 * Remove all edges for this node. This is done by looping thorugh all succs and removing the reference to this as a pred and vice veras for preds.
	 * Then removes the succs and preds for this node, completing the removal of edges.
	 */
	@Override
	protected void disconnect() {
		
		for(Node<E> node : succs) {
			MyNode<E> nd = (MyNode<E>) node;
			nd.removePred(this);
		}
		
		for(Node<E> node :  preds) {
			MyNode<E> nd = (MyNode<E>) node;
			nd.removeSucc(this);
		}
		succs.clear();
		preds.clear();
	}
}