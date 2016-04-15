package gaming.wolfback.nonirim.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import gaming.wolfback.nonirim.R;

/**
 * Created by Jarren on 4/13/2016.
 */
public class DiscardScreen extends Activity {


    private int getCardImageResourceId(String colorAndType) {
        return getResources().getIdentifier(colorAndType, "drawable", getPackageName());
    }

    private int getLabResourceId(int indexOfLab) {
        String discardIdName = "discardCard" + indexOfLab;
        return getResources().getIdentifier(discardIdName, "id", getPackageName());
    }

    private void updateDiscardImage(int cardResId, int index) {
        ImageView theDiscardImage = (ImageView) findViewById(getLabResourceId(index));
        theDiscardImage.setImageResource(cardResId);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discard_layout);


        Intent activityThatCalled = getIntent();

        String [] discardCards = activityThatCalled.getExtras().getStringArray("discardArray");

        for (int i = 0; i < 5; ++i) {
            String colorAndTypeOfCard = discardCards[i];
            if (colorAndTypeOfCard.equals("null"))
                break;
            int cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
            updateDiscardImage(cardImageResourceId, i);
        }


    }

    public void backToMainFromDiscard(View view) {
        //Intent goingBack = new Intent();

        finish();
    }
}
