package disastroids.disastroids_android;

import android.view.View;

/**
 * Created by Tom on 12-10-2016.
 */

public class ButtonManager implements InputMethod, View.OnClickListener {
    private boolean clicked = false;

    public ButtonManager(){
        System.out.println("Hey");
    }

    @Override
    public void onClick(View v) {
        System.out.println("Clicked!");
        clicked = true;
    }

    public String serialize(){
        if(clicked){
            clicked =false;
            return "{\"Type\": \"Fire\", \"x\": 0, \"y\": 0, \"z\": 0}";
        } else {
            return null;
        }
    }
}
