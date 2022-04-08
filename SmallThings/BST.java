/*
* Date: 2021-11-01.
* File Name: BST.Java
* Author: Morgan Andersson
*/

public class BST {
	int value;
	
	BST left = null;
	BST right = null;
	
	BST(int val) { value = val; }
	
	void add(int n) { 
		if(this.contains(n)) {
			return;
		}
		if(n < this.value) {
			if(left != null) {
				left.add(n);
			} else {
				left = new BST(n);
			}				
		}
		if(n > this.value) {
			if(right != null) {
				right.add(n);
			} else {
				right = new BST(n);
			}	
		}
	}
	
	boolean contains(int n) { 
		//return true if the value of the current node is equal to n.
		if(this.value == n) {
			return true;
		}
		//check if the n is less than value, and if there is a left-node call contains on that node
		if(this.value > n && left != null && left.contains(n)) {
			return true; 
		}
		//do the same but for right node (i.e if n is > value)
		if(this.value < n && right != null && right.contains(n)) {
			return true;
		}
	
		return false;
	}
}
