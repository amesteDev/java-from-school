/*
* Date: 2021-10-04.
* File Name: Queue.Java
* Author: Morgan Andersson
*
*/

import java.util.Iterator;

/**
 * A queue that works like a singly LinkedList
 * @author Morgan Andersson
 *
 */
public class Queue<E> implements QueueInterface<E> {
	
	private Node head;
	private Node tail;
	private int size = 0;
	/**
	 * Class that represents a Node in the LinkedList
	 * 
	 * @author Morgan Andersson
	 *
	 */
	private class Node {
		private E nodeData;
		private Node nextNode;
		
		Node(E element){
			this.nodeData = element;
			this.nextNode = null;
		}
		
		public E getNodeData() {
			return this.nodeData;
		}
	}
	
	public Queue(){
		this.head = null;
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		return(this.size == 0);
	}
	
	/**
	 * build and return a string from all the elements in the list.
	 */
	public String toString() {
		String string = "[";
		Iterator<E> it = iterator();
		while(it.hasNext()) {
			string += " " + it.next();
		}
		string += " ]";
		
		return string;
	}
	
	/**
	 * adds an element to the end of the queue/list
	 */
	@Override
	public void enqueue(E element) {
		Node oldTail = this.tail;
		this.tail = new Node(element);
		if(this.isEmpty()) {
			head = tail;
		} else {
			oldTail.nextNode = tail;
		}
		this.size++;
	}

	/**
	 * removes the first element i the queue, if the list is empty throw exception.
	 */
	@Override
	public E dequeue() {
		if(isEmpty()) {
			throw new IndexOutOfBoundsException("Nothing to dequeue");
		}
		
		E data = head.nodeData;
		System.out.println("Removing: " + data);
		this.head = this.head.nextNode;
		
		this.size--;
		return data;
	}
	
	@Override
	public E first() {
		if(isEmpty()) {
			throw new IndexOutOfBoundsException("Nothing to see here");
		}
		E data = head.nodeData;
		return data;
	}
	
	@Override
	public E last() {
		if(isEmpty()) {
			throw new IndexOutOfBoundsException("Nothing to see here");
		}
		E data = this.tail.nodeData;
		return data;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new QueueIterator<E>();
	}
	
	/**
	 * Iterator for the LinkedList
	 * @author Morgan Andersson
	 *
	 */
	public class QueueIterator<E> implements Iterator <E> {
		
		private int count = 0;
		
		private Node currentNode; //keep track of the current node
		
		public QueueIterator() {
			currentNode = head;
		}

		@Override
		public boolean hasNext() {
			return(count<size); //check if there is a node next in the list
		}
		
		/**
		 * Returns the value of the current node and then moves forward one step in the list.
		 */
		@Override
		public E next() {
			E nodeData = (E) currentNode.nodeData;
			count++;
			currentNode = currentNode.nextNode;
			return nodeData;
		}
		
		@Override
		public void remove() {
			throw new RuntimeException("No remove() for you!");
		}
	}
}
