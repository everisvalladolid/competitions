package com.everis.competition.jail;

import java.util.ArrayList;

import com.everis.competition.prisoner.Decision;
import com.everis.competition.prisoner.Prisoner;

public class AndresEscobarCotan162096 extends Prisoner {

	public AndresEscobarCotan162096(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int decide(ArrayList<Decision> decisions) {
		// Yo siempre digo la verdad
		return Decision.CONFESS;
	}

}
