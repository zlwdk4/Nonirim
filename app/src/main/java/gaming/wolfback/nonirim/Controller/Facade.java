package gaming.wolfback.nonirim.Controller;

import gaming.wolfback.nonirim.Model.DoorCount;
import gaming.wolfback.nonirim.Model.Hand;
import gaming.wolfback.nonirim.Model.Labyrinth;
import gaming.wolfback.nonirim.Model.NightmareCount;
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
//;lkjhlkjhgkjgbkjgiufviyfyctfjdhgjdchdth
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
            //drawPile2.addCardToDeck(c);
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
        //hand = new Hand(drawPile.draw(), drawPile.draw(),drawPile.draw(),drawPile.draw(),drawPile.draw());
        int j = 0;
        while (j!=5){
            drawFromDeckIntoHandInitial();
            j++;
        }

    }
    //**************************Hand stuff***********************************************//
    public String getCardColorAndTypeFromHand(int indexOfCard){
        String colorAndTypeOfCard = (hand.getCard(indexOfCard).getColor() + hand.getCard(indexOfCard).getType());
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
    //************************Deck stuff*******************************//
    public String getCardTypeFromDeck(int offset){
        return drawPile.top(offset).getType();
    }
    //**********************Interaction stuff**************************//

    public void drawFromDeckIntoHandInitial(){
        int offset = 0;
        boolean nightmareOrDoorWasDrawn = false;
        while (getCardTypeFromDeck(offset).equals("nightmare") || getCardTypeFromDeck(offset).equals("door") ){
            offset++;
            nightmareOrDoorWasDrawn = true;
        }
        Card tempCard = drawPile.draw(offset);
        tempCard.setIsCardDrawn(true);
        hand.addCard(tempCard);

        if(nightmareOrDoorWasDrawn)
            drawPile.shuffle();
    }

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
        return doorCount.getRedDoorCount();
    }

    public int getBlueDoorCount() {
        return doorCount.getBlueDoorCount();
    }

    public int getGreenDoorCount() {
        return doorCount.getGreenDoorCount();
    }

    public int getBrownDoorCount() {
        return doorCount.getBrownDoorCount();
    }

    public boolean updateDoorCount (){
        int numCardsToGiveToDidScore = numCardColorsToGiveToDidScore();
        String[] colorsOfCards = colorsOfCards(numCardsToGiveToDidScore);

        if(rules.didScore(colorsOfCards, numCardsToGiveToDidScore)) {
            String colorOfCard = lab.getCard(lab.getSize() - 1).getColor();
            if (colorOfCard.equals("red") && doorCount.getRedDoorCount() <= 1) {
                doorCount.incrementRedDoorCount();
                return true;
            } else if (colorOfCard.equals("blue")&& doorCount.getBlueDoorCount() <= 1) {
                doorCount.incrementBlueDoorCount();
                return true;
            } else if (colorOfCard.equals("green")&& doorCount.getGreenDoorCount() <= 1) {
                doorCount.incrementGreenDoorCount();
                return true;
            } else if (colorOfCard.equals("brown")&& doorCount.getBrownDoorCount() <= 1) {
                doorCount.incrementBrownDoorCount();
                return true;
            }
        }
        return false;
    }

    private int numCardColorsToGiveToDidScore(){
        int labSize = lab.getSize();
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

    private String[] colorsOfCards (int numCardsToGiveToDidScore){
        String[] colorsOfCards = new String[6];
        for (int i = 0; i < numCardsToGiveToDidScore; ++i) {
            colorsOfCards[i] = (lab.getCard(lab.getSize() - i - 1).getColor());
        }
        return colorsOfCards;
    }
    //*********************nightmare stuff*****************************//
    public void updateNightmareCount(){
        if ((lab.getCard(lab.getSize()-1)).getType().equals("nightmare")){
            nightmareCount.incrementNightmareCount();
        }
    }

    public int getNightmareCount(){
        return nightmareCount.getNightmareCount();
    }
    //*******************rules stuff*********************************//
    public boolean isValidPlay(int indexOfCardInHand){
        String curLabType = lab.getCard(lab.getSize()-1).getType();
        String curHandType = hand.getCard(indexOfCardInHand).getType();
        return (rules.isValidPlayRegardingType(curLabType, curHandType));
    }

    //*****************Discard Pile stuff***************************//
    public String getColorAndTypeOfTopDiscard(){
        String colorAndType = (discardPile.top().getColor() + discardPile.top().getType());
        if (colorAndType.equals("nightmarenightmare")){
            colorAndType = "nightmare";
        }
        return colorAndType;
    }
    //****************Private Variables***************************///
    private DrawPile drawPile = new DrawPile();
    private Hand hand = new Hand();
    private Labyrinth lab = new Labyrinth();
    private DiscardPile discardPile = new DiscardPile();
    private DoorCount doorCount = new DoorCount();
    private Rules rules = new Rules();
    private NightmareCount nightmareCount = new NightmareCount();
}
