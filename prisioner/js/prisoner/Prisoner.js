// As there are not interfaces in Javascript, I must use the Duck-typing procedure instead.
var Prisoner = function(name){
	this._name = name;
};

Prisoner.prototype.getName = function(){
	return this._name;
};

Prisoner.prototype.decide = function(){
	return -1;
}


module.exports.Prisoner = Prisoner;