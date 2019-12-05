package com.everis.competition.jail;

import java.util.ArrayList;

import com.everis.competition.prisoner.Decision;
import com.everis.competition.prisoner.Prisoner;

    public class PrisonerAlwaysNegate extends Prisoner {

        public PrisonerAlwaysNegate(String name) {
            super(name);
        }

        @Override
        public int decide(ArrayList<Decision> decisions) {
            return Decision.NEGATION;
        }
    }

