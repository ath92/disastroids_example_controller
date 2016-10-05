package disastroids.disastroids_android;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Remove_TestMove2 extends Activity implements View.OnClickListener, SensorEventListener {

    Button button_setStartPosition;

    static final float NS2S = 1.0f / 1000000000.0f;
    float[] last_values = null;
    float[] velocity = null;
    float[] position = null;
    long last_timestamp = 0;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_setStartPosition = (Button)findViewById(R.id.button_setStartPosition);
        button_setStartPosition.setOnClickListener(this);

        // TODO: Tutorial from http://www.techrepublic.com/blog/software-engineer/a-quick-tutorial-on-coding-androids-accelerometer/
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

        // TODO: Copied from http://stackoverflow.com/questions/11068671/read-x-y-z-coordinates-of-android-phone-using-accelerometer
        public void onSensorChanged (SensorEvent event){
            if (last_values != null) {
                float dt = (event.timestamp - last_timestamp) * NS2S;

                for (int index = 0; index < 3; ++index) {
                    velocity[index] += (event.values[index] + last_values[index]) / 2 * dt;
                    position[index] += velocity[index] * dt;
                }
            } else {
                last_values = new float[3];
                velocity = new float[3];
                position = new float[3];
                velocity[0] = velocity[1] = velocity[2] = 0f;
                position[0] = position[1] = position[2] = 0f;
            }
            System.arraycopy(event.values, 0, last_values, 0, 3);
            last_timestamp = event.timestamp;
            System.out.println("Position: " + position[0] + " || " + position[1] + " || " + position[2]);
        }

    @Override
    public void onClick(View v) {
        if(v == button_setStartPosition){
            System.out.println("Button clicked");

        }
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Required method when implementing SensorListener
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
