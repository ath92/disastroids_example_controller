package disastroids.disastroids_android;

/**
 * Created by Daniel on 02/10/2016.
 */

import android.app.ActionBar;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.audiofx.BassBoost;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    private Button btnShoot;
    private ImageButton btnSettings;
    private NetworkManager networkManager;
    private InputManager inputManager = InputManager.getInstance();

    private Orientation orientation;

    private ButtonManager buttonManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title and notification bar
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.main);

        //btnSettings = (ImageButton)findViewById(R.id.btnSettings);
        //btnSettings.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        //    }
        //});

        networkManager.getInstance().start();;
        setupInputMethods();
        buttonManager = new ButtonManager();
        inputManager.addInputMethod(buttonManager);
        btnShoot = (Button)findViewById(R.id.btnShoot);
        btnShoot.setOnClickListener(buttonManager);

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


    private void setupInputMethods(){
        //This function should only be run once. We use a function in our custom application class to do this.
        App app = (App)getApplication();
        if(app.isFirstRun()) {
            System.out.println("kjansdjknasd");
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