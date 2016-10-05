package disastroids.disastroids_android;

/**
 * Created by Daniel on 02/10/2016.
 */

import android.content.Intent;
import android.media.audiofx.BassBoost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Orientation.Listener  {

    private float posX;
    private float maxPosX = 1;
    private float minPosX = -1;
    private Button btnShoot;
    private Button btnSettings;

    private Orientation mOrientation;
    //private AttitudeIndicator mAttitudeIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btnShoot = (Button)findViewById(R.id.btnShoot);
        btnShoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SendCommand().execute("{\"type\":\"Fire\",\"x\":0,\"y\":0,\"z\":0}");
            }
        });

        btnSettings = (Button)findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
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

        // TODO: Store the rotation in every OrientationChange, then send it every intervall instead of sending it everytime

        // TODO: Limit -90 (move right) to 90 (move left)
        // TODO: Unity should be dividing it (roll)
        new SendCommand().execute("{\"type\":\"Move\",\"x\":" + roll / -1000 + ",\"y\":0,\"z\":0}");
        new SendCommand().execute("{\"type\":\"Rotate\",\"x\":0,\"y\":0,\"z\":" + roll + "}");
    }

    public void onClick(View v){

    }

    //private Runnable SendMessage = new Runnable() {
    //("\"type\":\"Move\",\"x\":"+0+"\"y\":0,\"z\":0").getBytes();

    //};
}


// GestureHandler which handles all the sensor input and