package fr.ifshare;

import java.util.Objects;

public class Rating {
	private final double rate;
	private final String comment;
	
	public Rating(double rate, String comment) {
		if (rate < 0.f || rate > 5.f) {
			throw new IllegalArgumentException("rate must be between [0.0 ; 5.0]");
		}
		this.rate = rate;
		this.comment = Objects.requireNonNull(comment);
	}
	
	public double getRate() {
		return rate;
	}
	
	@Override
	public String toString() {
		if (rate > 1.f) {
			return rate+ " stars : " +comment;
		}
		return rate+ " star : " +comment;
	}

}
