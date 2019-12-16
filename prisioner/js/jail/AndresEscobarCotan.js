var Prisoner = require('../prisoner/Prisoner').Prisoner;
var DECISION_OPTIONS = require('../prisoner/Decision').DECISION_OPTIONS;
var Decision = require('../prisoner/Decision').Decision;

var AndresEscobarCotan = function(name) {
    Prisoner.call(this, name); 
}
AndresEscobarCotan.prototype = Object.create(Prisoner.prototype);
Object.defineProperty(AndresEscobarCotan.prototype, 'constructor', {
    value: AndresEscobarCotan,
    enumerable: false,
    writable: true
});

AndresEscobarCotan.prototype.decide = function(decisions){
   
    var myDecision = DECISION_OPTIONS.CONFESS;
    if(decisions.length == 0){
        myDecision = DECISION_OPTIONS.CONFESS;
    } else {
        var antonTimesTrue = 0;
        decisions.forEach(pastDecision => {
            if(pastDecision.getAntonDecision() == DECISION_OPTIONS.CONFESS){
                antonTimesTrue +=1;
            } else {
                antonTimesTrue -=1;
            }
        });
        if(antonTimesTrue > 0){
            myDecision = DECISION_OPTIONS.BRIBERY;
        } else myDecision = DECISION_OPTIONS.NEGATION;
    }
    return myDecision;
}

module.exports.AndresEscobarCotan = AndresEscobarCotan;