package com.everis.competition.judge;

class Constants {
	/* Class to isolate the constants used across the package */ 
	
	/* Message constants */
	static final String MESSAGE_AGES = "\n\t-> Total Ages:";
	static final String MESSAGE_AVERAGE_AGES =  "\n\t-> Average Ages:";
	static final String MESSAGE_N_TOTAL_MATCHES = "|Number of total matches: ";
	static final String MESSAGE_N_ROUNDS = "|Number of rounds :  ";
	static final String MESSAGE_POINTS = "\n\t-> Points: ";
	static final String MESSAGE_WINNER = " (WINNER) ";
	
	/* Points related */
	static final int WINNER_POINTS = 3;
	static final int LOSER_POINTS = 0;
	static final int TIE_POINTS = 1;

	static final int NUMBER_CONFESSIONS = 100;
	static final int SOLO_ROUND = 1;
	
	/* Years penalty */
	static final int LUCKY_BOTH_YEARS = 5;
	static final int UNLUCKY_BOTH_YEARS = 7;
	static final int LUCKY_YEARS_CONFESSION = 2;
	static final int BAD_YEARS_CONFESSION = 10;
	
	/* Options */
	static boolean PRINT_BY_POINTS = false; // If false print by ages instead
	static boolean PRINT_BY_AGES = !PRINT_BY_POINTS?true:false; // If false print by points instead
	static boolean SHOW_POINTS = false; // If true, prints points
	
	
	public Constants() {
		// TODO Auto-generated constructor stub
	}

}
