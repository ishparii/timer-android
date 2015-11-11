package com.example.annezhao.timer.android;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.annezhao.timer.Model.ConcreteTimerModelFacade;
import com.example.annezhao.timer.R;
import com.example.annezhao.timer.Model.TimerModelFacade;
import com.example.annezhao.timer.common.TimerUIUpdateListener;

import java.io.IOException;

public class TimerAdapter extends Activity implements TimerUIUpdateListener{

    private TimerModelFacade modelFacade;

    public void setModel(ConcreteTimerModelFacade modelFacade){
        this.modelFacade = modelFacade;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // inject dependency on view so this adapter receives UI events
        setContentView(R.layout.activity_main);
        // inject dependency on model into this so model receives UI events
        this.setModel(new ConcreteTimerModelFacade());
        // inject dependency on this into model to register for UI updates
        modelFacade.setUIUpdateListener(this);
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

    @Override
    protected void onStart(){
        super.onStart();
        modelFacade.onStart();
    }

    @Override
    public void updateTime(final int timeValue) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final TextView displayTime = (TextView) findViewById(R.id.displayTime);
                displayTime.setText(Integer.toString(timeValue / 10) + Integer.toString(timeValue % 10));
            }
        });
    }

    @Override
    public void updateState(final int stateID) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final TextView currentState = (TextView) findViewById(R.id.CurrentState);
                currentState.setText(getString(stateID));
            }
        });
    }

    @Override
    public void clearInput() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final EditText input = (EditText) findViewById(R.id.inputTime);
                input.setText("");
            }
        });
    }

    // forward event listener methods to the model
    public void onClick(final View view){
        modelFacade.onClick();
    }

    public void playAlarmSound(){
        final Uri defaultRingtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        final MediaPlayer mediaPlayer = new MediaPlayer();
        final Context context = getApplicationContext();

        try{
            mediaPlayer.setDataSource(context,defaultRingtoneUri);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean inputEntered() {
        final EditText editText = (EditText)findViewById(R.id.inputTime);
        String input = editText.getText().toString();
        if (input.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean inputValid(){
        if (inputEntered()) {
            final EditText editText = (EditText)findViewById(R.id.inputTime);
            String input = editText.getText().toString();
            int inputInt = Integer.parseInt(input);

            if(inputInt>0 && inputInt<100){
                return true;
            } else {
                Toast.makeText(this, "Please enter a value between 1 and 99!", Toast.LENGTH_SHORT).show();
                //editText.setError("Please enter a value between 1 and 99!");
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public int getInputRunTIme() {
        if (inputEntered() && inputValid()) {
            final EditText editText = (EditText)findViewById(R.id.inputTime);
            String inputRunTimeString = editText.getText().toString();
            int inputRunTimeInt = Integer.parseInt(inputRunTimeString);
            return inputRunTimeInt;
        }
        else{
            return 0;
        }
    }

}
