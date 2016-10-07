package disastroids.disastroids_android;

/**
 * Created by Tom on 6-10-2016.
 */

public class App extends android.app.Application {
    private boolean firstRun = true;
    public App(){
        //only run the first time!
    }

    public boolean isFirstRun(){
        boolean first = firstRun;
        if(first) firstRun = false;
        return first;
    }
}
