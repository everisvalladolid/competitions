package com.everis.competition.jail;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.reflections.Reflections;

import com.everis.competition.prisoner.IPrisoner;
import com.everis.competition.prisoner.Prisoner;

public class PrisonFactory {
		/*
		 * Prison factory is the class responsible for finding prisoners and creating them as objects for the competition.
		 */
		private final static String REFLECTION_PACKAGE =  "com.everis.competition.jail";
		ArrayList<IPrisoner> prisonerList;
		// There are two pointers in the list, Paco must play with every Antons possible.
		private int  iteratorPaco =0;
		private int iteratorAnton = 1;
		private boolean prisonersForCompetition = true; // There are players for the match.

		public int getNumberOfPrisoners() {
			// Returns the current number of prisoners();
			return this.prisonerList.size(); 
		}
		
		public int getNumberOfRoundsPerPrisoner() {
			return getNumberOfPrisoners()-1;
		}
			
		public PrisonFactory() {
			/*
			 * Creator method,
			 * In this function, the program search for all prisoners in package com.everis.competition.prisionerExample
			 * and inject them into a list.
			 */
			prisonerList = new ArrayList<IPrisoner>();
			Reflections reflections = new Reflections(REFLECTION_PACKAGE);
			 Set<Class<? extends Prisoner>> allPrisoners = 
			     reflections.getSubTypesOf(Prisoner.class);
			 for(Class<? extends Prisoner> prisoner: allPrisoners) { 
				IPrisoner iPrisoner;
				try {
					Constructor<? extends Prisoner> createPrisoner = prisoner.getConstructor(new Class[] {String.class});		
					iPrisoner = (IPrisoner) createPrisoner.newInstance(prisoner.getSimpleName());
					prisonerList.add(iPrisoner);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			 }
			 
			 if(getNumberOfPrisoners()==0) {
				 // Wrong list creation, there are not prisoners for playing.
				 prisonersForCompetition = false;
				 
			 } else {
				 iteratorPaco = 0;
				 iteratorAnton = 1;
				 // Sanity check
			 }
		}
		
		public IPrisoner[] getNextPrisonersCompetition() {
			/*
			 * The goal of this function is to get the new Paco and the new Anton, from the prison collection in order
			 * to access to the competition, 
			 * both of them are returned as an array.
			 */
			IPrisoner paco = prisonerList.get(iteratorPaco);
			IPrisoner anton = prisonerList.get(iteratorAnton);
			this.computeNextPrisonersCompetition();
			return new IPrisoner[]{paco, anton};
		}
		
		private void computeNextPrisonersCompetition() {
			/*
			 * The goal of this function is to compute the indexes for the next two players for the competition
			 */
			int listSize = getNumberOfPrisoners()-1;
			iteratorAnton +=1;
			if(iteratorAnton > listSize) {
				iteratorPaco += 1;
				if(iteratorPaco >= listSize) {
					this.prisonersForCompetition = false; // The competition has ended, no more matches are needed.
				} else iteratorAnton = iteratorPaco+1;
			}
		}
		
		public boolean getArePrisonersNotMatched() {
			/*
			 * This function answers the following question: Are there any players that have not been in the competition yet?
			 */
			return this.prisonersForCompetition;
		}

	
}
