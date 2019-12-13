import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.everis.competition.jail.PrisonFactory;
import com.everis.competition.judge.Judge;
import com.everis.competition.judge.Scoring;
import com.everis.competition.prisoner.IPrisoner;


public class RunCompetition {
	  static Judge myJudge = new Judge();
	  static PrisonFactory myPrisonFactory = new PrisonFactory();
	  static Scoring myScoring = new Scoring();
	  private static final int PACO_POSITION = 0;
	  private static final int ANTON_POSITION = 1;
	  public static void main(String[] args) {
		  /*
		   * While there are prisoners to be match for a round do.
		   * Get next two prisoners (One will be Paco and the other will be Anton).
		   * Compute the round (judgmentRound). 
		   * Store the round information and print
		   * Once there are no more prisoners to play with, print the global results.
		   */
		  while(myPrisonFactory.getArePrisonersNotMatched()) {
		   	IPrisoner[] prisonersToConfess = myPrisonFactory.getNextPrisonersCompetition();
			myJudge.setPaco(prisonersToConfess[PACO_POSITION]);
			myJudge.setAnton(prisonersToConfess[ANTON_POSITION]);
		    int[] puntuations = myJudge.judgmentRun();		    
	        myScoring.addScores(prisonersToConfess[PACO_POSITION].getName(), prisonersToConfess[ANTON_POSITION].getName(), puntuations[PACO_POSITION], puntuations[ANTON_POSITION]);
	        myScoring.printRound();
		  }
		  myScoring.printFinalResults(myPrisonFactory.getNumberOfRoundsPerPrisoner());
	    }
}
