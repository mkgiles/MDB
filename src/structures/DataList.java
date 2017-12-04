package structures;

import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.Function;

/**
 * The Class DataList.
 *
 * @param <T>
 *            the generic type
 */
public class DataList<T> {
	
	/** The head. */
	// List is a doubly linked list.
	Node<T> head;
	
	/** The tail. */
	private Node<T> tail;

	/**
	 * Instantiates a new data list.
	 */
	// Initialises empty list.
	public DataList() {
		head = null;
		tail = null;
	}

	/**
	 * Instantiates a new data list.
	 *
	 * @param data
	 *            the data
	 */
	// One element list.
	public DataList(T data) {
		head = new Node<T>(data);
		tail = head;
	}

	// Implements list which is a copy of a prior list with a new data item prefixed
	/**
	 * Instantiates a new data list.
	 *
	 * @param data
	 *            the data
	 * @param list
	 *            the list
	 */
	// on.
	public DataList(T data, DataList<T> list) {
		head = new Node<T>(data, copy(list));
	}

	// Implements a list of fixed length containing nothing but a particular preset
	/**
	 * Instantiates a new data list.
	 *
	 * @param length
	 *            the length
	 * @param data
	 *            the data
	 */
	// value.
	public DataList(int length, T data) {
		this(data);
		for (int i = 1; i < length; i++)
			this.append(data);
	}

	/**
	 * Mergesort.
	 *
	 * @param <K>
	 *            the key type
	 * @param list
	 *            the list
	 * @param p
	 *            the p
	 * @return the data list
	 */
	private static <K> DataList<K> mergesort(DataList<K> list, BiPredicate<K, K> p) {
		DataList<K> sorted;
		if (list.length() == 0 || list.length() == 1)
			return list;
		int midpoint = list.length() / 2;
		DataList<K> left = new DataList<K>(), right = new DataList<K>();
		for (int i = 0; i < midpoint; i++)
			left.append(list.get(i));
		for (int i = midpoint; i < list.length(); i++)
			right.append(list.get(i));
		left = mergesort(left, p);
		right = mergesort(right, p);
		sorted = merge(left, right, p);
		return sorted;
	}

	/**
	 * Merge.
	 *
	 * @param <K>
	 *            the key type
	 * @param left
	 *            the left
	 * @param right
	 *            the right
	 * @param p
	 *            the p
	 * @return the data list
	 */
	private static <K> DataList<K> merge(DataList<K> left, DataList<K> right, BiPredicate<K, K> p) {
		DataList<K> merged = new DataList<K>();
		int i = 0, j = 0;
		for (; i < left.length() && j < right.length();) {
			if (p.test(left.get(i), right.get(j)))
				merged.append(left.get(i++));
			else
				merged.append(right.get(j++));
		}
		if (i < j)
			merged.append(left.getSubList(i));
		else
			merged.append(right.getSubList(j));
		return merged;
	}

	/**
	 * Sort.
	 *
	 * @param p
	 *            the p
	 * @return the data list
	 */
	public DataList<T> sort(BiPredicate<T, T> p) {
		return mergesort(this, p);
	}

	/**
	 * Copy.
	 *
	 * @param <K>
	 *            the key type
	 * @param list
	 *            the list
	 * @return the data list
	 */
	// Safely copies a list to prevent possible reference issues
	public static <K> DataList<K> copy(DataList<K> list) {
		if (list.head == null)
			return new DataList<K>();
		DataList<K> temp = new DataList<K>(list.head.data);
		Node<K> head = list.head.next;
		while (head != null) {
			temp.append(head.data);
			head = head.next;
		}
		return temp;
	}

	/**
	 * Append.
	 *
	 * @param data
	 *            the data
	 */
	// Appends data to end of list.
	public void append(T data) {
		if (tail == null) {
			tail = new Node<T>(data);
			if (head == null)
				head = tail;
		} else {
			tail.next = new Node<T>(tail, data);
			tail = tail.next;
		}
	}

	/**
	 * Append.
	 *
	 * @param list
	 *            the list
	 */
	public void append(DataList<T> list) {
		DataList<T> temp = copy(list);
		this.tail.next = temp.head;
		temp.head.prev = this.tail;
	}

