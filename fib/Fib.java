/*
* Date: 2021-11-01.
* File Name: Fib.Java
* Author: Morgan Andersson
*/

import java.util.ArrayList;

public class Fib {
	
	public static int fib(int n) {
		if(n <= 1) { //if the value is 0 or 1, return the value
			return n;
		}
		return fib(n-1) + fib(n-2); //use the mentioned calculation from above
	}
	
}
