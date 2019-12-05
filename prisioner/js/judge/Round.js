/**
 * http://usejsdoc.org/
 */

 var Round = function(pacoClassName, antonClassName, scorePrisoner1, scorePrisoner2, id) {
    this._pacoClassName = pacoClassName;
    this._antonClassName = antonClassName;
    this._pacoPuntuation = scorePrisoner1;
    this._antonPuntuation = scorePrisoner2;
    this._id = id;
 };

 Round.prototype.toString = function(){
     var realWinner = (this._pacoPuntuation < this._antonPuntuation)? this._pacoClassName : this._antonClassName;
     var winnerString = "\n\nWinner of the round -> "+realWinner+"\n";
     if(this._pacoPuntuation == this._antonPuntuation) { winnerString = "\n\nTie in this round";}
     return "|Round n#"+ this._id +" |\n\n\tPaco was: "+this._pacoClassName+", his points were "+this._pacoPuntuation + "\n\tAnton was: "+this._antonClassName+ ", his points were "+ this._antonPuntuation+winnerString;
 };
 
 module.exports.Round = Round; 