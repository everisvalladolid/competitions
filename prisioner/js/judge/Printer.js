var CONSTANTS = require('./Constants.js').CONSTANTS;

var Printer = function(finalScoring, agesScoring, numberOfRounds, numberOfMatches){
    this._finalScoring = finalScoring;
    this._agesScoring = agesScoring;
    this._numberOfRounds = numberOfRounds;
    this._numberOfMatches = numberOfMatches;     
    this.printSortedByAges = function(){
        var resultString = "";
        resultString +=CONSTANTS.MESSAGE_N_TOTAL_MATCHES+this._numberOfMatches+" |\n";
        resultString +=CONSTANTS.MESSAGE_N_ROUNDS+this._numberOfRounds+" |\n";
        // TODO: Refactor the format conversion after sort in another function 
        var unsortedArray = [];
        for(var key in this._agesScoring){
           unsortedArray.push({name:key, value: this._agesScoring[key]});
        }

       unsortedArray = unsortedArray.sort(function(prisoner1,prisoner2) {
           return (prisoner1.value > prisoner2.value) ? 1 : ((prisoner2.value > prisoner1.value) ? -1 : 0)
       });
       
       
       var isWinner = true;
       var outMessage="";
       for(var i in unsortedArray){
          var name = unsortedArray[i].name;
          var ages = unsortedArray[i].value;
          outMessage += this._printInnerResult(isWinner, name, ages);
          isWinner = false; // Only the first isWinner
       };
       resultString+=outMessage;
       return resultString;
    };

    this._printInnerResult = function(isWinner, name, ages){
        var outMessage = "";
        var roundsParticipated = this._numberOfRounds;
        roundsParticipated = (roundsParticipated == 0) ? CONSTANTS.SOLO_ROUND : roundsParticipated; // Sanity check
        var average_ages = Math.round((ages*1.0)/roundsParticipated);
        outMessage +=  name;
        if(isWinner==true){
         outMessage+= CONSTANTS.MESSAGE_WINNER;
         isWinner = false;
        }
        if(CONSTANTS.SHOW_POINTS)
        {
            outMessage+=CONSTANTS.MESSAGE_POINTS+this._finalScoring[name];
        }
        outMessage+= CONSTANTS.MESSAGE_AGES+ages;
        // Every prisoner has participated in (n-rounds -1) 
        outMessage += CONSTANTS.MESSAGE_AVERAGE_AGES+average_ages;
        outMessage+="\n";
        return outMessage;
    }
};



module.exports.Printer = Printer;