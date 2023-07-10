package maze;

import java.util.EmptyStackException;

public class Stack<E> implements Cloneable {
	
	private Node<E> top;
	
	public Stack() {
		
		top = null;
		
	}
	
	public void push(E item) {
		
		top = new Node<E>(item, top);
		
	}
	
	@SuppressWarnings("unchecked")
	public E pop() {
		
		E answer;
		
		if(top == null) {
			
			throw new EmptyStackException();
			
		}
		
		answer = (E) top.getData();
		top = top.getLink();
		
		return answer;				
		
	}
	
	@SuppressWarnings("unchecked")
	public E peek() {
		
		if(top == null) {
			
			throw new EmptyStackException();
			
		}
		
		return (E) top.getData();
	}
	
	public boolean isEmpty() {
		
		if(top == null) {
			
			return true;
			
		}
		
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public Stack<E> clone(){
		
		Stack<E> answer;
		
		try {
			
			answer = (Stack<E>) super.clone();
			
		}
		catch(CloneNotSupportedException e) {
			
			throw new RuntimeException("This class does not implement Cloneable");
			
		}
		
		return answer;
	}

}
