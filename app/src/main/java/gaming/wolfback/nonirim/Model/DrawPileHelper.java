package gaming.wolfback.nonirim.Model;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.Stack;

import gaming.wolfback.nonirim.Utility.Card;

/**
 * Created by Jarren on 8/9/2016.
 */
public class DrawPileHelper {

    public void logNextFiveInDrawPile () {
        for (int i = 0; i < 5; ++i) {
            Log.d(Integer.toString(i), drawPile.peekStartingFromTop(i).getColor() + " " + drawPile.peekStartingFromTop(i).getType());
        }
    }

    public void shuffleDrawPile () {
        drawPile.shuffle();
    }

    public void addCardToDrawPile (Card cardToBeAdded) {
        Log.d ("DrawPileHelper addCard ", cardToBeAdded.getColor() + " " + cardToBeAdded.getType());
        drawPile.addCardToTop(cardToBeAdded);
    }

    public boolean removeDoorFromDrawPile(String colorOfDoorToBeRemoved) {
        if (drawPile.removeCard(colorOfDoorToBeRemoved, "door"))
            return true;
        else return false;
    }

    public boolean removeCardFromDrawPile (String colorOfCard, String typeOfCard) {
        if (drawPile.removeCard(colorOfCard, typeOfCard)) {
            return true;
        }
        else return false;
    }
    //Removes and returns the top five location cards from the draw pile. All non-location cards are put into limbo
    public Card [] getTopFiveLocationCards() {
        Card [] fiveLocationCards = new Card [5];
        int i = 0;
        while (i < 5) {
            if (drawPile.top().getType() == "nightmare" || drawPile.top().getType() == "door") {
                limbo.push(drawPile.pop());
            }
            //To-do: add case for if the card drawn is null
            else {
                fiveLocationCards [i] = drawPile.pop();
                ++i;
            }
        }
        if (!limbo.empty()) {addLimboCardsIntoDrawPile (limbo);}

        return fiveLocationCards;
    }

    //Removes and returns the top location card from the draw pile. All non-location cards are put into limbo
    public Card getTopLocationCard() {
        Card cardToBeReturned;

        while (drawPile.top().getType() == "nightmare" || drawPile.top().getType() == "door") {
            limbo.push(drawPile.pop());
        }
        //To-do: add case for if the card drawn is null
        cardToBeReturned = drawPile.pop();

        if (!limbo.empty()) {addLimboCardsIntoDrawPile (limbo);}

        return cardToBeReturned;
    }

    //Removes and returns the top five cards from the draw pile.
    public Card [] getTopFiveCards() {
        Card [] tempFiveCards = new Card [5];

        for (int i = 0; i < 5; ++i) {
            tempFiveCards [i] = drawPile.pop();
        }

        return tempFiveCards;
    }

    //Removes and returns the top card in the draw pile
    public Card getTopCard() {
        return drawPile.pop();
    }

    //Returns but does not remove top card in the draw pile
    public Card viewTopCard() {
        return drawPile.top();
    }

    private void addLimboCardsIntoDrawPile (Stack<Card> limbo) {
        while (!limbo.empty()) {
            drawPile.addCardToTop(limbo.pop());
        }
        drawPile.shuffle();
    }

    private static Stack<Card> limbo = new Stack <Card> ();
    private static DrawPile drawPile = new DrawPile();
}
