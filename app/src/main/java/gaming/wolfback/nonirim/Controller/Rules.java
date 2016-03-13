package gaming.wolfback.nonirim.Controller;


/**
 * Created by Jarren on 3/12/2016.
 */
public class Rules {
    public boolean playIntoLabType(String prevCardType, String currentCardType){
        if (prevCardType.equals("null"))
            return true;
        else
            return !(prevCardType.equals(currentCardType));
    }
    public boolean isSequenceOfThree(String c1Color, String c2Color, String c3Color){
        if (c1Color.equals(c2Color) && c2Color.equals(c3Color)){
            return true;
        }
        else return false;
    }
}

