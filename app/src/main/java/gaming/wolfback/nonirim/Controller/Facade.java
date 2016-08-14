package gaming.wolfback.nonirim.Controller;

import android.support.annotation.NonNull;
import android.util.Log;

import gaming.wolfback.nonirim.Model.Counts;
import gaming.wolfback.nonirim.Model.DrawPileHelper;
import gaming.wolfback.nonirim.Model.Hand;
import gaming.wolfback.nonirim.Model.Labyrinth;
import gaming.wolfback.nonirim.Utility.Card;
import gaming.wolfback.nonirim.Model.DiscardPile;
import gaming.wolfback.nonirim.Model.DrawPile;

/**
 * Created by Jarren on 2/20/2016.
 */
public class Facade {
    public Facade(){
        String sun = "sun", moon = "moon", key = "key", blue = "blue", brown = "brown",
                green = "green", red = "red", nightmare = "nightmare", door = "door";

        int i = 1;

        for (int j = 0; j < 9; j++) {
            Card c = new Card(i, red, sun);
            drawPileHelper.addCardToDrawPile(c);
            i++;
        }

        for (int j = 0; j < 8; j++) {
            Card c = new Card(i, blue, sun);
            drawPileHelper.addCardToDrawPile(c);
            i++;
        }

        for (int j = 0; j < 7; j++) {
            Card c = new Card(i, green, sun);
            drawPileHelper.addCardToDrawPile(c);
            i++;
        }

        for (int j = 0; j < 6; j++) {
            Card c = new Card(i, brown, sun);
            drawPileHelper.addCardToDrawPile(c);
            i++;
        }

        for (int j = 0; j < 4; j++) {
            Card c = new Card(i, red, moon);
            i++;
            Card c1 = new Card(i, blue, moon);
            i++;
            Card c2 = new Card(i, green, moon);
            i++;
            Card c3 = new Card(i, brown, moon);
            i++;
            drawPileHelper.addCardToDrawPile(c);
            drawPileHelper.addCardToDrawPile(c1);
            drawPileHelper.addCardToDrawPile(c2);
            drawPileHelper.addCardToDrawPile(c3);

        }

        for (int j = 0; j < 3; j++) {
            Card c = new Card(i, red, key);
            i++;
            Card c1 = new Card(i, blue, key);
            i++;
            Card c2 = new Card(i, green, key);
            i++;
            Card c3 = new Card(i, brown, key);
            i++;
            drawPileHelper.addCardToDrawPile(c);
            drawPileHelper.addCardToDrawPile(c1);
            drawPileHelper.addCardToDrawPile(c2);
            drawPileHelper.addCardToDrawPile(c3);

        }

        for (int j = 0; j < 10; j++) {
            Card c = new Card(i, "", nightmare);
            drawPileHelper.addCardToDrawPile(c);
            i++;
        }

        for (int j = 0; j < 2; j++) {
            Card c = new Card(i, red, door);
            i++;
            Card c1 = new Card(i, blue, door);
            i++;
            Card c2 = new Card(i, green, door);
            i++;
            Card c3 = new Card(i, brown, door);
            i++;
            drawPileHelper.addCardToDrawPile(c);
            drawPileHelper.addCardToDrawPile(c1);
            drawPileHelper.addCardToDrawPile(c2);
            drawPileHelper.addCardToDrawPile(c3);
        }
        drawPileHelper.shuffleDrawPile();

        drawFiveTimesIntoHand();
        hand.seeHand();
    }
    //**************************Hand stuff***********************************************//
    public String getCardColorAndTypeFromHand(int indexOfCard){
        String colorAndTypeOfCard = "";
        try {
            colorAndTypeOfCard = (hand.getCard(indexOfCard).getColor() + hand.getCard(indexOfCard).getType());
        }
        catch(IndexOutOfBoundsException e){
            Log.d("TESTLOG", "Facade index out of bounds exception caught");
        }
        catch (NullPointerException e){
            Log.d("TESTLOG", "Facade nullptr exception caught");
            drawFromDrawPileIntoHand();
            colorAndTypeOfCard = (hand.getCard(indexOfCard).getColor() + hand.getCard(indexOfCard).getType());
        }
        return removeDuplicatedNightmareName(colorAndTypeOfCard);
    }

