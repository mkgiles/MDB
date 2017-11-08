package structures;

public class DataList<T> {
	private Node head;
	private Node tail;

	private class Node {
		private T data;
		private Node next;
		private Node prev;

		private Node(T data, DataList<T> next, Node prev) {
			this.data = data;
			if(next == null)
				this.next = null;
			else
				this.next = next.head;
			if(prev == null)
				this.prev = null;
			else
				this.prev = prev;
		}
		private Node(T data, DataList<T> next){
			this(data, next, null);
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
}
