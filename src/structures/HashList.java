package structures;

import java.lang.reflect.Array;
import java.util.function.BiPredicate;

/**
 * The Class HashList.
 *
 * @param <T>
 *            the generic type
 */
public class HashList<T> {
	
	/** The data. */
	/*
	 * HashList contains an array of Nodes, these Nodes are linked together to form
	 * a DataList which can be parsed and sorted for display. Inserting data into
	 * the HashList requires hashing the input data with an FNV 1-a hash to dictate
	 * where in the Array it goes. While sorting the DataList is done using a
	 * Mergesort with a comparative BiPredicate to decide which order the Nodes are
	 * linked. The Head and Tail Nodes contained by the HashList specify the start
	 * and end points of this virtual DataList. As such the HashList can be treated
	 * like a bucket array or like a DataList, depending on which functions are
	 * used.
	 */
	private Node<T>[] data;
	
	/** The head. */
	public Node<T> head;
	
	/** The tail. */
	public Node<T> tail;

	/**
	 * Instantiates a new hash list.
	 *
	 * @param length
	 *            the length
	 */
	// Create an empty array of Nodes of fixed length.
	@SuppressWarnings("unchecked")
	public HashList(int length) {
		data = (Node<T>[]) Array.newInstance(new Node<T>().getClass(), length);
	}

	/**
	 * Gets the data matching the key.
	 *
	 * @param key
	 *            the key
	 * @return the t
	 */
	// Get the data at the specified key.
	public T get(String key) {
		Node<T> temp = this.data[FNV.hash(key.getBytes()) % this.data.length];
		return temp == null ? null : temp.data;
	}

	/**
	 * Puts data into space matching key.
	 *
	 * @param key
	 *            the key
	 * @param data
	 *            the data
	 */
	public void put(String key, T data) {
		Node<T> temp = new Node<T>(tail, data);
		this.data[FNV.hash(key.getBytes()) % this.data.length] = temp;
		if (this.head == null)
			this.head = temp;
		if (this.tail != null)
			this.tail.next = temp;
		this.tail = temp;
	}

	/**
	 * Drops data at key.
	 *
	 * @param key
	 *            the key
	 */
	public void drop(String key) {
		Node<T> temp = data[FNV.hash(key.getBytes()) % data.length];
		temp.next = null;
		temp.prev = null;
		temp.data = null;
	}

	/**
	 * Sorts the hashlist.
	 *
	 * @param p
	 *            the p
	 */
	public void sort(BiPredicate<T, T> p) {
		DataList<T> sort = list().sort(p);
		this.head = sort.head;
		for (int i = 0; i < sort.length(); i++) {
			list().getNode(i).next = sort.getNode(i).next;
			list().getNode(i).prev = sort.getNode(i).prev;
		}
	}

	/**
	 * The Class FNV.
	 */
	// Class for the Fowler-Noll-Vo Hash algorithm.
	private static class FNV {
		
		/** The Constant prime. */
		// Smallest FNV Prime.
		private static final long prime = 16777619;
		
		/** The Constant offset. */
		// FNV offset for integer lengths.
		private static final long offset = 2166136261L;

		/**
		 * Hash.
		 *
		 * @param data
		 *            the data
		 * @return the int
		 */
		// FNV hash function.
		private static int hash(byte[] data) {
			long hash = offset;
			for (byte unit : data) {
				hash ^= unit;
				hash *= prime;
			}
			hash = (hash >> 32) ^ (hash & 0xffffffff);
			return Math.abs((int) hash);
		}
	}

	/**
	 * Method for extracting the virtual list as a DataList object.
	 *
	 * @return the data list
	 */
	public DataList<T> list() {
		DataList<T> list = new DataList<T>(head.data);
		for (Node<T> local = head.next, foreign = list.head; local != null; local = local.next, foreign = foreign.next) {
			foreign.next = new Node<T>(foreign, local.data);
		}
		return list;
	}
}
