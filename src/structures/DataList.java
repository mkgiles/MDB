package structures;

import java.util.function.Predicate;

public class DataList<T> {
	//List is a doubly linked list.
	Node<T> head;
	private Node<T> tail;
	//Initialises empty list.
	public DataList() {
		head = null;
		tail = null;
	}
	//One element list.
	public DataList(T data) {
		head = new Node<T>(data);
		tail = head;
	}
	//Implements list which is a copy of a prior list with a new data item prefixed on.
	public DataList(T data, DataList<T> list) {
		head = new Node<T>(data, copy(list));
	}
	//Implements a list of fixed length containing nothing but a particular preset value.
	public DataList(int length, T data) {
		this(data);
		for(int i = 1; i<length; i++)
			this.append(data);
	}
	//Safely copies a list to prevent possible reference issues
	public DataList<T> copy(DataList<T> list){
		DataList<T> temp = new DataList<T>(list.head.data);
		Node<T> head = list.head.next;
		while(head != null) {
			temp.append(head.data);
		}
		return temp;
	}
	//Appends data to end of list.
	public void append(T data) {
		tail.next = new Node<T>(tail, data);
		tail = tail.next;
	}
	//Prepends data to start of list.
	public void prepend(T data) {
		Node<T> temp = new Node<T>(data, this);
		this.head = temp;
	}
	//Iterates through list to get length.
	public int length() {
		int i = 0;
		for(Node<T> temp = head;temp!=null;temp=temp.next,i++);
		return i;
	}
	//Inserts data after specified index.
	public void insert(T data, int index) {
		//Needs refactoring.
		if(index<0)
			prepend(data);
		else {
			Node<T> temp = head;
			for(int i = index;i>=1;i--,temp=temp.next) {
				if(temp.next == null)
					break;
			}
			temp.next = new Node(data, temp.next, temp);
			if(temp.next != null)
				temp.next.next.prev = temp.next;
		}
	}
	//Gets the first element of list to return True for a predicate
	public T get(Predicate<T> p) {
		for(Node<T> temp = head; temp != null; temp = temp.next) {
			if(p.test(temp.data))
				return temp.data;
		}
		return null;
	}
	//gets the Node at a given index.
	public Node<T> getNode(int index) {
		Node<T> temp = head;
		for(int i = 0; i<index && temp!=null; i++, temp = temp.next);
		if(temp!=null)
			return temp;
		return null;
	}
	//Gets the element at a given index.
	public T get(int index) {
		Node<T> node = getNode(index);
		return node==null?null:node.data;
	}
	//Overwrites the node at a given index.
	public void set(int index, T data) {
		getNode(index).data = data;
	}
}
