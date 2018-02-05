package com.example.ilya.myfirstapp;

import java.io.IOException;
import java.io.InputStream;

public class Receiver extends Thread{
    private InputStream inputStream;
    private boolean run;
    public Receiver(InputStream inputStream){
        this.inputStream = inputStream;
        run = true;
        start();
    }

    public void safeStop(){
        run = false;
    }

    public void run(){
        byte[] data = new byte[100];
        int available;
        while(run) {
            try {
                available = inputStream.available();
                if (available > 0) {
                    available = inputStream.read(data);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
