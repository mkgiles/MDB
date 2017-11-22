package structures;

import java.util.function.Predicate;

public class DataList<T> {
	Node<T> head;
	private Node<T> tail;

	public DataList(T data) {
		head = new Node(data);
		tail = head;
	}
	public DataList(T data, DataList<T> list) {
		head = new Node(data, copy(list));
	}
	public DataList(int length, T data) {
		this(data);
		for(int i = 1; i<length; i++)
			this.append(data);
	}
	public DataList<T> copy(DataList<T> list){
		DataList<T> temp = new DataList<T>(list.head.data);
		Node<T> head = list.head.next;
		while(head != null) {
			temp.append(head.data);
		}
		return temp;
	}
	public void append(T data) {
		tail.next = new Node(tail, data);
	}
	public void prepend(T data) {
		Node<T> temp = new Node(data, this);
		this.head = temp;
	}
	public int length() {
		int i = 0;
		for(Node<T> temp = head;head!=null;head=head.next,i++);
		return i;
	}
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
	public T get(Predicate<T> p) {
		for(Node<T> temp = head; temp != null; temp = temp.next) {
			if(p.test(temp.data))
				return temp.data;
		}
		return null;
	}
	private Node<T> getNode(int index) {
		Node<T> temp = head;
		for(int i = 0; i<index && temp!=null; i++, temp = temp.next);
		if(temp!=null)
			return temp;
		return null;
	}
	public T get(int index) {
		Node<T> node = getNode(index);
		return node==null?null:node.data;
	}
	public void set(int index, T data) {
		getNode(index).data = data;
	}
}
