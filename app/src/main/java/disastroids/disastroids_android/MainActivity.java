package disastroids.disastroids_android;

/**
 * Created by Daniel on 02/10/2016.
 */


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Orientation.Listener  {

    private float posX;
    private float maxPosX = 1;
    private float minPosX = -1;
    private Button btnShoot;

    private Orientation mOrientation;
    //private AttitudeIndicator mAttitudeIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btnShoot = (Button)findViewById(R.id.btnShoot);
        btnShoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                new SendCommand().execute("{\"type\":\"Fire\",\"x\":0,\"y\":0,\"z\":0}");
            }
        });

        mOrientation = new Orientation(this);
        //mAttitudeIndicator = (AttitudeIndicator) findViewById(R.id.attitude_indicator);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mOrientation.startListening(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mOrientation.stopListening();
    }

    @Override
    public void onOrientationChanged(float pitch, float roll) {
        //mAttitudeIndicator.setAttitude(pitch, roll);
        System.out.println("Pitch:" + pitch + " , Roll:" + roll);

        //Position Code
        posX = posX + roll/1000;
        posX = posX < minPosX ? minPosX : posX;
        posX = posX > maxPosX ? maxPosX : posX;

        new SendCommand().execute("{\"type\":\"Move\",\"x\":" + roll / 1000 + ",\"y\":0,\"z\":0}");



    }

    public void onClick(View v){

    }

    //private Runnable SendMessage = new Runnable() {
    //("\"type\":\"Move\",\"x\":"+0+"\"y\":0,\"z\":0").getBytes();

    //};
}
