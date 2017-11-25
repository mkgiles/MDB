package structures;

public class Node<T> {
	//Nodes are the core of List structures, a way of encapsulating data and position relative to other nodes.
	public T data;
	public Node<T> next;
	Node<T> prev;
	//Generic Node constructor given data and a position. All other constructors call this one with different parameters.
	Node(T data, Node<T> next, Node<T> prev) {
		this.data = data;
		if(next == null)
			this.next = null;
		else
			this.next = next;
		if(prev == null)
			this.prev = null;
		else
			this.prev = prev;
	}
	Node(T data, Node<T> next){
		this(data, next, null);
	}
	Node(T data, DataList<T> next) {
		this(data, next.head, null);
	}
	Node(Node<T> prev, T data) {
		this(data, null, prev);
	}
	Node(T data) {
		this(data, null, null);
	}
	Node(){
		this(null, null, null);
	}
}