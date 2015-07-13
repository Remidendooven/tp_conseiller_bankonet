package com.bankonet;

public class CompteNonTrouveException extends Exception{
	/* Constructeurs */
	public CompteNonTrouveException() {
		super();
	}
	
	public CompteNonTrouveException(String msg) {
		super(msg);
	}
}
