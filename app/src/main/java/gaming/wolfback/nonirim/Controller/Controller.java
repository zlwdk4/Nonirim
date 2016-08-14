package gaming.wolfback.nonirim.Controller;

import android.util.Log;

import gaming.wolfback.nonirim.Utility.Card;

/**
 * Created by Jarren on 3/19/2016.
 */
public class Controller {
    public void playCard(int cardNum){
        facade.playCardIntoLabAndRemoveCardFromHand(cardNum);
        if(didScore()){
            String colorOfDoorUpdated = facade.updateDoorCount();
            facade.removeDoorFromDrawPile(colorOfDoorUpdated);
        }
        facade.drawFromDrawPileIntoHand();
    }
    public boolean wasNightmareDrawn(){
        if(facade.wasNightmareDrawn()){
            facade.incrementNightmareCount();
            facade.removeNightmareFromDrawPile();
            return true;
        }
        else return false;
    }

    //This method handles the case when a nightmare is drawn (as far as the internal model goes)
    //This method gets passed the option that the user selects.
    //It then calls the facade to do the appropriate action
    //TO DO: Add in error handling for if an option is selected that doesn't exist
    public void takeNightmareAction(int optionSelected){
        if(optionSelected == 0){
            //discardKey();
        }
        else if (optionSelected == 1){
            //put door in limbo
        }
        else if (optionSelected == 2){
            //discard next five
            facade.discardNextFiveFromDrawPile();
            facade.getRidOfNightmareInHand();
            facade.drawFromDrawPileIntoHand();
        }
        else if (optionSelected == 3){
            //discard hand
            for (int i = 0; i < 5; i++){
                facade.discardCardFromHand(i);
            }
            facade.drawFiveTimesIntoHand();
        }
    }

    public void discardCard(int cardNum){
        facade.discardCardFromHand(cardNum);
    }
    public void drawCard () {
        facade.drawFromDrawPileIntoHand();
    }
    public boolean isValidPlay(int indexOfCardInHand){
        int labSize = facade.getLabSize();
        String curLabType = facade.getLabType(labSize-1);
        String curHandType = facade.getCardTypeFromHand(indexOfCardInHand);
        return (rules.isValidPlayRegardingType(curLabType, curHandType));
    }
    //calls function to see how many cards should be given to rules
    //calls another function to get that many card colors from the labyrinth
    //then passes the card colors and number of cards to rules to see if it's the player scored
    public boolean didScore(){
        int numCards = numCardsToGiveToRules();
        String [] cardColors;
        cardColors = getArrayOfCardColorsFromLab(numCards);
        return rules.didScore(cardColors, numCards);
    }
    //a function to get the number of cards for rules. This will ask facade for the size of the lab
    //depending on the size of the lab, that's how many cards we need to give to rules
    private int numCardsToGiveToRules(){
        int labSize = facade.getLabSize();
        if (labSize < 3){
            return 0;
        }
        else if (labSize==3){
            return 3;
        }
        else if (labSize >=6){
            return 6;
        }
        else return 4;
    }
    //A function to get an array of strings from the lab.
    //These strings will be the colors of the cards in the lab starting with the most recently played card in the lab
    //It will grab as many colors as the parameter it is passed
    private String[] getArrayOfCardColorsFromLab(int numCards){
        int labSize = facade.getLabSize();
        String[] colorOfCardsArray = new String[numCards];
        for (int i = 0; i < numCards; ++i){
            colorOfCardsArray[i] = facade.getLabColor(labSize - 1 - i);
        }
        return colorOfCardsArray;
    }

    public String[] getTopFiveDiscardColorAndTypeArray() {
        String[] toReturn = new String [5];
        for (int i = 0; i < 5; ++i){
            toReturn[i] = facade.getDiscardColorAndType(i);
        }
        return toReturn;
    }

    public String[] getTopFiveDrawPileColorAndTypeArray() {
        String[] toReturn = new String [5];
        for (int i = 0; i < 5; ++i) {
            toReturn[i] = facade.getTopCardFromDrawPileColorAndType();
        }
        return toReturn;
    }

    public Card[] getTopFiveCardsFromDrawPile() {
        return facade.getTopFiveCardsFromDrawPile();
    }

    public int getRedDoorCount(){
        return facade.getRedDoorCount();
    }
    public int getGreenDoorCount(){
        return facade.getGreenDoorCount();
    }
    public int getBlueDoorCount(){
        return facade.getBlueDoorCount();
    }
    public int getBrownDoorCount(){
        return facade.getBrownDoorCount();
    }

    public String getColorAndTypeOfTopDiscard(){
        return facade.getColorAndTypeOfTopDiscard();
    }

    public String getCardColorAndTypeFromHand(int cardNum){
        return facade.getCardColorAndTypeFromHand(cardNum);
    }
    public String getCardColorAndTypeFromLab(int cardNum){
        return facade.getCardColorAndTypeFromLab(cardNum);
    }
    public String getCardTypeFromHand(int cardNum){
        return facade.getCardTypeFromHand(cardNum);
    }
    public int getNightmareCount(){
        return facade.getNightmareCount();
    }
    private Facade facade = new Facade();
    private Rules rules = new Rules();


    public void rearrangeCards(String prophReturnString, Card [] prophCards) {
        facade.rearrangeCards(prophReturnString, prophCards);
    }
}
