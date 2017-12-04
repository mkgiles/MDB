package structures;

/**
 * The Class Link.
 *
 * @param <A>
 *            the generic type
 * @param <I>
 *            the generic type
 * @param <B>
 *            the generic type
 */
public class Link<A, I, B> {
	
	/** The source. */
	// Links represent the connections between two objects in a pseudo-database.
	private A source;
	
	/** The dest. */
	private B dest;
	
	/** The path. */
	private I path;

	/**
	 * Instantiates a new link.
	 *
	 * @param source
	 *            the source
	 * @param path
	 *            the path
	 * @param dest
	 *            the dest
	 */
	public Link(A source, I path, B dest) {
		this.source = source;
		this.dest = dest;
		this.path = path;
	}

	/**
	 * Source.
	 *
	 * @return the a
	 */
	public A source() {
		return source;
	}

	/**
	 * Dest.
	 *
	 * @return the b
	 */
	public B dest() {
		return dest;
	}

	/**
	 * Path.
	 *
	 * @return the i
	 */
	public I path() {
		return path;
	}

}
