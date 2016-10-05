package disastroids.disastroids_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import disastroids.disastroids_android.model.Model;

/**
 * Created by Daniel on 05/10/2016.
 */
public class SettingsActivity extends Activity {

    Button btnSave;
    TextView txtInputIPAddress;
    TextView txtInputPort;
    private Model model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        txtInputIPAddress = (TextView)findViewById(R.id.txtInputIPAddress);
        txtInputPort = (TextView)findViewById(R.id.txtInputPort);

        if(model.getIPAddress() == null) { txtInputIPAddress.setText("127.0.0.1"); } else { txtInputIPAddress.setText(model.getIPAddress()); }
        if(model.getPort() == 0) { txtInputPort.setText("2048"); } else { txtInputPort.setText(String.valueOf(model.getPort())); }

        btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setIPAddress(txtInputIPAddress.getText().toString());
                model.setPort(Integer.parseInt(txtInputPort.getText().toString()));
                Toast toast = Toast.makeText(getApplicationContext(), "Saved! IP Address: " + model.getIPAddress() + " and Port: " + model.getPort(), Toast.LENGTH_SHORT);
                toast.show();
                startActivity(new Intent(SettingsActivity.this, MainActivity.class));
            }
        });


    }

}
