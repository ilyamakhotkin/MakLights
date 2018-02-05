package MakLights;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.Semaphore;

public class Communicator extends Thread{
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private Sender sender;
    private Receiver receiver;
    private String strIP;
    private int intPort;
    private String strUser;
    private String strPassword;
    private Semaphore semaphore;

    static Communicator communicator;

    public Communicator(){
        semaphore = new Semaphore(0);
        communicator = this;
    }

    void setIP(String newIP){
        strIP = newIP;
    }

    void setPort(int newPort){
        intPort = newPort;
    }

    void setUser(String newUser){
        strUser = newUser;
    }

    void setPassword(String newPassword){
        strPassword = newPassword;
    }

    void send(String command){
        if(sender != null && sender.isAlive()){
            sender.send(command);
        }
    }

    public void restart(){
        try {
            if(socket != null)
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        semaphore.release();
    }

    public void run(){
        try {
            while(true) {
                semaphore.acquire();
                if (sender != null) {
                    sender.safeStop();
                    sender.join(100);
                }
                if (receiver != null) {
                    receiver.safeStop();
                    receiver.join(100);
                }
                if (inputStream != null)
                    inputStream.close();
                if (outputStream != null)
                    outputStream.close();
                if (socket != null && socket.isConnected())
                    socket.close();

                socket = new Socket();
                InetSocketAddress address = new InetSocketAddress(strIP, intPort);
                socket.connect(address, 10);
                if (socket.isConnected()) {
                    outputStream = socket.getOutputStream();
                    inputStream = socket.getInputStream();

//                receiver = new Receiver(inputStream);
//                receiver.start();

                    String strIdentity = strUser.concat(";");
                    strIdentity = strIdentity.concat(strPassword);
                    strIdentity = strIdentity.concat(";");
                    sender = new Sender(strIdentity, outputStream);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
