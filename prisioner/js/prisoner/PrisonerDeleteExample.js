/**
 * http://usejsdoc.org/
 */
var Prisoner = require('./Prisoner.js').Prisoner;

PrisonerDeleteExample.prototype = Prisoner;
PrisonerDeleteExample.parent = Prisoner;
PrisonerDeleteExample.prototype.constructor = PrisonerDeleteExample;	
function PrisonerDeleteExample(name){
	this._name = name;
	
};

PrisonerDeleteExample.prototype.decide = function(){
	return "3";
}
module.exports.PrisonerDeleteExample = PrisonerDeleteExample;
