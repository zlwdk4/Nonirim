package gaming.wolfback.nonirim.Controller;

import android.util.Log;

import gaming.wolfback.nonirim.Model.Counts;
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
            drawPile.addCardToDeck(c);
            i++;
        }


        for (int j = 0; j < 8; j++) {
            Card c = new Card(i, blue, sun);
            drawPile.addCardToDeck(c);
            i++;
        }

        for (int j = 0; j < 7; j++) {
            Card c = new Card(i, green, sun);
            drawPile.addCardToDeck(c);
            i++;
        }

        for (int j = 0; j < 6; j++) {
            Card c = new Card(i, brown, sun);
            drawPile.addCardToDeck(c);
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
            drawPile.addCardToDeck(c);
            drawPile.addCardToDeck(c1);
            drawPile.addCardToDeck(c2);
            drawPile.addCardToDeck(c3);

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
            drawPile.addCardToDeck(c);
            drawPile.addCardToDeck(c1);
            drawPile.addCardToDeck(c2);
            drawPile.addCardToDeck(c3);

        }

        for (int j = 0; j < 10; j++) {
            Card c = new Card(i, nightmare, nightmare);
            drawPile.addCardToDeck(c);
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
            drawPile.addCardToDeck(c);
            drawPile.addCardToDeck(c1);
            drawPile.addCardToDeck(c2);
            drawPile.addCardToDeck(c3);
        }
        drawPile.shuffle();
        drawPile.shuffle();
        drawPile.shuffle();

        drawFiveTimesIntoHand();
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
            drawFromDeckIntoHand();
            colorAndTypeOfCard = (hand.getCard(indexOfCard).getColor() + hand.getCard(indexOfCard).getType());
        }
        if (colorAndTypeOfCard.equals("nightmarenightmare")) {
            colorAndTypeOfCard = "nightmare";
        }
        return colorAndTypeOfCard;
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

    //************************Deck stuff*******************************//
    public String getCardTypeFromDeck(int offset){
        return drawPile.top(offset).getType();
    }

    public void removeDoorFromDrawPile(String colorOfDoorUpdated){
        drawPile.removeCard(colorOfDoorUpdated, "door");
    }

    //**********************Interaction stuff**************************//
    //This method draws five cards from the draw pile and put them into the hand. If it draws a door or a nightmare, it puts that card into limbo.
    //Limbo is not actually an object here. It just means that it skips over that card and looks at the next card.
    //If a door or a nightmare was drawn, it shuffles the drawPile.
    public void drawFiveTimesIntoHand(){
        int offset = 0;
        boolean nightmareOrDoorWasDrawn = false;
        for (int i = 0; i < 5; i++){
            while (getCardTypeFromDeck(offset).equals("nightmare") || getCardTypeFromDeck(offset).equals("door") ){
                offset++;
                nightmareOrDoorWasDrawn = true;
            }
            Card tempCard = drawPile.draw(offset);
            tempCard.setIsCardDrawn(true);
            hand.addCard(tempCard);
        }
        if(nightmareOrDoorWasDrawn)
            drawPile.shuffle();
    }
    //This method draws one card from the deck into the hand. If a door is drawn, it puts that card into limbo.
    //Limbo is not actually an object here. It just means that it skips over that card and looks at the next card.
    //If a door was drawn, it shuffles the drawPile
    public void drawFromDeckIntoHand(){
        int offset = 0;
        boolean doorWasDrawn = false;
        while (getCardTypeFromDeck(offset).equals("door") ){
            offset++;
            doorWasDrawn = true;
        }
        Card tempCard = drawPile.draw(offset);
        tempCard.setIsCardDrawn(true);
        hand.addCard(tempCard);
        if(doorWasDrawn){
            drawPile.shuffle();
        }
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

    public void removeNightmareFromDrawPile(){
        drawPile.removeCard("nightmare", "nightmare");
    }
    //*****************Discard Pile stuff***************************//
    public String getColorAndTypeOfTopDiscard(){
        String colorAndType = (discardPile.top().getColor() + discardPile.top().getType());
        if (colorAndType.equals("nightmarenightmare")){
            colorAndType = "nightmare";
        }
        return colorAndType;
    }
    public void discardNextFiveFromDrawPile(){
        Log.d("TestLog before", discardPile.getDiscardString());
        int offset = 0;
        boolean nightmareOrDoorWasDrawn = false;
        for (int i = 0; i < 5; i++){
            while (getCardTypeFromDeck(offset).equals("nightmare") || getCardTypeFromDeck(offset).equals("door") ){
                offset++;
                nightmareOrDoorWasDrawn = true;
            }
            Card tempCard = drawPile.draw(offset);
            tempCard.setIsCardDiscarded(true);
            discardPile.addCardToDiscard(tempCard);
        }
        if(nightmareOrDoorWasDrawn)
            drawPile.shuffle();
        Log.d("TestLog after", discardPile.getDiscardString());
    }
    //The parameter index starts with the top card. So if you want the top card, you would give it index = 0
    //To-do: add exception if they ask for an index that isn't there
    public String getDiscardColorAndType (int index){
        int sizeOfDiscardPile = discardPile.getSize();
        return discardPile.getColorAndType(sizeOfDiscardPile - index - 1);
    }

    public String getDrawPileColorAndType (int index){
        //////get top card or total 5?
        String retString = " ";
        return  retString;
    }

    public String getTopCardFromDrawPileColorAndType (){
        //NOT ACCOUNTED FOR IF CANT DRAW MORE CARDS
       /* String[] fiveCards = new String[5];
        Card tempCard;
        for(int i = 0; i < 5; ++i){
            tempCard = drawPile.draw(0);
            fiveCards[i]  =  tempCard.getColor() + tempCard.getType();
        }
        */
        Card tempCard = drawPile.draw(0);
        String retColorAndType = tempCard.getColor() + tempCard.getType();
        return retColorAndType;

    }
    //****************Private Variables***************************///
    private DrawPile drawPile = new DrawPile();
    private Hand hand = new Hand();
    private Labyrinth lab = new Labyrinth();
    private DiscardPile discardPile = new DiscardPile();
    private Counts counts = new Counts();
}
