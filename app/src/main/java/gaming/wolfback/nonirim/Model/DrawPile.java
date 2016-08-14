package gaming.wolfback.nonirim.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import gaming.wolfback.nonirim.Utility.Card;

/**
 * Created by ZWolf on 2/19/2016.
 */
public class DrawPile {
    private static Stack<Card> drawPile;
    public DrawPile(){
        drawPile = new Stack<Card>();
    }

    public boolean isEmpty(){
        return drawPile.isEmpty();
    }

    public int getDrawPileCount() {
        return drawPile.size();
    }

    public void shuffle() {
        Collections.shuffle(drawPile);
    }

    public Card top() {
        return drawPile.peek();
    }

    public Card pop() {
        return drawPile.pop();
    }

    //returns true if the card is in the drawpile and false if it is not
    public boolean removeCard(String colorOfCardToBeRemoved, String typeOfCardToBeRemoved){
        int i = 0;
        int currentSizeOfDrawPile = drawPile.size();
        while (i < currentSizeOfDrawPile) {
            if(drawPile.get(i).getColor().equals(colorOfCardToBeRemoved) && drawPile.get(i).getType().equals(typeOfCardToBeRemoved)) {
                drawPile.remove(i);
                return true;
            }
            else {++i;}
        }
        return false;
    }

    public void addCardToTop(Card cardToBeAdded) {
        Log.d ("DrawPile addCard ", cardToBeAdded.getColor() + " " + cardToBeAdded.getType());
        drawPile.push(cardToBeAdded);
    }

    //To peek at the top card, you would pass an index of 0
    public Card peekStartingFromTop (int index) {
        return drawPile.get(drawPile.size() - index - 1);
    }
}
