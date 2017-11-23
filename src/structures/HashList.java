package structures;

import java.util.function.BiPredicate;

//Vector of entries, ordered list of keys.
public class HashList<T> {
	private Node<T>[] data;
	public Node<T> head;
	private Node<T> tail;
	@SuppressWarnings("unchecked")
	public HashList(int length) {
		data = (Node<T>[])(new Object[length]);
	}
	public T get(String key) {
		return data[FNV.hash(key.getBytes())].data;
	}
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
	private static class FNV {
		private static final long prime = 16777619;
		private static final long offset = 2166136261L;
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
	
	public DataList list() {
		DataList<T> list = new DataList<T>(head.data);
		for(Node<T> local = head.next,foreign = list.head; local != null; local = local.next,foreign = foreign.next) {
			foreign.next = new Node<T>(foreign, local.data);
		}
		return list;
	}
	
	public static int uid(Object object) {
		return FNV.hash(object.toString().getBytes());
	}
}
