package gaming.wolfback.nonirim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public Facade theFacade = new Facade();
    public ImageButton c0;
    public ImageButton c1;
    public ImageButton c2;
    public ImageButton c3;
    public ImageButton c4;
    public TextView discard;
    public int resId;
    public String theCardString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInitialCardsInHand();
        setOnClickListenersForHand();

        discard = (TextView) findViewById(R.id.discardPileId);
    }

    public void setInitialCardsInHand(){
        //String theCardString;
        //int resId;

        c0 = (ImageButton) findViewById(R.id.hand0);
        theCardString = theFacade.getCardFromHand(0);
        if (theCardString.equals("nightmarenightmare")) {
            theCardString = "nightmare";
        }
        resId = getResources().getIdentifier(theCardString, "drawable", getPackageName());
        c0.setImageResource(resId);

        c1 = (ImageButton) findViewById(R.id.hand1);
        theCardString = theFacade.getCardFromHand(1);
        if (theCardString.equals("nightmarenightmare")) {
            theCardString = "nightmare";
        }
        resId = getResources().getIdentifier(theCardString, "drawable", getPackageName());
        c1.setImageResource(resId);

        c2 = (ImageButton) findViewById(R.id.hand2);
        theCardString = theFacade.getCardFromHand(2);
        if (theCardString.equals("nightmarenightmare")) {
            theCardString = "nightmare";
        }
        resId = getResources().getIdentifier(theCardString, "drawable", getPackageName());
        c2.setImageResource(resId);

        c3 = (ImageButton) findViewById(R.id.hand3);
        theCardString = theFacade.getCardFromHand(3);
        if (theCardString.equals("nightmarenightmare")) {
            theCardString = "nightmare";
        }
        resId = getResources().getIdentifier(theCardString, "drawable", getPackageName());
        c3.setImageResource(resId);

        c4 = (ImageButton) findViewById(R.id.hand4);
        theCardString = theFacade.getCardFromHand(4);
        if (theCardString.equals("nightmarenightmare")) {
            theCardString = "nightmare";
        }
        resId = getResources().getIdentifier(theCardString, "drawable", getPackageName());
        c4.setImageResource(resId);

    }

    public void setOnClickListenersForHand(){
        //String theCardString;
        //int resId;
        c0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theCardString = theFacade.getCardFromHand(0);
                if (theCardString.equals("nightmarenightmare")) {
                    theCardString = "nightmare";
                }

                resId = getResources().getIdentifier(theCardString, "drawable", getPackageName());
                //Takes the index of the card in the hand, removes the card from hand, and adds the card into the labyrinth
                theFacade.playCardIntoLab(0);

                ImageView theLab = (ImageView) findViewById(R.id.LabId);
                theLab.setImageResource(resId);

                theFacade.drawFromDeckIntoHand();
                theCardString = theFacade.getCardFromHand(0);
                if (theCardString.equals("nightmarenightmare")) {
                    theCardString = "nightmare";
                }
                resId = getResources().getIdentifier(theCardString, "drawable", getPackageName());
                c0.setImageResource(resId);
            }
        });

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theCardString = theFacade.getCardFromHand(1);
                if (theCardString.equals("nightmarenightmare")) {
                    theCardString = "nightmare";
                }

                resId = getResources().getIdentifier(theCardString, "drawable", getPackageName());
                //Takes the index of the card in the hand, removes the card from hand, and adds the card into the labyrinth
                theFacade.playCardIntoLab(1);

                ImageView theLab = (ImageView) findViewById(R.id.LabId);
                theLab.setImageResource(resId);

                theFacade.drawFromDeckIntoHand();
                theCardString = theFacade.getCardFromHand(1);
                if (theCardString.equals("nightmarenightmare")) {
                    theCardString = "nightmare";
                }
                resId = getResources().getIdentifier(theCardString, "drawable", getPackageName());
                c1.setImageResource(resId);
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theCardString = theFacade.getCardFromHand(2);
                if (theCardString.equals("nightmarenightmare")) {
                    theCardString = "nightmare";
                }

                resId = getResources().getIdentifier(theCardString, "drawable", getPackageName());
                //Takes the index of the card in the hand, removes the card from hand, and adds the card into the labyrinth
                theFacade.playCardIntoLab(2);

                ImageView theLab = (ImageView) findViewById(R.id.LabId);
                theLab.setImageResource(resId);

                theFacade.drawFromDeckIntoHand();
                theCardString = theFacade.getCardFromHand(2);
                if (theCardString.equals("nightmarenightmare")) {
                    theCardString = "nightmare";
                }
                resId = getResources().getIdentifier(theCardString, "drawable", getPackageName());
                c2.setImageResource(resId);
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theCardString = theFacade.getCardFromHand(3);
                if (theCardString.equals("nightmarenightmare")) {
                    theCardString = "nightmare";
                }

                resId = getResources().getIdentifier(theCardString, "drawable", getPackageName());
                //Takes the index of the card in the hand, removes the card from hand, and adds the card into the labyrinth
                theFacade.playCardIntoLab(3);

                ImageView theLab = (ImageView) findViewById(R.id.LabId);
                theLab.setImageResource(resId);

                theFacade.drawFromDeckIntoHand();
                theCardString = theFacade.getCardFromHand(3);
                if (theCardString.equals("nightmarenightmare")) {
                    theCardString = "nightmare";
                }
                resId = getResources().getIdentifier(theCardString, "drawable", getPackageName());
                c3.setImageResource(resId);
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theCardString = theFacade.getCardFromHand(4);
                if (theCardString.equals("nightmarenightmare")) {
                    theCardString = "nightmare";
                }

                resId = getResources().getIdentifier(theCardString, "drawable", getPackageName());
                //Takes the index of the card in the hand, removes the card from hand, and adds the card into the labyrinth
                theFacade.playCardIntoLab(4);

                ImageView theLab = (ImageView) findViewById(R.id.LabId);
                theLab.setImageResource(resId);

                theFacade.drawFromDeckIntoHand();
                theCardString = theFacade.getCardFromHand(4);
                if (theCardString.equals("nightmarenightmare")) {
                    theCardString = "nightmare";
                }
                resId = getResources().getIdentifier(theCardString, "drawable", getPackageName());
                c4.setImageResource(resId);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
