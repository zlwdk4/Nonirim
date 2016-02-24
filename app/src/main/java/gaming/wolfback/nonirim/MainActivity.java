package gaming.wolfback.nonirim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public Facade theFacade = new Facade();
    public ImageButton c0;
    public ImageButton c1;
    public ImageButton c2;
    public ImageButton c3;
    public ImageButton c4;
    public TextView discard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInitialCardsInHand();
        setOnClickListenersForHand();

        discard = (TextView) findViewById(R.id.discardPileId);
    }

    public void setInitialCardsInHand(){
        String theDoorString;
        int resId;

        c0 = (ImageButton) findViewById(R.id.hand0);
        theDoorString = theFacade.getCardFromHand(0);
        if (theDoorString == "nightmarenightmare") {
            theDoorString = "nightmare";
        }
        resId = getResources().getIdentifier(theDoorString, "drawable", getPackageName());
        c0.setImageResource(resId);

        c1 = (ImageButton) findViewById(R.id.hand1);
        theDoorString = theFacade.getCardFromHand(1);
        if (theDoorString == "nightmarenightmare") {
            theDoorString = "nightmare";
        }
        resId = getResources().getIdentifier(theDoorString, "drawable", getPackageName());
        c1.setImageResource(resId);

        c2 = (ImageButton) findViewById(R.id.hand2);
        theDoorString = theFacade.getCardFromHand(2);
        if (theDoorString == "nightmarenightmare") {
            theDoorString = "nightmare";
        }
        resId = getResources().getIdentifier(theDoorString, "drawable", getPackageName());
        c2.setImageResource(resId);

        c3 = (ImageButton) findViewById(R.id.hand3);
        theDoorString = theFacade.getCardFromHand(3);
        if (theDoorString == "nightmarenightmare") {
            theDoorString = "nightmare";
        }
        resId = getResources().getIdentifier(theDoorString, "drawable", getPackageName());
        c3.setImageResource(resId);

        c4 = (ImageButton) findViewById(R.id.hand4);
        theDoorString = theFacade.getCardFromHand(4);
        if (theDoorString == "nightmarenightmare") {
            theDoorString = "nightmare";
        }
        resId = getResources().getIdentifier(theDoorString, "drawable", getPackageName());
        c4.setImageResource(resId);

    }

    public void setOnClickListenersForHand(){
        c0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Takes the index of the card in the hand, removes the card from hand, and adds the card into the labyrinth
                theFacade.playCardIntoLab(0);
                String theLabString = theFacade.seeLab();
                TextView theLab = (TextView) findViewById(R.id.LabId);
                //
                theLab.setText(theLabString);
                theFacade.drawFromDeckIntoHand();
                //c0.setText(theFacade.getCardFromHand(0));

            }
        });

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theFacade.playCardIntoLab(1);
                String theLabString = theFacade.seeLab();
                TextView theLab = (TextView) findViewById(R.id.LabId);
                theLab.setText(theLabString);
                theFacade.drawFromDeckIntoHand();
                //c1.setText(theFacade.getCardFromHand(1));
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theFacade.playCardIntoLab(2);
                String theLabString = theFacade.seeLab();
                TextView theLab = (TextView) findViewById(R.id.LabId);
                theLab.setText(theLabString);
                theFacade.drawFromDeckIntoHand();
                //c2.setText(theFacade.getCardFromHand(2));
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theFacade.playCardIntoLab(3);
                String theLabString = theFacade.seeLab();
                TextView theLab = (TextView) findViewById(R.id.LabId);
                theLab.setText(theLabString);
                theFacade.drawFromDeckIntoHand();
                //c3.setText(theFacade.getCardFromHand(3));
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theFacade.playCardIntoLab(4);
                String theLabString = theFacade.seeLab();
                TextView theLab = (TextView) findViewById(R.id.LabId);
                theLab.setText(theLabString);
                theFacade.drawFromDeckIntoHand();
                //c4.setText(theFacade.getCardFromHand(4));
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
