package structures;

import java.lang.reflect.Array;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.Function;

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
	private static <K> DataList<K> mergesort(DataList<K> list, BiPredicate<K,K> p){
		DataList<K> sorted;
		if(list.length() == 0 || list.length() == 1)
			return list;
		int midpoint = list.length()/2;
		DataList<K> left=new DataList<K>(), right = new DataList<K>();
		for(int i = 0; i<midpoint;i++)
			left.append(list.get(i));
		for(int i = midpoint;i<list.length();i++)
			right.append(list.get(i));
		left = mergesort(left, p);
		right = mergesort(right, p);
		sorted = merge(left, right, p);
		return sorted;
	}
	private static <K> DataList<K> merge(DataList<K> left, DataList<K> right, BiPredicate<K,K> p) {
		DataList<K> merged = new DataList<K>();
		int i=0,j=0;
		for(;i<left.length()&&j<right.length();) {
			if(p.test(left.get(i), right.get(j))) 
				merged.append(left.get(i++));
			else
				merged.append(right.get(j++));
		}
		if(i<j)
			merged.append(left.getSubList(i));
		else
			merged.append(right.getSubList(j));
		return merged;
	}
	public DataList<T> sort(BiPredicate<T,T> p){
		return mergesort(this, p);
	}
	//Safely copies a list to prevent possible reference issues
	public static <K> DataList<K> copy(DataList<K> list){
		if(list.head == null)
			return new DataList<K>();
		DataList<K> temp = new DataList<K>(list.head.data);
		Node<K> head = list.head.next;
		while(head != null) {
			temp.append(head.data);
			head=head.next;
		}
		return temp;
	}
	//Appends data to end of list.
	public void append(T data) {
		if(tail == null) {
			tail = new Node<T>(data);
			if(head == null)
				head = tail;
		}
		else {
			tail.next = new Node<T>(tail, data);
			tail = tail.next;
		}
	}
	public void append(DataList<T> list) {
		DataList<T> temp = copy(list);
		this.tail.next = temp.head;
		temp.head.prev = this.tail; 
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
	public DataList<T> getSubList(int index){
		DataList<T> sublist = copy(this);
		sublist.head = sublist.getNode(index);
		return sublist;
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
	public void drop(int index) {
		Node<T> node = getNode(index);
		node.prev.next = node.next;
		node.next.prev = node.prev;
	}
	public void drop(Predicate<T> p) {
		for(Node<T> temp = head; temp != null; temp = temp.next) {
			if(p.test(temp.data)) {
				if(length() > 1) {
					temp.next.prev = temp.prev;
					temp.prev.next = temp.next;
				}
				else {
					this.head = null;
					this.tail = null;
				}
			}
		}
	}
	public DataList<T> getSubList(Predicate<T> p){
		DataList<T> temp = copy(this);
		for(Node<T> node = temp.head; node != null; node = node.next) {
			if(!p.test(node.data)) {
				node.next.prev = node.prev;
				node.prev.next = node.next;
			}
		}
		return temp;
	}
	public <R> DataList<R> filter(Function<T, R> f){
		DataList<T> temp = copy(this);
		DataList<R> result = new DataList<R>();
		for(Node<T> node = temp.head; node!=null; node=node.next) {
			result.append(f.apply(node.data));
		}
		return result;
	}
}
