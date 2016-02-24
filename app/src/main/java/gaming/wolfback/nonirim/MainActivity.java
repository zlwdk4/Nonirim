package gaming.wolfback.nonirim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public Facade theFacade = new Facade();
    public TextView c0;
    public TextView c1;
    public TextView c2;
    public TextView c3;
    public TextView c4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInitialCardsInHand();
        setOnClickListenersForHand();

    }

    public void setInitialCardsInHand(){
        c0 = (TextView) findViewById(R.id.hand0);
        c0.setText(theFacade.getCardFromHand(0));

        c1 = (TextView) findViewById(R.id.hand1);
        c1.setText(theFacade.getCardFromHand(1));

        c2 = (TextView) findViewById(R.id.hand2);
        c2.setText(theFacade.getCardFromHand(2));

        c3 = (TextView) findViewById(R.id.hand3);
        c3.setText(theFacade.getCardFromHand(3));

        c4 = (TextView) findViewById(R.id.hand4);
        c4.setText(theFacade.getCardFromHand(4));

    }

    public void setOnClickListenersForHand(){
        c0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theFacade.playCardIntoLab(0);
                String theLabString = theFacade.seeLab();
                TextView t = (TextView) findViewById(R.id.LabId);
                t.setText(theLabString);
                theFacade.drawFromDeckIntoHand();
                c0.setText(theFacade.getCardFromHand(0));
            }
        });

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theFacade.playCardIntoLab(1);
                String theLabString = theFacade.seeLab();
                TextView t = (TextView) findViewById(R.id.LabId);
                t.setText(theLabString);
                theFacade.drawFromDeckIntoHand();
                c1.setText(theFacade.getCardFromHand(1));
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theFacade.playCardIntoLab(2);
                String theLabString = theFacade.seeLab();
                TextView t = (TextView) findViewById(R.id.LabId);
                t.setText(theLabString);
                theFacade.drawFromDeckIntoHand();
                c2.setText(theFacade.getCardFromHand(2));
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theFacade.playCardIntoLab(3);
                String theLabString = theFacade.seeLab();
                TextView t = (TextView) findViewById(R.id.LabId);
                t.setText(theLabString);
                theFacade.drawFromDeckIntoHand();
                c3.setText(theFacade.getCardFromHand(3));
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theFacade.playCardIntoLab(4);
                String theLabString = theFacade.seeLab();
                TextView t = (TextView) findViewById(R.id.LabId);
                t.setText(theLabString);
                theFacade.drawFromDeckIntoHand();
                c4.setText(theFacade.getCardFromHand(4));
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
