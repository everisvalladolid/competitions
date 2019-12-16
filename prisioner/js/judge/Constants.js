
const CONSTANTS = {
    /* Class to isolate the constants used across the package */ 
	
	/* Message constants */
	MESSAGE_AGES : "\n\t-> Total Ages:",
	MESSAGE_AVERAGE_AGES :  "\n\t-> Average Ages:",
	MESSAGE_N_TOTAL_MATCHES : "|Number of total matches: ",
	MESSAGE_N_ROUNDS : "|Number of rounds :  ",
	MESSAGE_POINTS : "\n\t-> Points: ",
	MESSAGE_WINNER : " (WINNER) ",
	/* Points related */
	WINNER_POINTS : 3,
	LOSER_POINTS : 0,
	TIE_POINTS : 1,
	NUMBER_CONFESSIONS : 100,
	SOLO_ROUND : 1,
	/* Years penalty */
	LUCKY_BOTH_YEARS : 5,
	UNLUCKY_BOTH_YEARS : 7,
	LUCKY_YEARS_CONFESSION : 2,
	BAD_YEARS_CONFESSION : 10,
	/* Options */
	SHOW_POINTS : false // If true, prints points
}

module.exports.CONSTANTS = CONSTANTS;