package com.everis.competition.judge;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

class Printer {
	/*
	 * This class prints the score for the global results 
	 */
	private  Map<String, Integer> finalScoring = new HashMap<String, Integer>();
	private  Map<String, Integer> agesScoring = new HashMap<String, Integer>();
	private  int numberOfRounds = 0;
	private  int numberOfMatches = 0;
	
	void printSortedByAges() {
		/*
		 * This function orders the score by ages			 
		 */
		Map<String, Integer> sortByAges = agesScoring
		        .entrySet()
		        .stream()
		        .sorted(comparingByValue())
		        .collect(
		            toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
		                LinkedHashMap::new));

		AtomicReference<Boolean> isWinner = new AtomicReference<>();
		isWinner.set(true);
		System.out.println(Constants.MESSAGE_N_TOTAL_MATCHES+String.valueOf(numberOfMatches));
		System.out.println(Constants.MESSAGE_N_ROUNDS+String.valueOf(numberOfRounds)+" |\n");
		sortByAges.forEach((name, ages)-> {
			int points = finalScoring.get(name);
			printInnerResult(isWinner, name, points ,ages);
		});

	}

	void printSortedByPoints() {
		/*
		 * This function orders the score by points			 
		 */
		Map<String, Integer> sortByPoints = finalScoring
		        .entrySet()
		        .stream()
		        .sorted(comparingByValue(Comparator.reverseOrder()))
		        .collect(
		            toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
		                LinkedHashMap::new));
		
		AtomicReference<Boolean> isWinner = new AtomicReference<>();
		isWinner.set(true);
		System.out.println(Constants.MESSAGE_N_TOTAL_MATCHES+String.valueOf(numberOfMatches)+" |\n");
		System.out.println(Constants.MESSAGE_N_ROUNDS+String.valueOf(numberOfRounds)+" |\n");
		sortByPoints.forEach((name, points) -> {
			int ages = agesScoring.get(name);
			printInnerResult(isWinner, name, points ,ages);
		});
		
		
	}
	private void printInnerResult(AtomicReference<Boolean> isWinner, String name, Integer points,Integer ages) {
		int roundsParticipated = numberOfRounds;
		roundsParticipated =(roundsParticipated == 0) ? Constants.SOLO_ROUND: roundsParticipated; // Sanity check, divisor cannot be zero.
		double averageAges = (ages*1.0)/roundsParticipated;
		String outMessage = name;
		if(isWinner.get()) {
			  outMessage += Constants.MESSAGE_WINNER;
			  isWinner.set(false);
		  } 
		 if(Constants.SHOW_POINTS) {
			 outMessage = outMessage +Constants.MESSAGE_POINTS +String.valueOf(points);
		 }
		 outMessage += Constants.MESSAGE_AGES+String.valueOf(ages); 
		 // Every prisoner has participated in (n-rounds -1) 
		  outMessage +=Constants.MESSAGE_AVERAGE_AGES+String.valueOf(Math.round(averageAges));
		  System.out.println(outMessage);
	}
	
	public Printer(Map<String, Integer> finalScoring, Map<String, Integer> agesScoring, int numberOfRounds, int numberOfMatches) {
		this.finalScoring = finalScoring;
		this.agesScoring = agesScoring;
		this.numberOfRounds = numberOfRounds;
		this.numberOfMatches = numberOfMatches;
	}
	
	

}
