/**
 * http://usejsdoc.org/
 */
var DECISION_OPTIONS = {
		NEGATION: 0,
		CONFESS: 1,
		BRAVERY:2
}


var Decision = function(one, theOther){
	if((one>DECISION_OPTIONS.BRAVERY || one<DECISION_OPTIONS.NEGATION)){
		one = DECISION_OPTIONS.NEGATION;
	}
	if((theOther>DECISION_OPTIONS.BRAVERY || theOther<DECISION_OPTIONS.NEGATION)){
		theOther = DECISION_OPTIONS.NEGATION;
	}
	this._myDecision = one;
	this._otherPrisonerDecision = theOther;
}

Decision.prototype.getPacoDecision = function(){
	return this._myDecision;
}

Decision.prototype.getAntonDecision = function(){
	return this._otherPrisonerDecision;
}

module.exports.DECISION_OPTIONS = DECISION_OPTIONS;
module.exports.Decision = Decision;