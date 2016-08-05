package gaming.wolfback.nonirim.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gaming.wolfback.nonirim.Utility.Card;

/**
 * Created by ZWolf on 2/19/2016.
 */
public class DrawPile {
    private static List<Card> deck;
    public DrawPile(){
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

    public Card top(int offset){
        offset++;
        return deck.get(deck.size()-offset);
    }

    public Card draw(int offset){
        offset++;
        Card tempCard = new Card(deck.get(deck.size()-offset).getId(),
                deck.get(deck.size()-offset).getColor(),
                deck.get(deck.size()-offset).getType());

        deck.remove(deck.size() - offset);
        return tempCard;
    }


    public void removeCard(String colorOfCardToBeRemoved, String typeOfCardToBeRemoved){
        boolean cardFound = false;
        int i = 0;
        while (!cardFound){
            if(deck.get(i).getColor().equals(colorOfCardToBeRemoved)&& deck.get(i).getType().equals(typeOfCardToBeRemoved)){
                cardFound = true;
            }
            else{++i;}
        }
        deck.remove(i);
    }

    //TODO ADD TO BOTTOM OF DECK
    public void addCardToTop(Card cardToBeAdded){
        deck.add(0, cardToBeAdded);
    }

    public void addCardToTopOfDeck(Card cardToBeAdded){
        deck.add(cardToBeAdded);
        Log.d("It is adding:", cardToBeAdded.getColor() + cardToBeAdded.getType());
    }

    public void addCardToDeck(Card cardToBeAdded){

        deck.add(cardToBeAdded);
    }

}
