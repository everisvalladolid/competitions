package com.everis.competition.judge;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Scoring {
	/*
	 * This class stores the score for every match, and for the global results.
	 * It also prints information to the standard output. 
	 */
	
	private  Map<String, Integer> finalScoring = new HashMap<String, Integer>();
	private  Map<String, Integer> agesScoring = new HashMap<String, Integer>();
	private  ArrayList<Round> finalRound = new ArrayList<Round>();
	private final static int WINNER_POINTS = 3;
	private final static int LOSER_POINTS = 0;
	private final static int TIE_POINTS = 1;
	private static final int NUMBER_CONFESSIONS = 100;
	private int numberOfRounds = 0;
	
	public void addScores(String namePrisoner1, String namePrisoner2, int scorePrisoner1, int scorePrisoner2) {
		/*
		 * This function adds after every match the scores for the players
		 * Also stores the information on the current round
		 */
		finalRound.add(new Round(namePrisoner1, namePrisoner2,scorePrisoner1, scorePrisoner2, finalRound.size()));
		int afterRoundScore1 = LOSER_POINTS;
		int afterRoundScore2 = LOSER_POINTS;
		if(scorePrisoner1==scorePrisoner2) {
			afterRoundScore1 = TIE_POINTS;
			afterRoundScore2 = TIE_POINTS;
		} else if(scorePrisoner1 < scorePrisoner2) {
			afterRoundScore1= WINNER_POINTS;
			afterRoundScore2 = LOSER_POINTS;
		} else {
			afterRoundScore2= WINNER_POINTS;
			afterRoundScore1 = LOSER_POINTS;
		}
		
        if(!finalScoring.containsKey(namePrisoner1)) {
        	finalScoring.put(namePrisoner1, 0);
        }
        if(!finalScoring.containsKey(namePrisoner2)) {
        	finalScoring.put(namePrisoner2, 0);
        }
        if(!agesScoring.containsKey(namePrisoner1)) {
        	agesScoring.put(namePrisoner1, 0);
        }
        if(!agesScoring.containsKey(namePrisoner2)) {
        	agesScoring.put(namePrisoner2, 0);
        }
        finalScoring.replace(namePrisoner1,  finalScoring.get(namePrisoner1)+afterRoundScore1);
        agesScoring.replace(namePrisoner1,  agesScoring.get(namePrisoner1)+scorePrisoner1);
        finalScoring.replace(namePrisoner2, finalScoring.get(namePrisoner2) +afterRoundScore2);
        agesScoring.replace(namePrisoner2, agesScoring.get(namePrisoner2) +scorePrisoner2);
        numberOfRounds +=1;
	}
	
	public void printRound() {
		System.out.println(this.finalRound.get(finalRound.size()-1).toString());
	}
	public void printFinalResults() {
			/*
			 * This function orders the score by puntuation
			 * 
			 */
		  
		  Map<String, Integer> sortedByAges = agesScoring
			        .entrySet()
			        .stream()
			        .sorted(comparingByValue())
			        .collect(
			            toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
			                LinkedHashMap::new));

		  AtomicReference<Boolean> isWinner = new AtomicReference<>();
		  isWinner.set(true);
		  System.out.println("|Number of rounds :  "+String.valueOf(numberOfRounds)+" |\n");
		  sortedByAges.forEach((name, ages)-> {
			  String outMessage = name+ "\n\t-> Points: "+String.valueOf(finalScoring.get(name));
			  outMessage += "\n\t-> Total Ages:"+String.valueOf(ages);
			  // Every prisoner has participated in (n-rounds -1) 
			  outMessage += "\n\t-> Average Ages:"+String.valueOf(ages/((numberOfRounds-1)*NUMBER_CONFESSIONS));
			  if(isWinner.get()) {
				  outMessage += " (WINNER) ";
				  isWinner.set(false);
			  } 
			  System.out.println(outMessage);
		  });
	}
	public Scoring() {
		
	}

}
