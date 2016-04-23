package gaming.wolfback.nonirim.View;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import gaming.wolfback.nonirim.R;

public class ProphecyScreen extends Activity {


    private int getCardImageResourceId(String colorAndType) {
        return getResources().getIdentifier(colorAndType, "drawable", getPackageName());
    }

    private int getLabResourceId(int indexOfLab) {
        String prophCardIdName = "prophCard" + indexOfLab;
        return getResources().getIdentifier(prophCardIdName, "id", getPackageName());
    }

    private void updateProphecyCardImage(int cardResId, int index) {
        ImageView prophCardView = (ImageView) findViewById(getLabResourceId(index));
        prophCardView.setImageResource(cardResId);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prophecy_screen);

        Intent activityThatCalled = getIntent();

        String [] prophCards = activityThatCalled.getExtras().getStringArray("prophArray");

        for (int i = 0; i < 5; ++i) {
            String colorAndTypeOfCard = prophCards[i];
            if (colorAndTypeOfCard.equals("null"))
                break;
            int cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
            updateProphecyCardImage(cardImageResourceId, i);
        }


    }

    public void backToMainFromProph(View view) {


        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_prophecy_screen, menu);
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
