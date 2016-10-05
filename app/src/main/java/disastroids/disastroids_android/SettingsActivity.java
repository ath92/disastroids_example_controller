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
    Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        //adding the model
        model = ((DisastroidsApplication) this.getApplication()).getModel();

        txtInputIPAddress = (TextView)findViewById(R.id.txtInputIPAddress);
        txtInputPort = (TextView)findViewById(R.id.txtInputPort);

        txtInputIPAddress.setText(model.getIPAddress());
        txtInputPort.setText(model.getPort());

        btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setIPAddress(txtInputIPAddress.getText().toString());
                model.setPort(txtInputPort.getText().toString());
                Toast toast = Toast.makeText(getApplicationContext(), "Saved! IP Address: " + model.getIPAddress() + " and Port: " + model.getPort(), Toast.LENGTH_SHORT);
                toast.show();
                startActivity(new Intent(SettingsActivity.this, MainActivity.class));
            }
        });


    }

}
