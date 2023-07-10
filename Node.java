package maze;

public class Node<E> implements Cloneable{

	private E data;
	private Node <E> link;
	
	public Node(E initialData, Node<E> initialLink) {
		
		this.data = initialData;
		
		this.link = initialLink;
	}
	
	public int listlength(Node<E> top) {
		
		Node<E> cursor;
		
		int answer;
		
		answer = 0;
		
		for(cursor = top; cursor != null; cursor = cursor.link) {
			
			answer++;
			
		}
		
		return answer;
		
	}

	public Node<E> getLink() {
		
		return link;
		
	}

	public Object getData() {
		
		return data;
	}
	
	public void setLink(Node<E> newLink) {
		
		link = newLink;
		
	}
	
	@SuppressWarnings("unchecked")
	public Node<E> clone(){
		
		Node<E> answer = null;
		
		try {
			answer = (Node<E>) super.clone();
			answer.link = link.clone();
		}
		
		catch(CloneNotSupportedException e) {
			
			e.printStackTrace();
			
		}
		
		return answer;
	}
}
