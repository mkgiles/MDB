package structures;

/**
 * The Class Pair.
 *
 * @param <A>
 *            the generic type
 * @param <B>
 *            the generic type
 */
public class Pair<A, B> {
	
	/** The first. */
	// Pairs represent objects that are linked directly together.
	private A first;
	
	/** The second. */
	private B second;

	/**
	 * Instantiates a new pair.
	 *
	 * @param first
	 *            the first item
	 * @param second
	 *            the second item
	 */
	public Pair(A first, B second) {
		this.first = first;
		this.second = second;
	}

	/**
	 * Car of pair.
	 *
	 * @return the first element
	 */
	public A car() {
		return first;
	}

	/**
	 * Cdr of pair.
	 *
	 * @return the b
	 */
	public B cdr() {
		return second;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return first + (second == null ? null : " (" + second + ")");
	}

}
