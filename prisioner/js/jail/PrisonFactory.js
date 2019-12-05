  /*
    * Prison factory is the class responsible for finding prisoners and creating them as objects for the competition.
    */
var path = require('path');
var importModules = require('import-modules');
var Prisoner = require('../prisoner/Prisoner').Prisoner;
var PrisonFactory = function(){
     	/*
        * Creator method,
        * In this function, the program search for all prisoners in jail
        * and inject them into a list.
        */

    // There are two pointers in the list, Paco must play with every Antons possible.
    this.prisonersList = new Array();
    this.iteratorPaco = 0;
    this.iteratorAnton = 0;
    this.prisonersForCompetition = true;  // There are players for the match.
    this.directoryPath =path.join(__dirname,''); 
    this.prisonersImported = importModules(this.directoryPath);
    Object.keys(this.prisonersImported).forEach(possiblePrisonerNM => {
        var possiblePrisoner = require('./'+possiblePrisonerNM)[possiblePrisonerNM];
        if(typeof possiblePrisoner.prototype.decide === 'function') {
            this.prisonersList.push(new possiblePrisoner(possiblePrisoner.name));
        }
    });
    if(this.prisonersList.length==0) {
        // Wrong list creation, there are not prisoners for playing.
        this.prisonersForCompetition = false;
        console.log("Not suitable prisoners for the competition were found");
    } else {
        this.iteratorPaco = 0;
        this.iteratorAnton = 1;
        // Sanity check
    }

   this.getNextPrisonersCompetition = function() {
        	/*
			 * The goal of this function is to get the new Paco and the new Anton, from the prison collection in order
			 * to access to the competition, 
			 * both of them are returned as an array.
			 */
            var paco = this.prisonersList[this.iteratorPaco];
            var anton =this.prisonersList[this.iteratorAnton];
            this._computeNextPrisonersCompetition();
            return {paco, anton};
    };

     this._computeNextPrisonersCompetition = function(){
        /*
			 * The goal of this function is to compute the indexes for the next two players for the competition
			 */
        var listSize = this.prisonersList.length -1;
        this.iteratorAnton += 1;
        if(this.iteratorAnton > listSize){
            this.iteratorPaco+=1;
            if(this.iteratorPaco >= listSize){
                this.prisonersForCompetition = false; // The competition has ended, no more matches are needed.
            } else {
                this.iteratorAnton = this.iteratorPaco +1;
            }
        }
        
    };

     this.getArePrisonersNotMatched= function(){
        /*
        * This function answers the following question: Are there any players that have not been in the competition yet?
        */
        return this.prisonersForCompetition;
    };
};    
module.exports.PrisonFactory = PrisonFactory;