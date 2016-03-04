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
    public ImageView dR1;
    public ImageView dR2;
    public ImageView dB1;
    public ImageView dB2;
    public ImageView dG1;
    public ImageView dG2;
    public ImageView dBr1;
    public ImageView dBr2;
    public TextView nightmareView;
    public int currentLabIndex = 0;
    public ImageView discard;
    public int cardImageResourceId;
    public String colorAndTypeOfCard;
    public Labyrinth theLab = new Labyrinth();
    public int redDoorCount = 0;
    public int greenDoorCount = 0;
    public int blueDoorCount = 0;
    public int brownDoorCount = 0;
    public int nightmareCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInitialCardsInHand();
        setOnClickListenersForHand();

        discard = (ImageView) findViewById(R.id.discardPileId);
        dR1 = (ImageView) findViewById(R.id.doorIdR1);
        dR2 = (ImageView) findViewById(R.id.doorIdR2);
        dB1 = (ImageView) findViewById(R.id.doorIdB1);
        dB2 = (ImageView) findViewById(R.id.doorIdB2);
        dG1 = (ImageView) findViewById(R.id.doorIdG1);
        dG2 = (ImageView) findViewById(R.id.doorIdG2);
        dBr1 = (ImageView) findViewById(R.id.doorIdBr1);
        dBr2 = (ImageView) findViewById(R.id.doorIdBr2);
        nightmareView = (TextView) findViewById(R.id.nightmareId);

    }


    public void setInitialCardsInHand(){
        c0 = (ImageButton) findViewById(R.id.hand0);
        colorAndTypeOfCard = getCardColorAndTypeFromHand(0);
        cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
        c0.setImageResource(cardImageResourceId);

        c1 = (ImageButton) findViewById(R.id.hand1);
        colorAndTypeOfCard = getCardColorAndTypeFromHand(1);
        cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
        c1.setImageResource(cardImageResourceId);

        c2 = (ImageButton) findViewById(R.id.hand2);
        colorAndTypeOfCard = getCardColorAndTypeFromHand(2);
        cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
        c2.setImageResource(cardImageResourceId);

        c3 = (ImageButton) findViewById(R.id.hand3);
        colorAndTypeOfCard = getCardColorAndTypeFromHand(3);
        cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
        c3.setImageResource(cardImageResourceId);

        c4 = (ImageButton) findViewById(R.id.hand4);
        colorAndTypeOfCard = getCardColorAndTypeFromHand(4);
        cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
        c4.setImageResource(cardImageResourceId);

    }

    public void setOnClickListenersForHand(){
        c0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cNum = 0;
                //Top half is for putting current card in hand into the Labyrinth
                colorAndTypeOfCard = getCardColorAndTypeFromHand(cNum);
                cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
                addDoor(cNum);
                updateNightmareCount();
                
                //uncomment these two to see the discard pile in action
                //theFacade.discardCardFromHand(cNum);
                //discard.setImageResource(cardImageResourceId);

                theFacade.playCardIntoLabAndRemoveCardFromHand(cNum);
                updateLabImage(cardImageResourceId);
                updateCurrentLabIndex();

                //Bottom half is for drawing a new card from the deck and updating the hand
                theFacade.drawFromDeckIntoHand();
                colorAndTypeOfCard = getCardColorAndTypeFromHand(cNum);
                cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
                c0.setImageResource(cardImageResourceId);
            }
        });

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cNum = 1;
                //Top half is for putting current card in hand into the Labyrinth
                colorAndTypeOfCard = getCardColorAndTypeFromHand(cNum);
                cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
                addDoor(cNum);
                updateNightmareCount();
                theFacade.playCardIntoLabAndRemoveCardFromHand(cNum);
                updateLabImage(cardImageResourceId);
                updateCurrentLabIndex();

                //Bottom half is for drawing a new card from the deck and updating the hand
                theFacade.drawFromDeckIntoHand();
                colorAndTypeOfCard = getCardColorAndTypeFromHand(cNum);
                cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
                c1.setImageResource(cardImageResourceId);
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cNum = 2;
                //Top half is for putting current card in hand into the Labyrinth
                colorAndTypeOfCard = getCardColorAndTypeFromHand(cNum);
                cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
                addDoor(cNum);
                updateNightmareCount();
                theFacade.playCardIntoLabAndRemoveCardFromHand(cNum);
                updateLabImage(cardImageResourceId);
                updateCurrentLabIndex();

                //Bottom half is for drawing a new card from the deck and updating the hand
                theFacade.drawFromDeckIntoHand();
                colorAndTypeOfCard = getCardColorAndTypeFromHand(cNum);
                cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
                c2.setImageResource(cardImageResourceId);
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cNum = 3;
                //Top half is for putting current card in hand into the Labyrinth
                colorAndTypeOfCard = getCardColorAndTypeFromHand(cNum);
                cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
                addDoor(cNum);
                updateNightmareCount();
                theFacade.playCardIntoLabAndRemoveCardFromHand(cNum);
                updateLabImage(cardImageResourceId);
                updateCurrentLabIndex();

                //Bottom half is for drawing a new card from the deck and updating the hand
                theFacade.drawFromDeckIntoHand();
                colorAndTypeOfCard = getCardColorAndTypeFromHand(cNum);
                cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
                c3.setImageResource(cardImageResourceId);
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cNum = 4;
                //Top half is for putting current card in hand into the Labyrinth
                colorAndTypeOfCard = getCardColorAndTypeFromHand(cNum);
                cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
                addDoor(cNum);
                updateNightmareCount();
                theFacade.playCardIntoLabAndRemoveCardFromHand(cNum);
                updateLabImage(cardImageResourceId);
                updateCurrentLabIndex();

                //Bottom half is for drawing a new card from the deck and updating the hand
                theFacade.drawFromDeckIntoHand();
                colorAndTypeOfCard = getCardColorAndTypeFromHand(cNum);
                cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
                c4.setImageResource(cardImageResourceId);
            }
        });
    }

    private void updateNightmareCount(){
        if (colorAndTypeOfCard.equals("nightmare")){
            nightmareCount++;
            nightmareView.setText(Integer.toString(nightmareCount));
        }
    }

    public void addDoor(int cNum){
        if(theFacade.getCardTypeFromHand(cNum).equals("door")){
            if(theFacade.getCardColorFromHand(cNum).equals("red")){
                redDoorCount++;
                if (redDoorCount == 1){
                    dR1.setImageResource(cardImageResourceId);
                }
                else dR2.setImageResource(cardImageResourceId);
            }
            else if(theFacade.getCardColorFromHand(cNum).equals("blue")){
                blueDoorCount++;
                if (blueDoorCount == 1){
                    dB1.setImageResource(cardImageResourceId);
                }
                else dB2.setImageResource(cardImageResourceId);
            }
            else if(theFacade.getCardColorFromHand(cNum).equals("green")){
                greenDoorCount++;
                if (greenDoorCount == 1){
                    dG1.setImageResource(cardImageResourceId);
                }
                else dG2.setImageResource(cardImageResourceId);
            }
            else if(theFacade.getCardColorFromHand(cNum).equals("brown")){
                brownDoorCount++;
                if (brownDoorCount == 1){
                    dBr1.setImageResource(cardImageResourceId);
                }
                else dBr2.setImageResource(cardImageResourceId);
            }
        }

    }

    public int getCardImageResourceId (String colorAndType) {
        return getResources().getIdentifier(colorAndType, "drawable", getPackageName());
    }

    private int getLabResourceId (int indexOfLab){
        String labIdName = "LabId" + indexOfLab;
        int resID = getResources().getIdentifier(labIdName, "id", getPackageName());
        return resID;
    }

    private void updateCurrentLabIndex(){
        currentLabIndex++;
        if (currentLabIndex == 8){
            theLab.shiftLeft();
            for (currentLabIndex = 0; currentLabIndex < 8; ++currentLabIndex){
                colorAndTypeOfCard = getCardColorAndTypeFromLab(currentLabIndex);
                cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
                updateLabImage(cardImageResourceId);
            }
            theFacade.removeCardFromLab(7);
            clearLabImage(7);
            currentLabIndex = 7;
        }
    }

    public void updateLabImage(int cardResId){
        ImageView theLab = (ImageView) findViewById(getLabResourceId(currentLabIndex));
        theLab.setImageResource(cardResId);
    }

    public void clearLabImage(int indexOfCardInLab){
        ImageView theLab = (ImageView) findViewById(getLabResourceId(indexOfCardInLab));
        theLab.setImageDrawable(null);
    }

    public String getCardColorAndTypeFromHand(int cNum){
        String colorAndTypeOfCard = theFacade.getCardColorAndTypeFromHand(cNum);
        if (colorAndTypeOfCard.equals("nightmarenightmare")) {
            colorAndTypeOfCard = "nightmare";
        }
        return colorAndTypeOfCard;
    }

    //!!! is this repeated code?
    public String getCardColorAndTypeFromLab(int cNum){
        String colorAndTypeOfCard = theFacade.getCardColorAndTypeFromLab(cNum);
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
