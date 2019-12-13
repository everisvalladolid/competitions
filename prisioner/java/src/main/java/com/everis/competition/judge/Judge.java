package com.everis.competition.judge;

import java.util.ArrayList;

import com.everis.competition.prisoner.Decision;
import com.everis.competition.prisoner.IPrisoner;

public class Judge {
	/*
	 * This class made a confession for every round
	 * storing the decisions made for every player into a list. 
	 */	
	IPrisoner paco;
	IPrisoner anton;
	ArrayList<Decision> decisionsPaco;
	ArrayList<Decision> decisionsAnton;
	public Judge() {
		decisionsPaco = new ArrayList<Decision>();
		decisionsAnton = new ArrayList<Decision>();
	}
	
	public int[] judgmentRun() {
		/*
		 * Executes the procedure for every round
		 * 1- Clears the previous decisions lists if there was one.
		 * 2- Make both prisoners confess N times
		 * 3- Compute with the decisions lists the points of both prisoners.
		 */
		decisionsPaco.clear();
		decisionsAnton.clear();
		confess();
		int[] puntuations = computePoints(); 
        return puntuations;
	}
	public void setPaco(IPrisoner paco) {
		this.paco = paco;
	}
	
	public void setAnton(IPrisoner anton) {
		this.anton = anton;
	}

    private void confess() {
    	/*
    	 * Confess just execute the decide method from both prisoners
    	 * and stores the result in a list.
    	 */
        int pacoDecision, antonDecision;
        for (int i = 0; i < Constants.NUMBER_CONFESSIONS; i++) {
            pacoDecision = paco.decide(decisionsPaco);
            antonDecision = anton.decide(decisionsAnton);
            decisionsPaco.add(new Decision(pacoDecision, antonDecision));
            // Disguises Anton as Paco by reversing the order, so he knows which decision has made
            decisionsAnton.add(new Decision(antonDecision, pacoDecision));
        }
        
    }

    private int[] computePoints() {
    	/*
    	 * Returns both points 
    	 * after consulting the list of the decisions after the confession.
    	 */
    	int accPaco = 0;
    	int accAnton = 0;
    	// Only iterates through decisionsPaco
    	// Decisions are the same for both lists excepts order is reversed.
    	for(Decision decision: decisionsPaco) {
    		int decisionPaco = decision.getPacoDecision();
    		int decisionAnton = decision.getAntonDecision();
    		// If both decisions were the same
    		if(decisionPaco == decisionAnton) {
    			if(decisionPaco==Decision.CONFESS  || decisionPaco==Decision.BRIBERY) {
    				accPaco += Constants.UNLUCKY_BOTH_YEARS;
    				accAnton += Constants.UNLUCKY_BOTH_YEARS;
    			} else {
    				// Both decide to negate and dont confess
    				accPaco += Constants.LUCKY_BOTH_YEARS;
    				accAnton += Constants.LUCKY_BOTH_YEARS;
    			}
    			
    		} else {
    			// Decisions differs
    			boolean luckyForPaco = isLuckyForPaco(decisionPaco, decisionAnton);
    			if(luckyForPaco) {
    				accPaco += Constants.LUCKY_YEARS_CONFESSION;
    				accAnton += Constants.BAD_YEARS_CONFESSION;
    			} else {
    				// Anton was lucky this time
    				accAnton += Constants.LUCKY_YEARS_CONFESSION;
    				accPaco += Constants.BAD_YEARS_CONFESSION;
    			}
    			
    		}
 		
    	}
    	
        return new int[] {accPaco, accAnton};
    }
    
    private boolean isLuckyForPaco(int decisionPaco, int decisionAnton) {
    	boolean luckyPaco = false;
    	boolean confessAndNegation = ((decisionPaco==Decision.CONFESS)&&(decisionAnton==Decision.NEGATION)); 
    	boolean briberyAndConfess =((decisionPaco==Decision.BRIBERY)&&(decisionAnton==Decision.CONFESS)); 
    	boolean negationAndBribery = ((decisionPaco==Decision.NEGATION)&&(decisionAnton==Decision.BRIBERY));	
    	if(confessAndNegation || briberyAndConfess || negationAndBribery) luckyPaco = true;
    	return luckyPaco;
    }

}
