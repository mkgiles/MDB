package structures;

import java.util.function.BiPredicate;

public class HashList<T> {
	/* HashList contains an array of Nodes, these Nodes are linked together to form a DataList which can be parsed and sorted for display.
	 * Inserting data into the HashList requires hashing the input data with an FNV 1-a hash to dictate where in the Array it goes.
	 * While sorting the DataList is done using a Mergesort with a comparative BiPredicate to decide which order the Nodes are linked.
	 * The Head and Tail Nodes contained by the HashList specify the start and end points of this virtual DataList.
	 * As such the HashList can be treated like a bucket array or like a DataList, depending on which functions are used.*/
	private Node<T>[] data;
	public Node<T> head;
	private Node<T> tail;
	//Create an empty array of Nodes of fixed length.
	@SuppressWarnings("unchecked")
	public HashList(int length) {
		data = (Node<T>[])(new Object[length]);
	}
	//Get the data at the specified key.
	public T get(String key) {
		return data[FNV.hash(key.getBytes())].data;
	}
	//Merge half of the mergesort algorithm.
	private static Node[] merge(Node[] left, Node[] right, BiPredicate<Node,Node> p) {
		Node[] merged = new Node[left.length + right.length];
		int n = 0,i=0,j=0;
		for(;i<left.length && j<right.length;n++) {
			if(p.test(left[i],right[j]))
				merged[n] = left[i++];
			else
				merged[n] = right[j++];
		}
		if(left.length > right.length) 
			for(;n<left.length;n++)
				merged[n] = left[i];
		else
			for(;n<right.length;n++)
				merged[n] = right[i];
		return merged;
	}
	//Sort half of the mergesort algorithm.
	private static Node[] mergesort(Node[] data, BiPredicate<Node,Node> p) {
		Node[] sorted = new Node[data.length];
		if(data.length == 1)
			sorted = data;
		else {
			int midpoint = data.length/2;
			Node[] left=new Node[midpoint], right = new Node[data.length-midpoint];
			for(int i = 0; i<midpoint-1;i++)
				left[i] = data[i];
			for(int i = midpoint;i<data.length;i++)
				right[i-midpoint] = data[i];
			left = mergesort(left, p);
			right = mergesort(right, p);
			if(p.test(left[left.length-1], right[0])) {
				for(int i = 0; i<data.length;i++) {
					if(i<midpoint)
						sorted[i] = left[i];
					else
						sorted[i] = right[i-midpoint];
				}
			}
			else
				sorted = merge(left, right, p);
		}
		return sorted;
	}
	//Function which calls mergesort on the HashList with a given comparison function.
	private void sort(BiPredicate<Node,Node> p) {
		Node[] list = mergesort(data, p);
		for(int i = 0; i<data.length;i++) {
			if(i!=data.length)
				list[i].next = data[i+1];
			else
				list[i].next = null;
			if(i!=0)
				list[i].prev = data[i-1];
			else
				list[i].prev = null;
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
	public DataList list() {
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
