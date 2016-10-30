package disastroids.disastroids_android;

import android.app.Activity;
import android.content.Intent;
import android.net.Network;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The settingsactivity allows the user to set up the connection to the server. Its usability is limited,
 * but it works well enough for a demo application. This is a fairly simple activity, that just has some text fields
 * that can update some variables within the networkmanager.
 */

public class SettingsActivity extends Activity {

    Button btnSave;
    TextView txtInputIPAddress;
    TextView txtInputPort;
    private NetworkManager networkManager = NetworkManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        txtInputIPAddress = (TextView)findViewById(R.id.txtInputIPAddress);
        txtInputPort = (TextView)findViewById(R.id.txtInputPort);

        if(networkManager.getHost() == null) { txtInputIPAddress.setText("127.0.0.1"); } else { txtInputIPAddress.setText(networkManager.getHost()); }
        if(networkManager.getPort() == 0) { txtInputPort.setText("2048"); } else { txtInputPort.setText(String.valueOf(networkManager.getPort())); }

        btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                networkManager.setConnection(txtInputIPAddress.getText().toString(), Integer.parseInt(txtInputPort.getText().toString()));
                Toast toast = Toast.makeText(getApplicationContext(), "Saved! IP Address: " + networkManager.getHost() + " and Port: " + networkManager.getPort(), Toast.LENGTH_SHORT);
                toast.show();
                startActivity(new Intent(SettingsActivity.this, MainActivity.class));
            }
        });


    }

}