    public String getCardTypeFromHand(int indexOfCard){
        return (hand.getCard(indexOfCard).getType());
    }

    public String getCardColorFromHand(int indexOfCard){
        return (hand.getCard(indexOfCard).getColor());
    }

    public void discardCardFromHand(int indexOfCardInHand){
        discardPile.addCardToDiscard(hand.removeCard(indexOfCardInHand));
    }
    //This method assumes that the last card placed in the hand was a nightmare
    public void getRidOfNightmareInHand(){
        //hand.seeHand();
        int indexOfNightmare = hand.getIndexOfLastCardAdded();
        hand.removeCard(indexOfNightmare);
        //hand.seeHand();
    }

    public void seeHand(){
        hand.seeHand();
    }

    //****************************Lab stuff**********************************//
    public String getCardColorAndTypeFromLab(int indexOfCard){
        String colorAndTypeOfCard = (lab.getCard(indexOfCard).getColor()+ lab.getCard(indexOfCard).getType());
        if (colorAndTypeOfCard.equals("nullnull")){
            return "null";
        }
        return removeDuplicatedNightmareName(colorAndTypeOfCard);
    }

    @NonNull
    private String removeDuplicatedNightmareName(String colorAndTypeOfCard) {
        if (colorAndTypeOfCard.equals("nightmarenightmare")) {
            colorAndTypeOfCard = "nightmare";
        }
        return colorAndTypeOfCard;
    }

    public int getLabSize(){
        return lab.getSize();
    }
    public String getLabColor(int indexOfCard){
        return lab.getCard(indexOfCard).getColor();
    }
    public String getLabType(int indexOfCard){
        return lab.getCard(indexOfCard).getType();
    }

    //************************DrawPile stuff*******************************//
    public boolean removeDoorFromDrawPile(String colorOfDoorUpdated){
        if (drawPileHelper.removeDoorFromDrawPile(colorOfDoorUpdated)) {
            return true;
        }
        else return false;
    }

    //**********************Interaction stuff**************************//
    //This method draws five location into the hand
    public void drawFiveTimesIntoHand() {
        Card cardsToBeAddedToHand [] = drawPileHelper.getTopFiveLocationCards();

        for (int i = 0; i < 5; ++i) {
            Log.d ("Class: Facade", "Function: drawFiveTimeIntoHand");
            Log.d ("Cards being ", "added into hand");
            Log.d("card #" + Integer.toString(i), cardsToBeAddedToHand[i].getColor() + " " + cardsToBeAddedToHand[0].getType());
            hand.addCard(cardsToBeAddedToHand[i]);
        }
    }
    //This method draws one location card from the drawPile into the hand.
    public void drawFromDrawPileIntoHand(){
        Card cardToBeAddedToHand = drawPileHelper.getTopLocationCard();

        hand.addCard(cardToBeAddedToHand);
    }

    public void playCardIntoLabAndRemoveCardFromHand(int indexOfCardInHand) {
        lab.addCard(hand.removeCard(indexOfCardInHand));
    }


    //*********************door stuff*****************************//
    public int getRedDoorCount() {
        return counts.getRedDoorCount();
    }

    public int getBlueDoorCount() {
        return counts.getBlueDoorCount();
    }

    public int getGreenDoorCount() {
        return counts.getGreenDoorCount();
    }

    public int getBrownDoorCount() {
        return counts.getBrownDoorCount();
    }

