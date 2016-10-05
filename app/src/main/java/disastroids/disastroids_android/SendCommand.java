package disastroids.disastroids_android;

import android.os.AsyncTask;
import android.os.Bundle;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import disastroids.disastroids_android.model.Model;

/**
 * This is the class responsible for sending UDP communication via JSON
 */
public class SendCommand extends AsyncTask<String,Void,Void> {

        private Model model = Model.getInstance();

        protected Void doInBackground(String... msg){

                try {
                    String host;
                    int port;

                    if(model.getIPAddress() == null) { host = "127.0.0.1"; } else { host = model.getIPAddress(); }
                    if(model.getPort() == 0) { port = 2048; } else { port = model.getPort(); }

                    byte[] message = msg[0].getBytes();
                    //byte[] message = ("\"type\":\"rotate\",\"roll\":"+roll).getBytes();

                    // Get the internet address of the specified host
                    InetAddress address = InetAddress.getByName(host);

                    // Initialize a datagram packet with data and address
                    DatagramPacket packet = new DatagramPacket(message, message.length,
                            address, port);

                    // Create a datagram socket, send the packet through it, close it.
                    DatagramSocket dsocket = new DatagramSocket();
                    dsocket.send(packet);
                    dsocket.close();
                    System.out.println("Sent: " + message);
                } catch (Exception e) {
                    System.err.println(e);
                }
                return null;
            }
}
