package gaming.wolfback.nonirim;

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
        hand = new Hand(drawPile.draw(), drawPile.draw(),drawPile.draw(),drawPile.draw(),drawPile.draw());

    }

    public String getCardColorAndTypeFromHand(int indexOfCard){
        return (hand.getCard(indexOfCard).getColor() + hand.getCard(indexOfCard).getType());
    }

    public String getCardColorAndTypeFromLab(int indexOfCard){
        return (lab.getCard(indexOfCard).getColor()+ lab.getCard(indexOfCard).getType());
    }

    public String getCardTypeFromHand(int indexOfCard){
        return (hand.getCard(indexOfCard).getType());
    }

    public String getCardColorFromHand(int indexOfCard){
        return (hand.getCard(indexOfCard).getColor());
    }

    public void drawFromDeckIntoHand(){
        Card tempCard = drawPile.draw();
        tempCard.setIsCardDrawn(true);
        hand.addCard(tempCard);
    }

    public void removeCardFromLab(int index){
        lab.removeCard(index);
    }
    public void playCardIntoLabAndRemoveCardFromHand(int indexOfCardInHand) {
        lab.addCard(hand.removeCard(indexOfCardInHand));
    }

    public void discardCardFromHand(int indexOfCardInHand){
        discardPile.addCardToDiscard(hand.removeCard(indexOfCardInHand));
    }

    public String seeLab(){
        return lab.getLabString();
    }

    private DrawPile drawPile = new DrawPile();
    private Hand hand = new Hand();
    private Labyrinth lab = new Labyrinth();
    private DiscardPile discardPile = new DiscardPile();
}
