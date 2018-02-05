package com.example.ilya.myfirstapp;

class LEDController {

    static final int MAX_INTENSITY = 9;
    static final int MIN_INTENSITY = 0;

    LEDController(){
    }
    static boolean getConnectionStatus(){
        return true;
    }
    static int getRed(){
        return 0;
    }
    static int getGreen(){
        return 0;
    }
    static int getBlue(){
        return 0;
    }
    static void setRed(int i){
        if(i < MIN_INTENSITY || i > MAX_INTENSITY)
            return;
        String message = "SET_LED_R;";
        message = message.concat(String.valueOf(i));
        Communicator.communicator.send(message);
    }

    static void setGreen(int i){
        if(i < MIN_INTENSITY || i > MAX_INTENSITY)
            return;
        String message = "SET_LED_G;";
        message = message.concat(String.valueOf(i));
        Communicator.communicator.send(message);
    }

    static void setBlue(int i){
        if(i < MIN_INTENSITY || i > MAX_INTENSITY)
            return;
        String message = "SET_LED_B;";
        message = message.concat(String.valueOf(i));
        Communicator.communicator.send(message);
    }
}
