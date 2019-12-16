var Prisoner = require('../prisoner/Prisoner').Prisoner;
var DECISION_OPTIONS = require('../prisoner/Decision').DECISION_OPTIONS;
var Decision = require('../prisoner/Decision').Decision;

var PrisonerAlwaysConfess = function(name) {
    Prisoner.call(this, name); 
}
PrisonerAlwaysConfess.prototype = Object.create(Prisoner.prototype);
Object.defineProperty(PrisonerAlwaysConfess.prototype, 'constructor', {
    value: PrisonerAlwaysConfess,
    enumerable: false,
    writable: true
});
PrisonerAlwaysConfess.prototype.decide = function(decisions){
    return DECISION_OPTIONS.CONFESS;
}

module.exports.PrisonerAlwaysConfess = PrisonerAlwaysConfess;