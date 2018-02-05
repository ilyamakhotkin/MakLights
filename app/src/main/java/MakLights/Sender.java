package MakLights;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Sender extends Thread{

    private Semaphore semaphore;
    private Semaphore listAccess;
    private OutputStream outputStream;
    private Queue<String> messages;
    private boolean run;
    private String strIdentity;

    Sender(String strIdentity_, OutputStream outputStream_){
        semaphore = new Semaphore(0);
        listAccess = new Semaphore(0);
        listAccess.release();
        strIdentity = strIdentity_;
        outputStream = outputStream_;
        messages = new LinkedList();
        run = true;
        start();
    }

    void send(String command){
        String message = strIdentity.concat(command);
        message = message.concat("\r\n");
        try {
            listAccess.acquire();
            if(messages.add(message))
                semaphore.release();
            listAccess.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void safeStop(){
        run = false;
        semaphore.release();
    }

    public void run() {
        while (run) {
            try{
                semaphore.acquire();
                listAccess.acquire();
                String message = messages.remove();
                listAccess.release();
                System.out.printf("Sending %d bytes: %s\r\n", message.length(), message);
                outputStream.write(message.getBytes(), 0, message.length());
            } catch (IOException e) {
                e.printStackTrace();
                return;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
