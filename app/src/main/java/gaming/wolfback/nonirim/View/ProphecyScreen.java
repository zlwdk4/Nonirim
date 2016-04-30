package gaming.wolfback.nonirim.View;

import android.app.Activity;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import gaming.wolfback.nonirim.R;

public class ProphecyScreen extends Activity {



    public String prophSelectionString = "";

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



        class DragShadow extends View.DragShadowBuilder {

            ColorDrawable greyBox;


            public DragShadow(View view) {
                super(view);
                greyBox = new ColorDrawable(Color.BLACK);

            }

            @Override
            public void onDrawShadow(Canvas canvas) {
                greyBox.draw(canvas);
            }

            @Override
            public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {
                View v = getView();

                int height = (int) v.getHeight() / 2;
                int width = (int) v.getWidth() / 2;

                greyBox.setBounds(0, 0, width, height);


                shadowSize.set(width, height);

                shadowTouchPoint.set((int) width / 2, (int) height / 2);
            }
        }


        //DRAG AND DROP LOGIC







        View.OnDragListener dropListener = new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                int dragEvent = event.getAction();
                switch (dragEvent){
                    case DragEvent.ACTION_DROP:
                        View dragged = (View) event.getLocalState();
                        ClipData theData = event.getClipData();
                        int theInd;

                        String theSelection = theData.getItemAt(0).coerceToText(getApplicationContext()).toString();


                        if(v.getId() == R.id.proph_select_display_1){
                            TextView tv = (TextView) findViewById(R.id.proph_display_1);
                            tv.setText(theSelection);

                        }

                        else if(v.getId() == R.id.proph_select_display_2){
                            TextView tv = (TextView) findViewById(R.id.proph_display_2);
                            tv.setText(theSelection);

                        }

                        else if(v.getId() == R.id.proph_select_display_3){
                            TextView tv = (TextView) findViewById(R.id.proph_display_3);
                            tv.setText(theSelection);
                        }

                        else if(v.getId() == R.id.proph_select_display_4){
                            TextView tv = (TextView) findViewById(R.id.proph_display_4);
                            tv.setText(theSelection);
                        }

                        else if(v.getId() == R.id.proph_select_display_5){
                            TextView tv = (TextView) findViewById(R.id.proph_display_5);
                            tv.setText(theSelection);
                        }

                }
                return true;
            }
        };



        ////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////

        View.OnTouchListener touchListener1 = new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                DragShadow dragShadow = new DragShadow(v);

                //used to pass the metadata through (card id being used)
                ClipData data = ClipData.newPlainText("id", "1");

                v.startDrag(data, dragShadow, v, 0);

                return false;
            }
        };

        View.OnTouchListener touchListener2 = new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                DragShadow dragShadow = new DragShadow(v);

                //used to pass the metadata through (card id being used)
                ClipData data = ClipData.newPlainText("id", "2");

                v.startDrag(data, dragShadow, v, 0);

                return false;
            }
        };

        View.OnTouchListener touchListener3 = new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                DragShadow dragShadow = new DragShadow(v);

                //used to pass the metadata through (card id being used)
                ClipData data = ClipData.newPlainText("id", "3");

                v.startDrag(data, dragShadow, v, 0);

                return false;
            }
        };

        View.OnTouchListener touchListener4 = new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                DragShadow dragShadow = new DragShadow(v);

                //used to pass the metadata through (card id being used)
                ClipData data = ClipData.newPlainText("id", "4");

                v.startDrag(data, dragShadow, v, 0);

                return false;
            }
        };




        View.OnTouchListener touchListenerX = new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                DragShadow dragShadow = new DragShadow(v);

                //used to pass the metadata through (card id being used)
                ClipData data = ClipData.newPlainText("id", "X");

                v.startDrag(data, dragShadow, v, 0);

                return false;
            }
        };

        ////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////

        ///SETTING TARGETS
        ImageView prophTarget1 = (ImageView) findViewById(R.id.proph_select_display_1);
        prophTarget1.setOnDragListener(dropListener);

        ImageView prophTarget2 = (ImageView) findViewById(R.id.proph_select_display_2);
        prophTarget2.setOnDragListener(dropListener);

        ImageView prophTarget3 = (ImageView) findViewById(R.id.proph_select_display_3);
        prophTarget3.setOnDragListener(dropListener);

        ImageView prophTarget4 = (ImageView) findViewById(R.id.proph_select_display_4);
        prophTarget4.setOnDragListener(dropListener);

        ImageView prophTarget5 = (ImageView) findViewById(R.id.proph_select_display_5);
        prophTarget5.setOnDragListener(dropListener);

        TextView prophSelectOption1 = (TextView) findViewById(R.id.proph_option_1);
        prophSelectOption1.setOnTouchListener(touchListener1);

        TextView prophSelectOption2 = (TextView) findViewById(R.id.proph_option_2);
        prophSelectOption2.setOnTouchListener(touchListener2);

        TextView prophSelectOption3 = (TextView) findViewById(R.id.proph_option_3);
        prophSelectOption3.setOnTouchListener(touchListener3);

        TextView prophSelectOption4 = (TextView) findViewById(R.id.proph_option_4);
        prophSelectOption4.setOnTouchListener(touchListener4);

        TextView prophSelectOptionX = (TextView) findViewById(R.id.proph_option_x);
        prophSelectOptionX.setOnTouchListener(touchListenerX);


        Intent activityThatCalled = getIntent();

        String [] prophCards = activityThatCalled.getExtras().getStringArray("prophArray");


        for (int i = 0; i < 5; ++i) {
            String colorAndTypeOfCard = prophCards[i];
            if (colorAndTypeOfCard.equals("null"))
                break;

           //HHHHHEEEEERREEEEEEEE
            //int cardImageResourceId = getCardImageResourceId(colorAndTypeOfCard);
            //updateProphecyCardImage(cardImageResourceId, i);
        }


    }




    public void prophFinishAction(View view){

        String[] theOrder = new String[5];

        getTheOrderFromViews(theOrder);

        String retS = "";

        for(int i = 0; i < 5; i++){
            retS += theOrder[i];
        }

        prophSelectionString = retS;

        Toast cbToast = Toast.makeText(getApplicationContext(), retS, Toast.LENGTH_LONG);
        cbToast.show();



        backToMainFromProph(view);
    }

    private String[] getTheOrderFromViews(String[] theOrder) {
        TextView[] theViews = getProphDisplayViews();

        for(int i = 0; i < 5; i++){
            theOrder[i] = theViews[i].getText().toString();
        }

        return theOrder;
    }

    public void backToMainFromProph(View view) {

        Intent prophRetIntent = new Intent();

        prophRetIntent.putExtra("prophSelectionString", prophSelectionString);
        setResult(RESULT_OK, prophRetIntent);

        finish();
    }

    private TextView[] getProphDisplayViews(){
        TextView[] theViews = new TextView[5];
        theViews[0] = (TextView) findViewById(R.id.proph_display_1);
        theViews[1] = (TextView) findViewById(R.id.proph_display_2);
        theViews[2] = (TextView) findViewById(R.id.proph_display_3);
        theViews[3] = (TextView) findViewById(R.id.proph_display_4);
        theViews[4] = (TextView) findViewById(R.id.proph_display_5);

        return  theViews;
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
