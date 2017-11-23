package structures;

public class Link<A,I,B> {
	private A source;
	private B dest;
	private I path;

	public Link(A source, B dest, I path) {
		this.source = source;
		this.dest = dest;
		this.path = path;
	}
	
	public A source() {
		return source;
	}
	public B dest() {
		return dest;
	}
	public I path() {
		return path;
	}

}
