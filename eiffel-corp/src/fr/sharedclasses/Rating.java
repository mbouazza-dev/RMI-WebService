package fr.sharedclasses;

import java.io.Serializable;
import java.util.Objects;

public class Rating implements Serializable {
	private double rate;
	private String comment;
	
	public Rating() { }
	
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
	
	public String getComment() {
		return comment;
	}
	
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		if (rate > 1.f) {
			return rate+ " stars : " +comment;
		}
		return rate+ " star : " +comment;
	}

}
