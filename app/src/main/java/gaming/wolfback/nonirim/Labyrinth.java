package gaming.wolfback.nonirim;


import java.util.List;

/**
 * Created by Jarren on 2/19/2016.
 */
public class Labyrinth {
    private List<Card> labList;
    public Labyrinth()
    {
    }
    public void addCard(Card newCard)
    {
        labList.add(newCard);
    }

    void removeCard(Card toRemoveCard)
    {
        labList.remove(toRemoveCard);
    }

    String seeLab()
    {
        //int i = 0;
        String theLabString = "";

        for (int i = 0; i < labList.size(); ++i)
        {
            theLabString.concat("Card #" + i + labList.get(i).getColor() +
                    " " + labList.get(i).getType() + " ");
        }
        System.out.println(theLabString);
        return theLabString;
    }
}
