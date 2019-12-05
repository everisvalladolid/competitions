package com.everis.competition.jail;

import java.util.ArrayList;

import com.everis.competition.prisoner.Decision;
import com.everis.competition.prisoner.Prisoner;

public class AndresEscobarCotan extends Prisoner {

	public AndresEscobarCotan(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int decide(ArrayList<Decision> decisions) {
		// TODO Auto-generated method stub
		int myDecision = Decision.CONFESS;
		if(decisions.size()==0) {
			myDecision = Decision.CONFESS;
			
		} else {
			int antonTimesTrue = 0;
			for(Decision pastDecision: decisions) {
				if(pastDecision.getAntonDecision()==Decision.CONFESS) {
					antonTimesTrue +=1;
				} else {
					antonTimesTrue -=1;
				}
			}
			if(antonTimesTrue > 0) {
				myDecision = Decision.BRIBERY;
			} else myDecision = Decision.NEGATION;
			
		}
		return myDecision;
	}

}
