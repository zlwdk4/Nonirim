import java.util.ArrayList;
import java.util.List;

import gaming.wolfback.nonirim.Card;

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
        discardPile.remove(c);
    }

    public String getDiscardString(){
        for (int i = 0; i < discardPile.size(); ++i)
        {
            discardString += ("Card #" + i + " " + discardPile.get(i).getColor() +
                    " " + discardPile.get(i).getType() + "  -  ");
        }

        return discardString;
    }

}