    //returns the color of the door that was updated
    public String updateDoorCount (){
        String colorOfCard = lab.getCard(lab.getSize() - 1).getColor();
        if (colorOfCard.equals("red") && counts.getRedDoorCount() <= 1) {
            counts.incrementRedDoorCount();
            return "red";
        } else if (colorOfCard.equals("blue")&& counts.getBlueDoorCount() <= 1) {
            counts.incrementBlueDoorCount();
            return "blue";
        } else if (colorOfCard.equals("green")&& counts.getGreenDoorCount() <= 1) {
            counts.incrementGreenDoorCount();
            return "green";
        } else if (colorOfCard.equals("brown")&& counts.getBrownDoorCount() <= 1) {
            counts.incrementBrownDoorCount();
            return "brown";
        }
        return "";
    }
    //*********************nightmare stuff*****************************//
    public void incrementNightmareCount(){
        counts.incrementNightmareCount();
    }
    public boolean wasNightmareDrawn(){
        if (hand.getCard(hand.getIndexOfLastCardAdded()).getType().equals("nightmare"))
            return true;
        else return false;
    }
    public int getNightmareCount(){
        return counts.getNightmareCount();
    }

    //// TODO: 8/9/2016 have this return a boolean to make sure that there is a nightmare in the draw pile
    public void removeNightmareFromDrawPile(){
        drawPileHelper.removeCardFromDrawPile("nightmare", "nightmare");
    }
    //*****************Discard Pile stuff***************************//
    public String getColorAndTypeOfTopDiscard(){
        String colorAndType = (discardPile.top().getColor() + discardPile.top().getType());
        return removeDuplicatedNightmareName(colorAndType);
    }
    public void discardNextFiveFromDrawPile(){
        Card cardsToBeDiscardPile [] = drawPileHelper.getTopFiveLocationCards();

        for (int i = 0; i < 5; ++i) {
            discardPile.addCardToDiscard(cardsToBeDiscardPile[i]);
        }
    }
    //The parameter index starts with the top card. So if you want the top card, you would give it index = 0
    //To-do: add exception if they ask for an index that isn't there
    public String getDiscardColorAndType (int index){
        int sizeOfDiscardPile = discardPile.getSize();
        return discardPile.getColorAndType(sizeOfDiscardPile - index - 1);
    }

    public String getDrawPileColorAndType (int index){
        String retString = " ";
        return  retString;
    }

    public String getTopCardFromDrawPileColorAndType (){
        //NOT ACCOUNTED FOR IF CANT DRAW MORE CARDS
        Card tempCard = drawPileHelper.getTopCard();
        String retColorAndType = tempCard.getColor() + tempCard.getType();
        return retColorAndType;

    }

    public Card [] getTopFiveCardsFromDrawPile() {
        return drawPileHelper.getTopFiveCards();
    }

    public void rearrangeCards(String prophReturnString, Card [] prophCards) {
        Log.d("Class: Facade", "Method: rearrange cards");

        Card[] arrangedCards = new Card[5];
        char curChar;
        int posInt;

        for(int i = 0; i < 5 ; ++i){
            curChar = prophReturnString.charAt(i);
            if(curChar == 'X'){
                arrangedCards[4] = prophCards[i];
                continue;
            }

            posInt = (int) curChar;
            posInt -= '0';
            posInt--;
            arrangedCards[posInt] = prophCards[i];
        }

        String stringOfRCards = "";

        for (int i = 0; i < arrangedCards.length; i++) {
            stringOfRCards += Integer.toString(i) + ": " + arrangedCards[i].getColor() + " " + arrangedCards[i].getType() + ", " ;
        }

        Log.d("State After Rearrange: ", stringOfRCards);

        for (int i = 3; i >= 0; i--) {
            drawPileHelper.addCardToDrawPile(arrangedCards[i]);
        }

        discardPile.addCardToDiscard(arrangedCards[4]);

        Log.d("Card put in discard ", arrangedCards[4].getColor() + " " + arrangedCards[4].getType());

    }

    //****************Private Variables***************************///
    private DrawPileHelper drawPileHelper = new DrawPileHelper();
    private Hand hand = new Hand();
    private Labyrinth lab = new Labyrinth();
    private DiscardPile discardPile = new DiscardPile();
    private Counts counts = new Counts();

}
