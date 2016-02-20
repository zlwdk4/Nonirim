package gaming.wolfback.nonirim;

/**
 * Created by ZWolf on 2/19/2016.
 */
public class Hand {
    private Card[] hand = new Card[5];

    public Hand(DrawPile deck){
        for(int i = 0; i < 5; i++){
            this.hand[i] = deck.draw();
        }
    }

    public void addCard(Card newCard){
        int i = 0;
        while(hand[i] != null){
            i++;
        }
        hand[i] = newCard;
    }

    public void removeCard(Card cardToRemove){
        int i = 0;
        while(hand[i] != cardToRemove){
            i++;
        }
        hand[i] = null;
    }
}
