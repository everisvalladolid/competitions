/**
 * http://usejsdoc.org/
 */
 var Round = require('./Round.js').Round;
 var Scoring = function(){
    this._WINNER_POINTS = 3;
    this._LOSER_POINTS = 0;
    this._TIE_POINTS = 1;
    this._NUMBER_CONFESSIONS = 100;
    this._numberOfRounds=0;
    this.finalScoring = {};
    this.agesScoring = {};
    this.finalRound = new Array();
    this.addScores = function(namePrisoner1, namePrisoner2, scorePrisoner1, scorePrisoner2) {
         this.finalRound.push(new Round(namePrisoner1,namePrisoner2,scorePrisoner1,scorePrisoner2,this.finalRound.length));
         var afterRoundScore1 = this._LOSER_POINTS;
         var afterRoundScore2 = this._LOSER_POINTS;
         if(scorePrisoner1 == scorePrisoner2){
            afterRoundScore1 = this._TIE_POINTS;
            afterRoundScore2 = this._TIE_POINTS;
         } else if (scorePrisoner1 < scorePrisoner2) {
            afterRoundScore1 = this._WINNER_POINTS;
            afterRoundScore2 = this._LOSER_POINTS;
         } else {
            afterRoundScore1 = this._WINNER_POINTS;
            afterRoundScore2 = this._LOSER_POINTS;
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
         this._numberOfRounds+=1;
      };
      

    

    this.printRound = function() {
         return this.finalRound[this.finalRound.length-1].toString();
    };

    
    this.printFinalResults = function() {
      	/*
			 * This function orders the score by puntuation
			 * 
			 */
         var resultString = "";
         resultString += "|Number of rounds "+this._numberOfRounds+" |\n";
         // TODO: Refactor the format conversion after sort in another function 
         var unsortedArray = [];
         for(var key in this.agesScoring){
            unsortedArray.push({name:key, value: this.agesScoring[key]});
         }

        unsortedArray = unsortedArray.sort(function(prisoner1,prisoner2) {
            return (prisoner1.value > prisoner2.value) ? 1 : ((prisoner2.value > prisoner1.value) ? -1 : 0)
        });
        
        
        var isWinner = true;
        var outMessage="";
        for(var i in unsortedArray){
           var name = unsortedArray[i].name;
           var ages = unsortedArray[i].value;
           outMessage +=  name+ "\n\t-> Points: "+this.finalScoring[name];
           outMessage+= "\n\t-> Total Ages:"+ages;
            // Every prisoner has participated in (n-rounds -1) 
            outMessage += "\n\t-> Average Ages:"+ages/((this._numberOfRounds-1)*this._NUMBER_CONFESSIONS);
            if(isWinner==true){
               outMessage+= " (WINNER) ";
               isWinner = false;
            }
            outMessage+="\n";
        };
        
        resultString+=outMessage;
        

        return resultString;

    };
};
module.exports.Scoring = Scoring;