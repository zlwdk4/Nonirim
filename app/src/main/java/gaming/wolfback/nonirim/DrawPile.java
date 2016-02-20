package gaming.wolfback.nonirim;

import java.util.Collections;
import java.util.Vector;

/**
 * Created by ZWolf on 2/19/2016.
 */
public class DrawPile {
    private static Vector<Card> deck = new Vector<Card>();

    public boolean isEmpty(){
        return deck.isEmpty();
    }

    public int getDeckCount(){
        return deck.size();
    }

    public void shuffle(){
        Collections.shuffle(deck);
    }

    public Card draw(){
        Card tempCard = new Card(deck.lastElement().getId(),
                deck.lastElement().getColor(),
                deck.lastElement().getType());

        deck.remove(deck.size() - 1);
        return tempCard;
    }

    public void addCardToDeck(Card cardToBeAdded){
        deck.add(cardToBeAdded);
    }

}
