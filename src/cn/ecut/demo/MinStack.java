package cn.ecut.demo;

import java.util.Queue;
import java.util.Stack;

import com.sun.jmx.remote.internal.ArrayQueue;

/**
 * 155. 最小栈
 * @see https://leetcode-cn.com/problems/min-stack/
 * @author xiang
 *
 */
class MinStack {

   // Queue<Integer> queue;
   // Queue<Integer> minqueue;
	
	Stack<Integer> stack ;  // add,peek,pop
	Stack<Integer> helpstack ;
    /** initialize your data structure here. */
    public MinStack() {
    	stack = new Stack();
    	helpstack = new Stack();
    }
    
    public void push(int x) {
    	stack.add(x);
    	//不是  helpstack ！= null   是 >= ，不是>
    	if(helpstack.isEmpty() || helpstack.peek()>=x)
    		helpstack.add(x);
    }
    
    public void pop() {
    	//stack.lastElement();
    	if(!stack.isEmpty()) {
    		int top = stack.pop();
    		//  /!!!
    		if(top==helpstack.peek()) {
    			helpstack.pop();
    		}
    	}
    }
    
    public int top() {
    	if(!stack.isEmpty()) {
    		return stack.peek();
    	}
    	return 0;
    }
    
    public int getMin() {
    	if(!helpstack.isEmpty()) {
    		helpstack.peek();
    	}
    	return 0;
    }
}
