package com.example.ilya.myfirstapp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.Semaphore;


public class Sender extends Thread{
    private byte[] buffer;
    private int size;
    static Semaphore mutex;


    public Sender(String message){
        buffer=message.getBytes();
        size=message.length();
        mutex = new Semaphore(0);
    }

    public void run(){
        try {
            Socket clientSocket = new Socket();
            InetSocketAddress address = new InetSocketAddress("192.168.1.111", 233);
            clientSocket.connect(address);
            OutputStream outputStream = clientSocket.getOutputStream();
            outputStream.write(buffer, 0, size);
            outputStream.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
