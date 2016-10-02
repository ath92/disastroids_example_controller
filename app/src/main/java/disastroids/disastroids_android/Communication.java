package disastroids.disastroids_android;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by Daniel on 02/10/2016.
 */
public class Communication {

    private static final String host = null;
    private int port;
    String str=null;
    /** Called when the activity is first created. */
    byte[] send_data = new byte[1024];
    byte[] receiveData = new byte[1024];
    String modifiedSentence;

    public void client() throws IOException {
        DatagramSocket client_socket = new DatagramSocket(2362);
        InetAddress IPAddress =  InetAddress.getByName("182.168.43.208");

        //while (true)
        // {
        send_data = str.getBytes();
        //System.out.println("Type Something (q or Q to quit): ");

        DatagramPacket send_packet = new DatagramPacket(send_data,str.length(), IPAddress, 2362);
        client_socket.send(send_packet);
        //chandra
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        client_socket.receive(receivePacket);
        modifiedSentence = new String(receivePacket.getData());
        //System.out.println("FROM SERVER:" + modifiedSentence);
        if(modifiedSentence.charAt(2)=='%')
            //txt5.setText(modifiedSentence.substring(0, 3));
        //else
            //txt1.setText(modifiedSentence);
        modifiedSentence=null;
        client_socket.close();

        // }

    }
}
