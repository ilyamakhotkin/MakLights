package com.example.ilya.myfirstapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private Communicator communicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("MainActivity onCreate");
        setContentView(R.layout.activity_main);
        set_W_BAR();
        set_R_BAR();
        set_G_BAR();
        set_B_BAR();
        // Move the color bars
        SeekBar bar;
        bar = findViewById(R.id.LED_R_BAR);
        bar.setProgress(LEDController.getRed());
        bar = (SeekBar) findViewById(R.id.LED_G_BAR);
        bar.setProgress(LEDController.getGreen());
        bar = (SeekBar) findViewById(R.id.LED_B_BAR);
        bar.setProgress(LEDController.getBlue());
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Ilya Is Awsome");

        communicator = new Communicator();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        communicator.setUser(sharedPref.getString("user_name", "noname"));
        communicator.setPassword(sharedPref.getString("password", "123456"));
        communicator.setIP(sharedPref.getString("ip", "127.0.0.1"));
        String strPort = sharedPref.getString("port", "0");
        int iPort = Integer.parseInt(strPort);
        communicator.setPort(iPort);
        communicator.start();
        communicator.restart();

    }

    @Override
    protected void onPause(){
        super.onPause();
        System.out.println("Pausing main activity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("Resuming main activity");
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
            Intent i2 = new Intent(this,SettingsActivity.class);
            startActivity(i2);
            return true;
        }

        if (id == R.id.action_alarm) {
            Intent i2 = new Intent(this,Alarm.class);
            startActivity(i2);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        return super.onMenuOpened(featureId, menu);
    }

    // Called when the user taps the ON button
    public void ActionOnButton(View view) {
        LEDController.setRed(LEDController.MAX_INTENSITY);
        LEDController.setGreen(LEDController.MAX_INTENSITY);
        LEDController.setBlue(LEDController.MAX_INTENSITY);

        SeekBar bar;
        bar = (SeekBar) findViewById(R.id.LED_W_BAR);
        bar.setProgress(9);
        bar = (SeekBar) findViewById(R.id.LED_R_BAR);
        bar.setProgress(9);
        bar = (SeekBar) findViewById(R.id.LED_G_BAR);
        bar.setProgress(9);
        bar = (SeekBar) findViewById(R.id.LED_B_BAR);
        bar.setProgress(9);
    }

    // Called when the user taps the OFF button
    public void ActionOffButton(View view) {
        LEDController.setRed  (LEDController.MIN_INTENSITY);
        LEDController.setGreen(LEDController.MIN_INTENSITY);
        LEDController.setBlue (LEDController.MIN_INTENSITY);

        SeekBar bar;
        bar = (SeekBar) findViewById(R.id.LED_W_BAR);
        bar.setProgress(0);
        bar = (SeekBar) findViewById(R.id.LED_R_BAR);
        bar.setProgress(0);
        bar = (SeekBar) findViewById(R.id.LED_G_BAR);
        bar.setProgress(0);
        bar = (SeekBar) findViewById(R.id.LED_B_BAR);
        bar.setProgress(0);
    }

    private void set_W_BAR() {
        SeekBar bar = (SeekBar) findViewById(R.id.LED_W_BAR);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    LEDController.setRed  (progress);
                    LEDController.setGreen(progress);
                    LEDController.setBlue (progress);

                    // Move the color bars
                    SeekBar bar;
                    bar = (SeekBar) findViewById(R.id.LED_R_BAR);
                    bar.setProgress(progress);
                    bar = (SeekBar) findViewById(R.id.LED_G_BAR);
                    bar.setProgress(progress);
                    bar = (SeekBar) findViewById(R.id.LED_B_BAR);
                    bar.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void set_R_BAR() {
        SeekBar bar = (SeekBar) findViewById(R.id.LED_R_BAR);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
           @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               if(fromUser)
                   LEDController.setRed  (progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void set_G_BAR() {
        SeekBar bar = (SeekBar) findViewById(R.id.LED_G_BAR);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser)
                    LEDController.setGreen(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void set_B_BAR() {
        SeekBar bar = (SeekBar) findViewById(R.id.LED_B_BAR);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser)
                    LEDController.setBlue (progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
