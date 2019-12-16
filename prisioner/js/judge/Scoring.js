/**
 * http://usejsdoc.org/
 */
 var Round = require('./Round.js').Round;
 var Printer = require('./Printer.js').Printer;
 var CONSTANTS = require('./Constants.js').CONSTANTS;

 var Scoring = function(){
    this._numberOfMatches=0;
    this._numberOfRoundsPerPrisoner=0;
    this.finalScoring = {};
    this.agesScoring = {};
    this.finalRound = new Array();
    
    this.addScores = function(namePrisoner1, namePrisoner2, scorePrisoner1, scorePrisoner2) {
         this.finalRound.push(new Round(namePrisoner1,namePrisoner2,scorePrisoner1,scorePrisoner2,this.finalRound.length));
         var afterRoundScore1 = CONSTANTS.LOSER_POINTS;
         var afterRoundScore2 = CONSTANTS.LOSER_POINTS;
         if(scorePrisoner1 == scorePrisoner2){
            afterRoundScore1 = CONSTANTS.TIE_POINTS;
            afterRoundScore2 = CONSTANTS.TIE_POINTS;
         } else if (scorePrisoner1 < scorePrisoner2) {
            afterRoundScore1 = CONSTANTS.WINNER_POINTS;
            afterRoundScore2 = CONSTANTS.LOSER_POINTS;
         } else {
            afterRoundScore1 = CONSTANTS.WINNER_POINTS;
            afterRoundScore2 = CONSTANTS.LOSER_POINTS;
         }
        
         if(!(namePrisoner1 in this.finalScoring)){
            this.finalScoring[namePrisoner1] = 0;
         }
         if(!(namePrisoner2 in this.finalScoring)){
            this.finalScoring[namePrisoner2] = 0;
         }

         if(!(namePrisoner1 in this.agesScoring)){
            this.agesScoring[namePrisoner1] = 0;
         }
         if(!(namePrisoner2 in this.agesScoring)){
            this.agesScoring[namePrisoner2] = 0;
         }

         this.finalScoring[namePrisoner1] = this.finalScoring[namePrisoner1]+afterRoundScore1;
         this.agesScoring[namePrisoner1] = this.agesScoring[namePrisoner1]+scorePrisoner1;
         this.finalScoring[namePrisoner2] = this.finalScoring[namePrisoner2]+afterRoundScore2;
         this.agesScoring[namePrisoner2] = this.agesScoring[namePrisoner2]+scorePrisoner2;
         this._numberOfMatches+=1;
      };
  
    this.printRound = function() {
         return this.finalRound[this.finalRound.length-1].toString();
    };

    
    this.printFinalResults = function(i) {
      this._numberOfRoundsPerPrisoner = i
      var printer = new Printer(this.finalScoring, this.agesScoring, this._numberOfRoundsPerPrisoner, this._numberOfMatches)
      var resultString = printer.printSortedByAges();
      return resultString;
    };
};
module.exports.Scoring = Scoring;