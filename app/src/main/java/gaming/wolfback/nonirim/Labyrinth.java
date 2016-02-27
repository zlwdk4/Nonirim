package gaming.wolfback.nonirim;


import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jarren on 2/19/2016.
 */
public class Labyrinth {
    private static List<Card> labList;
    public Labyrinth()
    {
        labList = new ArrayList<Card>();

    }
    public void addCard(Card newCard)
    {
       labList.add(newCard);

    }

    public void shiftLeft(){
        for (int i = 0; i < 7; ++i){
            labList.set(i,labList.get(i+1));
        }
    }

    public void removeCard(int index)
    {
        labList.remove(index);
    }

    public Card getCard (int index){return labList.get(index);
    }

    public String getLabString()
    {
        //int i = 0;
        String theLabString = " ";

        for (int i = 0; i < labList.size(); ++i)
        {
            theLabString += ("Card #" + i + " " + labList.get(i).getColor() +
                    " " + labList.get(i).getType() + "  -  ");
        }

        Log.d("TESTLOG", theLabString);




        return theLabString;
    }
}
