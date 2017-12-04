package structures;

/**
 * The Class Node.
 *
 * @param <T>
 *            the generic type
 */
public class Node<T> {
	// Nodes are the core of List structures, a way of encapsulating data and
	/** The data. */
	// position relative to other nodes.
	public T data;
	
	/** The next. */
	public Node<T> next;
	
	/** The prev. */
	Node<T> prev;

	// Generic Node constructor given data and a position. All other constructors
	/**
	 * Instantiates a new node.
	 *
	 * @param data
	 *            the data
	 * @param next
	 *            the next
	 * @param prev
	 *            the prev
	 */
	// call this one with different parameters.
	Node(T data, Node<T> next, Node<T> prev) {
		this.data = data;
		if (next == null)
			this.next = null;
		else
			this.next = next;
		if (prev == null)
			this.prev = null;
		else
			this.prev = prev;
	}

	/**
	 * Instantiates a new node.
	 *
	 * @param data
	 *            the data
	 * @param next
	 *            the next
	 */
	Node(T data, Node<T> next) {
		this(data, next, null);
	}

	/**
	 * Instantiates a new node.
	 *
	 * @param data
	 *            the data
	 * @param next
	 *            the next
	 */
	Node(T data, DataList<T> next) {
		this(data, next.head, null);
	}

	/**
	 * Instantiates a new node.
	 *
	 * @param prev
	 *            the prev
	 * @param data
	 *            the data
	 */
	Node(Node<T> prev, T data) {
		this(data, null, prev);
	}

	/**
	 * Instantiates a new node.
	 *
	 * @param data
	 *            the data
	 */
	Node(T data) {
		this(data, null, null);
	}

	/**
	 * Instantiates a new node.
	 */
	Node() {
		this(null, null, null);
	}
}