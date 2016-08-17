package gaming.wolfback.nonirim.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import gaming.wolfback.nonirim.Utility.Card;

/**
 * Created by Jarren on 2/24/2016.
 */
public class DiscardPile {
    private static List<Card> discardPile;
    private static String discardString = "";
    public DiscardPile(){
        discardPile = new ArrayList<Card>();
    }
    public void addCardToDiscard(Card c){
        Log.d("\tClass: DiscardPile ", "Method: addCardToDiscard");
        Log.d("\t\tCard discarded: ", c.getColor() + " " + c.getType());
        discardPile.add(c);
    }
    public Card top(){
        return discardPile.get(discardPile.size() - 1);
    }

    public String getDiscardString(){
        discardString = "";
        for (int i = 0; i < discardPile.size(); ++i)
        {
            discardString += ("Card #" + i + " " + discardPile.get(i).getColor() +
                    " " + discardPile.get(i).getType() + "  -  ");
        }

        return discardString;
    }

    public int getSize(){
        return discardPile.size();
    }

    public String getColorAndType(int index){
        return (discardPile.get(index).getColor()+discardPile.get(index).getType());
    }
}
