package maze;

public class Set<E> implements Cloneable{
	
	private Node<E> head;
	private Node<E> cursor;
	
	
	public Set() {
		
		head = null;
		
		cursor = null;
	}

	public void enter(E item) {
		
		if(isElement(item) == false) {
			
			head = new Node<E> (item, head);
		}
		
	}

	public boolean isElement(E item) {
		
		Node<E> pointer;
		
		for(pointer = head; pointer != null; pointer = pointer.getLink()) {
			
			if(item.equals(pointer.getData())) {
				
				return true;
				
			}
		}
		
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public Set<E> clone(){
		
		Set<E> answer;
		
		try {
			
			answer = (Set<E>) super.clone();
			answer.head = head.clone();
			answer.cursor = head;
					
		}
		
		catch(CloneNotSupportedException e) {
			
			throw new RuntimeException("this class does not implement cloneable");
		}
		
		return answer;
	}
	
	public Node<E> getCursor(){
		
		return cursor;
		
	}
	
	public Node<E> getHead(){
		
		return head;
		
	}

}
