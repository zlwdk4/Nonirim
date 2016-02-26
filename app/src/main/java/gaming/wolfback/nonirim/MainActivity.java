package gaming.wolfback.nonirim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    public int cardResId;
    public String colorAndTypeOfCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInitialCardsInHand();
        setOnClickListenersForHand();

        discard = (TextView) findViewById(R.id.discardPileId);
    }


    public void setInitialCardsInHand(){
        c0 = (ImageButton) findViewById(R.id.hand0);
        colorAndTypeOfCard = getCardColorAndTypeFromHand(0);
        cardResId = getCardImageResourceId(colorAndTypeOfCard);
        c0.setImageResource(cardResId);

        c1 = (ImageButton) findViewById(R.id.hand1);
        colorAndTypeOfCard = getCardColorAndTypeFromHand(1);
        cardResId = getCardImageResourceId(colorAndTypeOfCard);
        c1.setImageResource(cardResId);

        c2 = (ImageButton) findViewById(R.id.hand2);
        colorAndTypeOfCard = getCardColorAndTypeFromHand(2);
        cardResId = getCardImageResourceId(colorAndTypeOfCard);
        c2.setImageResource(cardResId);

        c3 = (ImageButton) findViewById(R.id.hand3);
        colorAndTypeOfCard = getCardColorAndTypeFromHand(3);
        cardResId = getCardImageResourceId(colorAndTypeOfCard);
        c3.setImageResource(cardResId);

        c4 = (ImageButton) findViewById(R.id.hand4);
        colorAndTypeOfCard = getCardColorAndTypeFromHand(4);
        cardResId = getCardImageResourceId(colorAndTypeOfCard);
        c4.setImageResource(cardResId);

    }

    public void setOnClickListenersForHand(){
        c0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cNum = 0;
                //Top half is for putting current card in hand into the Labyrinth
                colorAndTypeOfCard = getCardColorAndTypeFromHand(cNum);
                cardResId = getCardImageResourceId(colorAndTypeOfCard);
                theFacade.playCardIntoLabAndRemoveCardFromHand(cNum);
                updateLabImage(cardResId);

                //Bottom half is for drawing a new card from the deck and updating the hand
                theFacade.drawFromDeckIntoHand();
                colorAndTypeOfCard = getCardColorAndTypeFromHand(cNum);
                cardResId = getCardImageResourceId(colorAndTypeOfCard);
                c0.setImageResource(cardResId);
            }
        });

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cNum = 1;
                //Top half is for putting current card in hand into the Labyrinth
                colorAndTypeOfCard = getCardColorAndTypeFromHand(cNum);
                cardResId = getCardImageResourceId(colorAndTypeOfCard);
                theFacade.playCardIntoLabAndRemoveCardFromHand(cNum);
                updateLabImage(cardResId);

                //Bottom half is for drawing a new card from the deck and updating the hand
                theFacade.drawFromDeckIntoHand();
                colorAndTypeOfCard = getCardColorAndTypeFromHand(cNum);
                cardResId = getCardImageResourceId(colorAndTypeOfCard);
                c1.setImageResource(cardResId);
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cNum = 2;
                //Top half is for putting current card in hand into the Labyrinth
                colorAndTypeOfCard = getCardColorAndTypeFromHand(cNum);
                cardResId = getCardImageResourceId(colorAndTypeOfCard);
                theFacade.playCardIntoLabAndRemoveCardFromHand(cNum);
                updateLabImage(cardResId);

                //Bottom half is for drawing a new card from the deck and updating the hand
                theFacade.drawFromDeckIntoHand();
                colorAndTypeOfCard = getCardColorAndTypeFromHand(cNum);
                cardResId = getCardImageResourceId(colorAndTypeOfCard);
                c2.setImageResource(cardResId);
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cNum = 3;
                //Top half is for putting current card in hand into the Labyrinth
                colorAndTypeOfCard = getCardColorAndTypeFromHand(cNum);
                cardResId = getCardImageResourceId(colorAndTypeOfCard);
                theFacade.playCardIntoLabAndRemoveCardFromHand(cNum);
                updateLabImage(cardResId);

                //Bottom half is for drawing a new card from the deck and updating the hand
                theFacade.drawFromDeckIntoHand();
                colorAndTypeOfCard = getCardColorAndTypeFromHand(cNum);
                cardResId = getCardImageResourceId(colorAndTypeOfCard);
                c3.setImageResource(cardResId);
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cNum = 4;
                //Top half is for putting current card in hand into the Labyrinth
                colorAndTypeOfCard = getCardColorAndTypeFromHand(cNum);
                cardResId = getCardImageResourceId(colorAndTypeOfCard);
                theFacade.playCardIntoLabAndRemoveCardFromHand(cNum);
                updateLabImage(cardResId);

                //Bottom half is for drawing a new card from the deck and updating the hand
                theFacade.drawFromDeckIntoHand();
                colorAndTypeOfCard = getCardColorAndTypeFromHand(cNum);
                cardResId = getCardImageResourceId(colorAndTypeOfCard);
                c4.setImageResource(cardResId);
            }
        });
    }

    public int getCardImageResourceId (String colorAndType) {
        return getResources().getIdentifier(colorAndType, "drawable", getPackageName());
    }

    public void updateLabImage(int cardResId){
        ImageView theLab = (ImageView) findViewById(R.id.LabId);
        theLab.setImageResource(cardResId);
    }

    public String getCardColorAndTypeFromHand(int cNum){
        String colorAndTypeOfCard = theFacade.getCardColorAndTypeFromHand(cNum);
        if (colorAndTypeOfCard.equals("nightmarenightmare")) {
            colorAndTypeOfCard = "nightmare";
        }
        return colorAndTypeOfCard;
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
