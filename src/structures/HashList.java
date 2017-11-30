package structures;

import java.lang.reflect.Array;
import java.util.function.BiPredicate;

public class HashList<T> {
	/* HashList contains an array of Nodes, these Nodes are linked together to form a DataList which can be parsed and sorted for display.
	 * Inserting data into the HashList requires hashing the input data with an FNV 1-a hash to dictate where in the Array it goes.
	 * While sorting the DataList is done using a Mergesort with a comparative BiPredicate to decide which order the Nodes are linked.
	 * The Head and Tail Nodes contained by the HashList specify the start and end points of this virtual DataList.
	 * As such the HashList can be treated like a bucket array or like a DataList, depending on which functions are used.*/
	private Node<T>[] data;
	public Node<T> head;
	public Node<T> tail;
	//Create an empty array of Nodes of fixed length.
	@SuppressWarnings("unchecked")
	public HashList(int length) {
		data = (Node<T>[]) Array.newInstance(new Node<T>().getClass(), length);
	}
	//Get the data at the specified key.
	public T get(String key) {
		return data[FNV.hash(key.getBytes())%this.data.length].data;
	}
	public void put(T data) {
		Node<T> temp = new Node<T>(tail, data);
		this.data[Math.abs(FNV.hash(data.toString().getBytes())%this.data.length)] = temp;
		if(this.head == null)
			this.head = temp;
		if(this.tail != null)
			this.tail.next = temp;
		this.tail = temp;
	}
	public void sort(BiPredicate<T,T> p){
		DataList<T> sort = list().sort(p);
		this.head = sort.head;
		for(int i=0;i<sort.length();i++) {
			list().getNode(i).next = sort.getNode(i).next;
			list().getNode(i).prev = sort.getNode(i).prev;
		}
	}
	//Class for the Fowler-Noll-Vo Hash algorithm.
	private static class FNV {
		//Smallest FNV Prime.
		private static final long prime = 16777619;
		//FNV offset for integer lengths.
		private static final long offset = 2166136261L;
		//FNV hash function.
		private static int hash(byte[] data) {
			long hash = offset;
			for(byte unit : data) {
				hash ^= unit;
				hash *= prime;
			}
			hash = (hash>>32) ^ (hash & 0xffffffff);
			return (int) hash;
		}
	}
	//Method for extracting the virtual list as a DataList object.
	public DataList<T> list() {
		DataList<T> list = new DataList<T>(head.data);
		for(Node<T> local = head.next,foreign = list.head; local != null; local = local.next,foreign = foreign.next) {
			foreign.next = new Node<T>(foreign, local.data);
		}
		return list;
	}
	//Method for generating a hash for any object.
	public static int uid(Object object) {
		return FNV.hash(object.toString().getBytes());
	}
}
