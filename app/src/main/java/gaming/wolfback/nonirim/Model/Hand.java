package gaming.wolfback.nonirim.Model;

import android.util.Log;

import junit.framework.Assert;

import gaming.wolfback.nonirim.AssertSettings;
import gaming.wolfback.nonirim.Utility.Card;

/**
 * Created by ZWolf on 2/19/2016.
 */
public class Hand {
    private  Card[] hand;
    private final int maxCardsInHand = 5;
    private int indexOfLastCardAdded;

    public Hand() {
        hand = new Card[5];
        hand[0] = null;
        hand[1] = null;
        hand[2] = null;
        hand[3] = null;
        hand[4] = null;

    }

    public Hand(Card c0, Card c1, Card c2, Card c3, Card c4) {
        hand[0] = c0;
        hand[1] = c1;
        hand[2] = c2;
        hand[3] = c3;
        hand[4] = c4;

    }

    public void addCard(Card newCard) {
        Log.d ("\tClass: Hand", "Method: addCard");
        Log.d ("\t\tCard added: ", newCard.getColor() + " " + newCard.getType());
        int i = 0;
        while (i != maxCardsInHand && hand[i] != null) {
            i++;
        }
        if (i == 5) {
            return;
        } else {
            hand[i] = newCard;
            indexOfLastCardAdded = i;
            Log.d ("\t\tCard added @ index: ", Integer.toString(i));
        }
    }


    public Card removeCard(int index) {
        Log.d ("\tClass: Hand", "Method: removeCard");
        Card tempCard = hand[index];
        Log.d ("\t\tCard removed: ", hand[index].getColor() + " " + hand[index].getType());
        hand[index] = null;
        return tempCard;
    }

    public int getIndexOfLastCardAdded(){
        return indexOfLastCardAdded;
    }

    //throws NullPointerException if there is no card in the hand at the index given
    //throws IndexOutOfBoundsException if the index is greater than the maximum allowed cards in hand minus 1
    public Card getCard(int index) throws NullPointerException, IndexOutOfBoundsException{
        if (index > maxCardsInHand - 1){
            throw new IndexOutOfBoundsException();
        }
        if (hand[index]==null){
            return null;
            //throw new NullPointerException();
        }
        else {
            return hand[index];
        }
        //Card nullCard = new Card(0, "null", "null");
        //return nullCard;
    }
    //This is for debugging purposes only
    public void seeHand(){
        Log.d ("TestLog the hand 0", hand[0].getType());
        Log.d ("TestLog the hand 1", hand[1].getType());
        Log.d ("TestLog the hand 2", hand[2].getType());
        Log.d ("TestLog the hand 3", hand[3].getType());
        Log.d ("TestLog the hand 4", hand[4].getType());
    }
}


