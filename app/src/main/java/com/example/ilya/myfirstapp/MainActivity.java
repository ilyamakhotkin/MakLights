package com.example.ilya.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        set_W_BAR();
        set_R_BAR();
        set_G_BAR();
        set_B_BAR();
    }

    // Called when the user taps the ON button
    public void ActionOnButton(View view) {
        Sender sender = new Sender("LED_R_9");
        sender.start();
        sender = new Sender("LED_G_9");
        sender.start();
        sender = new Sender("LED_B_9");
        sender.start();
        SeekBar bar = (SeekBar) findViewById(R.id.LED_W_BAR);
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
        Sender sender = new Sender("LED_R_0");
        sender.start();
        sender = new Sender("LED_G_0");
        sender.start();
        sender = new Sender("LED_B_0");
        sender.start();
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
                    // Send PWM status changes to MSP430
                    Sender sender = new Sender("LED_R_"+ progress );
                    sender.start();
                    sender = new Sender("LED_G_"+ progress );
                    sender.start();
                    sender = new Sender("LED_B_"+ progress );
                    sender.start();

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
               if(fromUser){
                Sender sender = new Sender("LED_R_"+ progress );
                sender.start();
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

    private void set_G_BAR() {
        SeekBar bar = (SeekBar) findViewById(R.id.LED_G_BAR);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    Sender sender = new Sender("LED_G_" + progress);
                    sender.start();
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

    private void set_B_BAR() {
        SeekBar bar = (SeekBar) findViewById(R.id.LED_B_BAR);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    Sender sender = new Sender("LED_B_" + progress);
                    sender.start();
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
}
