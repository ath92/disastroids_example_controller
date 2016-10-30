package disastroids.disastroids_android;

/**
 * Created by Daniel on 02/10/2016.
 */

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private Button btnShoot;
    private ImageButton btnSettings;
    private NetworkManager networkManager = NetworkManager.getInstance();
    private InputManager inputManager;

    private OrientationInput orientation;

    private ButtonInput buttonInput;

    private GestureDetectorCompat gestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.main);


        //Create new inputmanager for this activity
        inputManager = new InputManager();

        //Handle the fire button
        buttonInput = new ButtonInput();
        inputManager.addInputMethod(buttonInput);
        btnShoot = (Button)findViewById(R.id.btnShoot);
        btnShoot.setOnClickListener(buttonInput);

        //Handle the swipe button
        SwipeInput swipeInput = new SwipeInput();
        gestureDetector = new GestureDetectorCompat(this, swipeInput);
        Button btnTouch = (Button)findViewById(R.id.btnTouch);
        btnTouch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //System.out.println("hey");
                gestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });
        inputManager.addInputMethod(swipeInput);

        //handle orientation changes
        orientation = new OrientationInput(this);
        inputManager.addInputMethod(orientation);
        orientation.startListening();


        //Have the networkmanager listen to the current inputmanager and start it.
        networkManager.setInputManager(inputManager);
        networkManager.start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}


// GestureHandler which handles all the sensor input and