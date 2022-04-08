/*
* Date: 2021-10-04.
* File Name: QueueTest.Java
* Author: Morgan Andersson
*
*/

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit tests for Queue.java
 * 
 * @author Morgan Andersson
 *
 */
class QueueTest {
	Queue que = new Queue();
	@Test
	void testExpectedException() {
	  Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
		  que.dequeue();
	  });	 
	  Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
		  que.first();
	  });	 
	  Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
		  que.last();
	  });	 
	}
	
	@Test
	void testFirstAndLast() {
	    que.enqueue(0); //test integer
	    que.enqueue("titta"); // test string
	    assertEquals("titta", que.last());
	    assertEquals(0, que.first());
	}
	
	@Test
	void testEnque() {
		for(int i = 0; i < 100; i++) {
			que.enqueue(i);
		}
		que.enqueue("Hej");
		assertEquals("Hej", que.last());
		que.enqueue(-12);
		assertEquals(-12, que.last());
		assertEquals(102, que.size());
	}
	

	@Test
	void testDequeue() {
		que.enqueue(0);
		que.enqueue(0);
		que.enqueue(1);
		que.enqueue(3);
		que.dequeue();
		assertEquals(que.toString(), "[ 0 1 3 ]");
	}
	
	@Test
	void testEmpty() {
		assertEquals(que.isEmpty(), true);
		que.enqueue(0);
		assertEquals(que.isEmpty(), false);
		que.dequeue();
		assertEquals(que.isEmpty(), true);
	}
	
	/**
	 * Add many elements to the queue and check that the size is correct.
	 */
	@Test
	void testSize() {
		assertEquals(0, que.size());
		for(int i = 0; i < 100000; i++) {
			que.enqueue(i);
		}
		assertEquals(100000, que.size());
	}
}
