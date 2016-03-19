package gaming.wolfback.nonirim.View;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

import gaming.wolfback.nonirim.Controller.Controller;
import gaming.wolfback.nonirim.R;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private Controller controller = new Controller();
    private ImageButton[] handButtons;
    private TextView doorRed;
    private TextView doorBlue;
    private TextView doorGreen;
    private TextView doorBrown;
    private TextView nightmareView;
    //currentIndexOfLabUI keeps track of where the next image to be placed in the lab should be
    private int currentIndexOfLabUI = 0;
    //currentIndexOfLabToBePulledFrom keeps track of which index in the lab we should be pulling from
    private int currentIndexOfLabToBePulledFrom;
    private ImageView discardPileView;
    private int cardNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setOnClickListenersForHand();
        setInitialCardsInHand();
        setHeightAndWidthOfHandButtons(90,60);
        setHeightAndWidthOfLab(90, 60);


        discardPileView = (ImageView) findViewById(R.id.discardPileId);
        doorRed = (TextView) findViewById(R.id.doorIdRed);
        doorBlue = (TextView) findViewById(R.id.doorIdBlue);
        doorGreen = (TextView) findViewById(R.id.doorIdGreen);
        doorBrown = (TextView) findViewById(R.id.doorIdBrown);
        nightmareView = (TextView) findViewById(R.id.nightmareId);

    }
    private void setOnClickListenersForHand(){
        handButtons = new ImageButton[5];
        for (int i = 0; i < handButtons.length; ++i){
            String buttonID = "hand" + (i);
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            handButtons[i] = ((ImageButton) findViewById(resID));
            handButtons[i].setOnClickListener(this);
        }
    }

    private void setHeightAndWidthOfHandButtons(int h, int w){
        for (ImageView imageButton : handButtons){
            imageButton.getLayoutParams().width = w;
            imageButton.getLayoutParams().height = h;
        }
    }

    private void setHeightAndWidthOfLab(int h, int w){
        ImageView[] labViews = new ImageView[8];
        for (int i = 0; i < labViews.length; ++i){
            String labID = "LabId" + (i);
            int resID = getResources().getIdentifier(labID, "id", getPackageName());
            labViews[i] = ((ImageView) findViewById(resID));
            labViews[i].getLayoutParams().width = w;
            labViews[i].getLayoutParams().height = h;

        }

    }
    @Override
    public void onClick(View v) {
        for (cardNum = 0; cardNum < handButtons.length; cardNum++) {
            if (handButtons[cardNum].getId() == v.getId()) {
                if(controller.isValidPlay(cardNum)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Play or Discard");
                    builder.setMessage("Do you want to play or discard this card?");
                    builder.setCancelable(true);
                    builder.setPositiveButton("Play", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            controller.playCard(cardNum);
                            updateDoorCount();
                            updateNightmareCount();
                            incrementIndexOfLabUI();
                            shiftCardsInLab();
                            displayCardsInLab();
                            updateImageOfHand(cardNum);
                        }
                    });
                    builder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            controller.discardCard(cardNum);
                            updateImageOfDiscard();
                            updateImageOfHand(cardNum);
                        }
                    });
                    builder.show();
                    break;
                }
                else{
                    controller.discardCard(cardNum);
                    updateImageOfDiscard();
                    updateImageOfHand(cardNum);
                    break;
                }
            }
        }
    }

//*************************Discard UI Stuff*******************************************************************************//////
    private void updateImageOfDiscard(){
        String colorAndTypeOfCard = controller.getColorAndTypeOfTopDiscard();
        int cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
        discardPileView.setImageResource(cardImageResourceId);
    }
//*****************************Hand UI stuff************************************************************************************////
    private void updateImageOfHand(int cardNum){
        String colorAndTypeOfCard = controller.getCardColorAndTypeFromHand(cardNum);
        int cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
        handButtons[cardNum].setImageResource(cardImageResourceId);
    }

    private void setInitialCardsInHand() {
        for (int i = 0; i < handButtons.length; ++i) {
            String colorAndTypeOfCard = controller.getCardColorAndTypeFromHand(i);
            int cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
            handButtons[i].setImageResource(cardImageResourceId);
        }
    }
//************************************************************************************************************************************

    private void updateNightmareCount(){
        nightmareView.setText(Integer.toString(controller.getNightmareCount()));
    }

    private void updateDoorCount(){
        doorRed.setText(Integer.toString(controller.getRedDoorCount()));
        doorBlue.setText(Integer.toString(controller.getBlueDoorCount()));
        doorGreen.setText(Integer.toString(controller.getGreenDoorCount()));
        doorBrown.setText(Integer.toString(controller.getBrownDoorCount()));
    }
    private int getCardImageResourceId (String colorAndType) {
        return getResources().getIdentifier(colorAndType, "drawable", getPackageName());
    }

    private int getLabResourceId (int indexOfLab){
        String labIdName = "LabId" + indexOfLab;
        return getResources().getIdentifier(labIdName, "id", getPackageName());
    }

    //*********************Lab UI stuff*********************************************************************************************************************///
    private void incrementIndexOfLabUI(){
        currentIndexOfLabUI++;
    }

    //The end result of calling this function is that all of the cards in the lab are shifted to the left if necessary
    //preconditions: the lab is full (currently that is when there are 8 cards in the lab)
    //post conditions: the cards in the lab will all be shifted left. The card that was in the 0th place is out of view. And the next place
    //that a card will be places is in the 8th spot (index 7)
    private void shiftCardsInLab(){
        if (currentIndexOfLabUI == 8) {
            currentIndexOfLabToBePulledFrom++;
            displayCardsInLab();
            currentIndexOfLabUI = 7;
        }
    }

    //preconditions: none
    //post conditions: up to seven cards will be displayed on the screen
    private void displayCardsInLab(){
        int i = currentIndexOfLabToBePulledFrom;
        for (currentIndexOfLabUI = 0; currentIndexOfLabUI < 7; ++currentIndexOfLabUI){
            String colorAndTypeOfCard = controller.getCardColorAndTypeFromLab(i);
            if (colorAndTypeOfCard.equals("null"))
                break;
            int cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
            updateLabImage(cardImageResourceId);
            i++;
        }
    }

    private void updateLabImage(int cardResId){
        ImageView theLab = (ImageView) findViewById(getLabResourceId(currentIndexOfLabUI));
        theLab.setImageResource(cardResId);
    }
//**************************************************************************************************

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