	/**
	 * Prepend.
	 *
	 * @param data
	 *            the data
	 */
	// Prepends data to start of list.
	public void prepend(T data) {
		Node<T> temp = new Node<T>(data, this);
		this.head = temp;
	}

	/**
	 * Length.
	 *
	 * @return the int
	 */
	// Iterates through list to get length.
	public int length() {
		int i = 0;
		for (Node<T> temp = head; temp != null; temp = temp.next, i++)
			;
		return i;
	}

	/**
	 * Insert.
	 *
	 * @param data
	 *            the data
	 * @param index
	 *            the index
	 */
	// Inserts data after specified index.
	public void insert(T data, int index) {
		// Needs refactoring.
		if (index < 0)
			prepend(data);
		else {
			Node<T> temp = head;
			for (int i = index; i >= 1; i--, temp = temp.next) {
				if (temp.next == null)
					break;
			}
			temp.next = new Node<T>(data, temp.next, temp);
			if (temp.next != null)
				temp.next.next.prev = temp.next;
		}
	}

	/**
	 * Gets the first element to match the predicate.
	 *
	 * @param p
	 *            the p
	 * @return the t
	 */
	// Gets the first element of list to return True for a predicate
	public T get(Predicate<T> p) {
		for (Node<T> temp = head; temp != null; temp = temp.next) {
			if (p.test(temp.data))
				return temp.data;
		}
		return null;
	}

	/**
	 * Gets the node at the corresponding index.
	 *
	 * @param index
	 *            the index
	 * @return the node
	 */
	// gets the Node at a given index.
	public Node<T> getNode(int index) {
		Node<T> temp = head;
		for (int i = 0; i < index && temp != null; i++, temp = temp.next)
			;
		if (temp != null)
			return temp;
		return null;
	}

	/**
	 * Gets the sub list from the index.
	 *
	 * @param index
	 *            the index
	 * @return the sub list
	 */
	public DataList<T> getSubList(int index) {
		DataList<T> sublist = copy(this);
		sublist.head = sublist.getNode(index);
		return sublist;
	}

	/**
	 * Gets the element at the index.
	 *
	 * @param index
	 *            the index
	 * @return the t
	 */
	// Gets the element at a given index.
	public T get(int index) {
		Node<T> node = getNode(index);
		return node == null ? null : node.data;
	}

	/**
	 * Sets the element at the index.
	 *
	 * @param index
	 *            the index
	 * @param data
	 *            the data
	 */
	// Overwrites the node at a given index.
	public void set(int index, T data) {
		getNode(index).data = data;
	}

	/**
	 * Drop.
	 *
	 * @param index
	 *            the index
	 */
	public void drop(int index) {
		Node<T> node = getNode(index);
		node.prev.next = node.next;
		node.next.prev = node.prev;
	}

	/**
	 * Drop.
	 *
	 * @param p
	 *            the p
	 */
	public void drop(Predicate<T> p) {
		for (Node<T> temp = head; temp != null; temp = temp.next) {
			if (p.test(temp.data)) {
				if (temp.prev != null)
					temp.prev.next = temp.next;
				else
					this.head = temp.next;
				if (temp.next != null)
					temp.next.prev = temp.prev;
				else
					this.tail = temp.prev;
			}
		}
	}

	/**
	 * Gets the sub list which matches the predicate.
	 *
	 * @param p
	 *            the p
	 * @return the sub list
	 */
	public DataList<T> getSubList(Predicate<T> p) {
		DataList<T> temp = copy(this);
		for (Node<T> node = temp.head; node != null; node = node.next) {
			if (!p.test(node.data)) {
				if (node.next != null)
					node.next.prev = node.prev;
				else
					temp.tail = node.prev;
				if (node.prev != null)
					node.prev.next = node.next;
				else
					temp.head = node.next;
			}
		}
		return temp;
	}

	/**
	 * Filter.
	 *
	 * @param <R>
	 *            the generic type
	 * @param f
	 *            the f
	 * @return the data list
	 */
	public <R> DataList<R> filter(Function<T, R> f) {
		DataList<T> temp = copy(this);
		DataList<R> result = new DataList<R>();
		for (Node<T> node = temp.head; node != null; node = node.next) {
			result.append(f.apply(node.data));
		}
		return result;
	}
}
