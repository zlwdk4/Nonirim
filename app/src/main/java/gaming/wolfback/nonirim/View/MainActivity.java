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

import gaming.wolfback.nonirim.Controller.Facade;
import gaming.wolfback.nonirim.R;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private Facade theFacade = new Facade();
    private ImageButton[] handButtons;
    private ImageView [] labViews;
    private TextView doorRed;
    private TextView doorBlue;
    private TextView doorGreen;
    private TextView doorBrown;
    private TextView nightmareView;
    //currentIndexOfLabUI keeps track of where the next image to be placed in the lab should be
    private int currentIndexOfLabUI = 0;
    //currentIndexOfLabToBePulledFrom keeps track of which index in the lab we should be pulling from
    private int currentIndexOfLabToBePulledFrom;
    private ImageView discard;
    private int cardImageResourceId;
    private String colorAndTypeOfCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setOnClickListenersForHand();
        setInitialCardsInHand();
        setHeightAndWidthOfHandButtons(90,60);
        setHeightAndWidthOfLab(90, 60);


        discard = (ImageView) findViewById(R.id.discardPileId);
        doorRed = (TextView) findViewById(R.id.doorIdRed);
        doorBlue = (TextView) findViewById(R.id.doorIdBlue);
        doorGreen = (TextView) findViewById(R.id.doorIdGreen);
        doorBrown = (TextView) findViewById(R.id.doorIdBrown);
        nightmareView = (TextView) findViewById(R.id.nightmareId);

    }
public void setOnClickListenersForHand(){
    handButtons = new ImageButton[5];
    for (int i = 0; i < handButtons.length; ++i){
        String buttonID = "hand" + (i);
        int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
        handButtons[i] = ((ImageButton) findViewById(resID));
        handButtons[i].setOnClickListener(this);
    }
}

    public void setHeightAndWidthOfHandButtons(int h, int w){
        for (int i = 0; i < handButtons.length; ++i){
            handButtons[i].getLayoutParams().width = w;
            handButtons[i].getLayoutParams().height = h;
        }
    }

    public void setHeightAndWidthOfLab(int h, int w){
        labViews = new ImageView[8];
        for (int i = 0; i < labViews.length; ++i){
            String labID = "LabId" + (i);
            int resID = getResources().getIdentifier(labID, "id", getPackageName());
            labViews[i] = ((ImageView) findViewById(resID));
            labViews[i].getLayoutParams().width = w;
            labViews[i].getLayoutParams().height = h;

        }

    }
    public int cNum;
    public int onClickCounter;
    @Override
    public void onClick(View v) {
        for (onClickCounter = 0; onClickCounter < handButtons.length; onClickCounter++) {
            if (handButtons[onClickCounter].getId() == v.getId()) {
                cNum = onClickCounter;
                if(theFacade.isValidPlay(cNum)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Play or Discard");
                    builder.setMessage("Do you want to play or discard this card?");
                    builder.setCancelable(true);
                    builder.setPositiveButton("Play", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            putCardInLab();
                        }
                    });
                    builder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            discardCard();
                        }
                    });
                    builder.show();
                    break;
                }
                else{
                    discardCard();
                    break;
                }
            }
        }
    }

    public void putCardInLab(){
        colorAndTypeOfCard = theFacade.getCardColorAndTypeFromHand(cNum);
        cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
        updateNightmareCount();

        theFacade.playCardIntoLabAndRemoveCardFromHand(cNum);
        updateDoorCount(cNum);
        updateLabImage(cardImageResourceId);
        currentIndexOfLabUI++;
        if (currentIndexOfLabUI == 8) {
            shiftCardsInLab();
        }
        drawNewCard();
    }
    public void discardCard(){
        cNum = onClickCounter;
        colorAndTypeOfCard = theFacade.getCardColorAndTypeFromHand(cNum);
        cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
        theFacade.discardCardFromHand(cNum);
        discard.setImageResource(cardImageResourceId);
        drawNewCard();
    }
    private void drawNewCard(){
        theFacade.drawFromDeckIntoHand();
        colorAndTypeOfCard = theFacade.getCardColorAndTypeFromHand(cNum);
        cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
        handButtons[onClickCounter].setImageResource(cardImageResourceId);
    }

    public void setInitialCardsInHand() {
        for (int i = 0; i < handButtons.length; ++i) {
            colorAndTypeOfCard = theFacade.getCardColorAndTypeFromHand(i);
            cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
            handButtons[i].setImageResource(cardImageResourceId);
        }
    }

    private void updateNightmareCount(){
        theFacade.updateNightmareCount(colorAndTypeOfCard);
        nightmareView.setText(Integer.toString(theFacade.getNightmareCount()));
    }

    private void updateDoorCount(int cNum){
        theFacade.updateDoorCount(cNum);
        doorRed.setText(Integer.toString(theFacade.getRedDoorCount()));
        doorBlue.setText(Integer.toString(theFacade.getBlueDoorCount()));
        doorGreen.setText(Integer.toString(theFacade.getGreenDoorCount()));
        doorBrown.setText(Integer.toString(theFacade.getBrownDoorCount()));
    }
    public int getCardImageResourceId (String colorAndType) {
        return getResources().getIdentifier(colorAndType, "drawable", getPackageName());
    }

    private int getLabResourceId (int indexOfLab){
        String labIdName = "LabId" + indexOfLab;
        int resID = getResources().getIdentifier(labIdName, "id", getPackageName());
        return resID;
    }

    private void shiftCardsInLab(){
        currentIndexOfLabToBePulledFrom++;
        displayCardsInLab();
        currentIndexOfLabUI = 7;
    }

    private void displayCardsInLab(){
        int i = currentIndexOfLabToBePulledFrom;
        for (currentIndexOfLabUI = 0; currentIndexOfLabUI < 7; ++currentIndexOfLabUI){
            colorAndTypeOfCard = theFacade.getCardColorAndTypeFromLab(i);
            if (colorAndTypeOfCard.equals("null"))
                break;
            cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
            updateLabImage(cardImageResourceId);
            i++;
        }
    }

    public void updateLabImage(int cardResId){
        ImageView theLab = (ImageView) findViewById(getLabResourceId(currentIndexOfLabUI));
        theLab.setImageResource(cardResId);
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
