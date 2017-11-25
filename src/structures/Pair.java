package structures;

public class Pair<A,B> {
	//Pairs represent objects that are linked directly together.
	private A first;
	private B second;

	public Pair(A first, B second) {
		this.first = first;
		this.second = second;
	}
	
	public A car() {
		return first;
	}

	public B cdr() {
		return second;
	}

}
