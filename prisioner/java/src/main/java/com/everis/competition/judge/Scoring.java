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
	
	
	
	private int numberOfMatches = 0;
	private int numberOfRoundsPerPrisoner = 0;
	
	public void addScores(String namePrisoner1, String namePrisoner2, int scorePrisoner1, int scorePrisoner2) {
		/*
		 * This function adds after every match the scores for the players
		 * Also stores the information on the current round
		 */
		finalRound.add(new Round(namePrisoner1, namePrisoner2,scorePrisoner1, scorePrisoner2, finalRound.size()));
		int afterRoundScore1 = Constants.LOSER_POINTS;
		int afterRoundScore2 = Constants.LOSER_POINTS;
		if(scorePrisoner1==scorePrisoner2) {
			afterRoundScore1 = Constants.TIE_POINTS;
			afterRoundScore2 = Constants.TIE_POINTS;
		} else if(scorePrisoner1 < scorePrisoner2) {
			afterRoundScore1= Constants.WINNER_POINTS;
			afterRoundScore2 = Constants.LOSER_POINTS;
		} else {
			afterRoundScore2= Constants.WINNER_POINTS;
			afterRoundScore1 = Constants.LOSER_POINTS;
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
        numberOfMatches +=1;
	}
	
	public void printRound() {
		int roundIndex = finalRound.size()-1;
		System.out.println(this.finalRound.get(roundIndex).toString());
	}
			
	public void printFinalResults(int i) {
		this.numberOfRoundsPerPrisoner = i;
		Printer printer = new Printer(finalScoring, agesScoring, numberOfRoundsPerPrisoner, numberOfMatches);
		if(Constants.PRINT_BY_POINTS) {
			printer.printSortedByPoints();
		} else if(Constants.PRINT_BY_AGES) {
			printer.printSortedByAges();
		}
	}

	public Scoring() {
		
	}

}


