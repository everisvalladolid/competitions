var Prisoner = require('../prisoner/Prisoner').Prisoner;
var DECISION_OPTIONS = require('../prisoner/Decision').DECISION_OPTIONS;
var Decision = require('../prisoner/Decision').Decision;

var PrisonerAlwaysNegate = function(name) {
    Prisoner.call(this, name)

}
PrisonerAlwaysNegate.prototype = Object.create(Prisoner.prototype);
Object.defineProperty(PrisonerAlwaysNegate.prototype, 'constructor', {
    value: PrisonerAlwaysNegate,
    enumerable: false,
    writable: true
});
PrisonerAlwaysNegate.prototype.decide = function(decisions){
    return DECISION_OPTIONS.NEGATION;
}

module.exports.PrisonerAlwaysNegate = PrisonerAlwaysNegate;