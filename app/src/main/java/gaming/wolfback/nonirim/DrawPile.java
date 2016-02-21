package gaming.wolfback.nonirim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * Created by ZWolf on 2/19/2016.
 */
public class DrawPile {
    private static List<Card> deck;
    DrawPile(){
        deck = new ArrayList<Card>();
    }

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
        Card tempCard = new Card(deck.get(deck.size()-1).getId(),
                deck.get(deck.size()-1).getColor(),
                deck.get(deck.size()-1).getType());

        deck.remove(deck.size() - 1);
        return tempCard;
    }

    public void addCardToDeck(Card cardToBeAdded){
        deck.add(cardToBeAdded);
    }

}
