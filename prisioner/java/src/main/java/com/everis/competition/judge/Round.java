package com.everis.competition.judge;

public class Round {
	/*
	 * This POJO class stores the basic information of a Round
	 */
	String pacoClassName;
	String antonClassName;
	int pacoPuntuation;
	int antonPuntuation;
	int id;
	public Round(String pacoClassName, String antonClassName, int scorePrisoner1, int scorePrisoner2, int id) {
		super();
		this.pacoClassName = pacoClassName;
		this.antonClassName = antonClassName;
		this.pacoPuntuation = scorePrisoner1;
		this.antonPuntuation = scorePrisoner2;
		this.id = id;
	}
	
	public String toString() { 
        String realWinner = (this.pacoPuntuation < this.antonPuntuation)? this.pacoClassName : this.antonClassName;
        String winnerString = "\n\nWinner of the round -> "+realWinner+"\n";
        if(this.pacoPuntuation == this.antonPuntuation) { winnerString = "\n\nTie in this round"; }
		return "|Round n#"+String.valueOf(this.id) +" |\n\n\tPaco was: "+this.pacoClassName+", his points were "+String.valueOf(this.pacoPuntuation) + "\n\tAnton was: "+this.antonClassName+ ", his points were "+ String.valueOf(this.antonPuntuation)+winnerString;
	}

}
