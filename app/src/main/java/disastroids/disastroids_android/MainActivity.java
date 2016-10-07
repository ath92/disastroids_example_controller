package disastroids.disastroids_android;

/**
 * Created by Daniel on 02/10/2016.
 */

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.audiofx.BassBoost;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    private Button btnShoot;
    private Button btnSettings;

    //private AttitudeIndicator mAttitudeIndicator;

    private NetworkManager networkManager;
    private InputManager inputManager = InputManager.getInstance();

    private Orientation orientation;

    private boolean inputMethodsSetup = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btnShoot = (Button)findViewById(R.id.btnShoot);
        btnShoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new SendCommand().execute("{\"type\":\"Fire\",\"x\":0,\"y\":0,\"z\":0}");
            }
        });

        btnSettings = (Button)findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });

        //mAttitudeIndicator = (AttitudeIndicator) findViewById(R.id.attitude_indicator);

        networkManager.getInstance().start();;
        setupInputMethods();
    }

    private void setupInputMethods(){
        //This function should only be run once. We use a function in our custom application class to do this.
        App app = (App)getApplication();
        if(app.isFirstRun()) {
            orientation = new Orientation(this);
            inputManager.addInputMethod(orientation);
            orientation.startListening();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        ///orientation.stopListening();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        inputManager.removeInputMethod(orientation);
    }

    public void onClick(View v){

    }
}


// GestureHandler which handles all the sensor input and