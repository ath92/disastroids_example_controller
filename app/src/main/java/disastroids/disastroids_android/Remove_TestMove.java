package disastroids.disastroids_android;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * TODO: Copied from http://www.techrepublic.com/blog/software-engineer/a-quick-tutorial-on-coding-androids-accelerometer/
 */
public class Remove_TestMove extends Activity implements SensorEventListener {

        private float mLastX, mLastY, mLastZ;
        private boolean mInitialized;
        private SensorManager mSensorManager;
        private Sensor mAccelerometer;
        private final float NOISE = (float) 1.0;
        private double currentPosition[] = new double[3];
        private double currentSpeed[] = new double[3];
        private double gravity[] = new double[3];
        private double linear_acceleration[] = new double[3];

        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);
            mInitialized = false;
            mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            mSensorManager.registerListener(this, mAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);
        }

        protected void onResume() {
            super.onResume();
            mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }

        protected void onPause() {
            super.onPause();
            mSensorManager.unregisterListener(this);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // can be safely ignored for this demo
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
                // In this example, alpha is calculated as t / (t + dT),
                // where t is the low-pass filter's time-constant and
                // dT is the event delivery rate.

                final double alpha = 0.8;
                // Isolate the force of gravity with the low-pass filter.
                gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
                gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
                gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

                // Remove the gravity contribution with the high-pass filter.
                linear_acceleration[0] = event.values[0] - gravity[0];
                linear_acceleration[1] = event.values[1] - gravity[1];
                linear_acceleration[2] = event.values[2] - gravity[2];

            TextView txtXPos= (TextView)findViewById(R.id.txtXPos);
            TextView txtYPos= (TextView)findViewById(R.id.txtYPos);
            TextView txtZPos= (TextView)findViewById(R.id.txtZPos);
            TextView txtPlayerXPos = (TextView)findViewById(R.id.txtPlayerXPos);
            TextView txtPlayerYPos= (TextView)findViewById(R.id.txtPlayerYPos);
            TextView txtPlayerZPos= (TextView)findViewById(R.id.txtPlayerZPos);

            ImageView iv = (ImageView)findViewById(R.id.image);
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            if (!mInitialized) {
                mLastX = x;
                mLastY = y;
                mLastZ = z;
                txtXPos.setText("0.0");
                txtYPos.setText("0.0");
                txtZPos.setText("0.0");
                mInitialized = true;
            } else {
                float deltaX = mLastX - x;
                float deltaY = mLastY - y;
                float deltaZ = mLastZ - z;
                if ((deltaX > 0.0 && deltaX < NOISE) || (deltaX < 0.0 && deltaX > -NOISE)) deltaX = (float)0.0;
                //if (deltaY < NOISE) deltaY = (float)0.0;
                //if (deltaZ < NOISE) deltaZ = (float)0.0;
                mLastX = x;
                mLastY = y;
                mLastZ = z;
                System.out.println("DeltaX:" + deltaX + ", x:" + x + ", mLastX:" + mLastX);
                txtXPos.setText("x:" + linear_acceleration[0]);
                txtYPos.setText("y:" + linear_acceleration[1]);
                txtZPos.setText("z:" + linear_acceleration[2]);
                currentSpeed[0] = currentSpeed[0] + linear_acceleration[0];
                currentSpeed[1] = currentSpeed[1] + linear_acceleration[1];
                currentSpeed[2] = currentSpeed[2] + linear_acceleration[2];
                //currentPosition[0] = currentPosition[0] + currentSpeed[0];
                //currentPosition[1] = currentPosition[1] + currentSpeed[1];
                //currentPosition[2] = currentPosition[2] + currentSpeed[2];
                txtPlayerXPos.setText("x:" + currentSpeed[0]);
                txtPlayerYPos.setText("y:" + currentSpeed[1]);
                txtPlayerZPos.setText("z:" + currentSpeed[2]);
                //iv.setVisibility(View.VISIBLE);
                if (deltaX > deltaY) {
                    //iv.setImageResource(R.drawable.horizontal);
                } else if (deltaY > deltaX) {
                    //iv.setImageResource(R.drawable.vertical);
                } else {
                    //iv.setVisibility(View.INVISIBLE);
                }
            }
        }
    }