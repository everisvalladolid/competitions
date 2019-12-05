package com.everis.competition.prisoner;

import java.util.InputMismatchException;

public class Decision {
	
	
	public static final int NEGATION = 0;
	public static final int CONFESS = 1;
	public static final int BRIBERY = 2;
	
    private int myDecision;
    private int otherPrisonerDecision;
     
    
    public int getPacoDecision() {
        return myDecision;
    }

    public int getAntonDecision() {
        return otherPrisonerDecision;
    }

    public Decision(int one, int theOther) {
    	if((one> BRIBERY) || (one < NEGATION)) {
    		// Sanity check: Consider to add an exception
    		one = NEGATION;
    	}
    	if((theOther> BRIBERY) || (theOther < NEGATION)) {
    		// Sanity check: Consider to add an exception
    		theOther = NEGATION;
    	}
        this.myDecision = one;
        this.otherPrisonerDecision = theOther;
    }
}
