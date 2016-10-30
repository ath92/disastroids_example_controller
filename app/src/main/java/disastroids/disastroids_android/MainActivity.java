package disastroids.disastroids_android;

/**
 * This example application shows how to use the GameControllerLibrary.
 * It uses a .AAR version of the library, which contains all the necessary functions.
 * This version only uses the InputMethods already included in the library.
 * If a developer would want to create their own methods, it would be relatively simple to create a new Class
 * that implements the InputMethod interface. An instance of this new InputMethod could then be added
 * to the InputManager like any other InputMehod. Take a look at the rest of the code, particularly
 * the things inside the onCreate method for MainActivity.
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
    //setup buttons
    private Button btnShoot;
    private ImageButton btnSettings;
    //some variables that manage the inputs.
    private NetworkManager networkManager = NetworkManager.getInstance();
    private InputManager inputManager;
    //some variables for the actual inputs.
    private OrientationInput orientation;

    private ButtonInput buttonInput;
    //the orientation input requires some extra things to make it work.
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

    //Handle the little settings button in the top right of the controller.
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

    //Handle the little settings button in the top right of the controller.
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
