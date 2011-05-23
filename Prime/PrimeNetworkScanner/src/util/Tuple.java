package util;


public class Tuple<G, H> {
	private G first;
	private H second;
	
	/**
	 * Defines a simple return-type that can hold two values
	 * @param first
	 * @param second
	 */
	public Tuple(G first, H second) {
		this.first = first;
		this.second = second;
	}
	
	public G getFirst() {
		return first;
	}
	
	public H getSecond() {
		return second;
	}
}
