 function runCompetition(webResults) {	
	var http = require('http');
	http.createServer(function handler(req, res) {
	    res.writeHead(200, {'Content-Type': 'text/plain'});
	    res.end(webResults);
	}).listen(8080, '127.0.0.1');
	console.log('Server running at http://127.0.0.1:8080/');
	
}

var PrisonFactory = require('./jail/PrisonFactory');
var Judge = require('./judge/Judge');
var Scoring = require('./judge/Scoring');
var PACO_POSITION = 0;
var ANTON_POSITION = 1;
var myJudge = new Judge.Judge();
var myScoring = new Scoring.Scoring();
var myPrisonFactory = new PrisonFactory.PrisonFactory(); // This jail must be cleaned
var webResults = ""; // String for passing to the web server.
while(myPrisonFactory.getArePrisonersNotMatched()){
	/*
	* While there are prisoners to be match for a round do.
	* Get next two prisoners (One will be Paco and the other will be Anton).
	* Compute the round (judgmentRound). 
	* Store the round information and print
	* Once there are no more prisoners to play with, print the global results.
	*/
	
	var prisonersToConfess = myPrisonFactory.getNextPrisonersCompetition();
	myJudge.setPaco(prisonersToConfess["paco"]);
	myJudge.setAnton(prisonersToConfess["anton"]);
	var puntuations =myJudge.judgementRun();
	myScoring.addScores(prisonersToConfess["paco"].getName(), prisonersToConfess["anton"].getName(), puntuations[PACO_POSITION],puntuations[ANTON_POSITION]);
	webResults+=myScoring.printRound();
}
webResults+=myScoring.printFinalResults(myPrisonFactory.getNumberOfRoundsPerPrisoner());
runCompetition(webResults);

