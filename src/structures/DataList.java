package structures;

import java.util.function.Predicate;

public class DataList<T> {
	private Node head;
	private Node tail;

	private class Node {
		private T data;
		private Node next;
		private Node prev;

		private Node(T data, Node next, Node prev) {
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
		private Node(T data, Node next){
			this(data, next, null);
		}
		private Node(T data, DataList<T> next) {
			this(data, next.head, null);
		}
		private Node(Node prev, T data) {
			this(data, null, prev);
		}
		private Node(T data) {
			this(data, null, null);
		}
	}

	public DataList(T data) {
		head = new Node(data);
		tail = head;
	}
	public DataList(T data, DataList<T> list) {
		head = new Node(data, copy(list));
	}
	public DataList<T> copy(DataList<T> list){
		DataList<T> temp = new DataList<T>(list.head.data);
		Node head = list.head.next;
		while(head != null) {
			temp.append(head.data);
		}
		return temp;
	}
	public void append(T data) {
		tail.next = new Node(tail, data);
	}
	public void prepend(T data) {
		Node temp = new Node(data, this);
		this.head = temp;
	}
	public int length() {
		int i = 0;
		for(Node temp = head;head!=null;head=head.next,i++);
		return i;
	}
	public void insert(T data, int index) {
		//Needs refactoring.
		if(index<0)
			prepend(data);
		else {
			Node temp = head;
			for(int i = index;i>=1;i--,temp=temp.next) {
				if(temp.next == null)
					break;
			}
			temp.next = new Node(data, temp.next, temp);
			if(temp.next != null)
				temp.next.next.prev = temp.next;
		}
	}
	public T search(Predicate<T> p) {
		for(Node temp = head; temp != null; temp = temp.next) {
			if(p.test(temp.data))
				return temp.data;
		}
		return null;
	}
}
