package gaming.wolfback.nonirim;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.ArrayList;

import gaming.wolfback.nonirim.Model.Hand;
import gaming.wolfback.nonirim.Utility.Card;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
    private Hand hand = new Hand();

    public void setUp(){
        Card c0 = new Card(0, "blue", "sun");
        Card c1 = new Card(1,"blue", "moon");
        Card c2 = new Card(2, "blue", "key");
        Card c3 = new Card(3,"red", "moon");
        Card c4 = new Card(4, "red", "key");

        hand.addCard(c0);
        hand.addCard(c1);
        hand.addCard(c2);
        hand.addCard(c3);
        hand.addCard(c4);
    }

    public void testGetCardFromHand() throws Exception{
        Card c5 = hand.getCard(4);
        assertNotNull(c5);
    }
}