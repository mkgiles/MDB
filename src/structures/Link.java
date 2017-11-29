package structures;

public class Link<A,I,B> {
	//Links represent the connections between two objects in a pseudo-database.
	private A source;
	private B dest;
	private I path;

	public Link(A source, I path, B dest) {
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
