package com.everis.competition.jail;

import java.util.ArrayList;

import com.everis.competition.prisoner.Decision;
import com.everis.competition.prisoner.Prisoner;

    public class PrisonerAlwaysFalse extends Prisoner {

        public PrisonerAlwaysFalse(String name) {
            super(name);
        }

        @Override
        public boolean decide(ArrayList<Decision> decisions) {
            return false;
        }
    }

