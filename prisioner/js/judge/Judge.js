/**
 * http://usejsdoc.org/
 */
var Decision = require('../prisoner/Decision.js').Decision
var DECISION_OPTIONS = require('../prisoner/Decision.js').DECISION_OPTIONS
var CONSTANTS = require('./Constants.js').CONSTANTS

var Judge = function() {
	/*
	 * This class made a confession for every round
	 * storing the decisions made for every player into a list. 
	 */	
	this._paco;
	this._anton;
	this._decisionsPaco = new Array();
	this._decisionsAnton = new Array();
	
	this.setPaco = function(paco){
		this._paco = paco;
	}
	
	this.setAnton = function(anton){
		this._anton = anton;
	}
	this.judgementRun = function() {
		/*
		 * Executes the procedure for every round
		 * 1- Clears the previous decisions lists if there was one.
		 * 2- Make both prisoners confess N times
		 * 3- Compute with the decisions lists the points of both prisoners.
		 */
		this._decisionsPaco.length = 0;
		this._decisionsAnton.length = 0;
		this.confess();
		var puntuations = this.computePoints();
		return puntuations;
	};
	
	this.confess = function() {
		/*
    	 * Confess just execute the decide method from both prisoners
    	 * and stores the result in a list.
    	 */
		var pacoDecision, antonDecision;
		for(var i = 0; i < CONSTANTS.NUMBER_CONFESSIONS; i++){
			pacoDecision = this._paco.decide(this._decisionsPaco);
			antonDecision = this._anton.decide(this._decisionsAnton);
			this._decisionsPaco.push(new Decision(pacoDecision, antonDecision));
			// Disguises Anton as Paco by reversing the order, so Anton he knows which decision has made.
			this._decisionsAnton.push(new Decision(antonDecision, pacoDecision));
		}
		
	}

	this.computePoints = function() {
		/*
    	 * Returns both points 
    	 * after consulting the list of the decisions after the confession.
    	 */
		var accPaco = 0;
		var accAnton = 0;
		// Only iterates through decisionsPaco
    	// Decisions are the same for both lists excepts order is reversed.
		this._decisionsPaco.forEach(decision => {
			var numericDecisionPaco =decision.getPacoDecision();
			var numericDecisionAnton =decision.getAntonDecision();
			// If both decisions were the same
			if(numericDecisionPaco == numericDecisionAnton){
				if(numericDecisionPaco==DECISION_OPTIONS.CONFESS || numericDecisionPaco==DECISION_OPTIONS.BRIBERY) {
					accPaco += CONSTANTS.UNLUCKY_BOTH_YEARS;
					accAnton += CONSTANTS.UNLUCKY_BOTH_YEARS;
				} else {
					accPaco += CONSTANTS.LUCKY_BOTH_YEARS;
					accAnton += CONSTANTS.LUCKY_BOTH_YEARS;
				}
			} else {
				// Decisions differs
				var luckyForPaco = this._isLuckyForPaco(numericDecisionPaco, numericDecisionAnton);
				if(luckyForPaco==true){
					accPaco += CONSTANTS.LUCKY_YEARS_CONFESSION;
					accAnton += CONSTANTS.BAD_YEARS_CONFESSION;
					
				} else {
					// Anton Was lucky this time.
					accAnton += CONSTANTS.LUCKY_YEARS_CONFESSION;
					accPaco += CONSTANTS.BAD_YEARS_CONFESSION;
				}

			}
		});
		
		return [accPaco, accAnton];
	}

	this._isLuckyForPaco = function(numericDecisionPaco, numericDecisionAnton) {
		var luckyForPaco = false;
		var confessAndNegation = ((numericDecisionPaco== DECISION_OPTIONS.CONFESS)&&(numericDecisionAnton==DECISION_OPTIONS.NEGATION));
		var briberyAndConfess = ((numericDecisionPaco== DECISION_OPTIONS.BRIBERY)&&(numericDecisionAnton==DECISION_OPTIONS.CONFESS));
		var negationAndBribery = ((numericDecisionPaco== DECISION_OPTIONS.NEGATION)&&(numericDecisionAnton==DECISION_OPTIONS.BRIBERY));
		if(confessAndNegation|| briberyAndConfess || negationAndBribery) luckyForPaco=true;
		return luckyForPaco;
	}
};	
module.exports.Judge = Judge;