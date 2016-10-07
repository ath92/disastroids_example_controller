package disastroids.disastroids_android;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 02/10/2016.
 */

public class InputManager {


    private static InputManager inputManager;

    private ArrayList<InputMethod> inputMethods = new ArrayList<InputMethod>();

    private InputManager(){
    }

    public static InputManager getInstance(){
        if (inputManager == null) {
            inputManager = new InputManager();
        }
        return inputManager;
    }

    public void addInputMethod(InputMethod inputMethod){
        inputMethods.add(inputMethod);
    }

    public boolean hasInputMethod(InputMethod inputMethod){
        for(InputMethod method: inputMethods){
            if(method == inputMethod) {
                return true;
            }
        }
        return false;
    }

    public void removeInputMethod(InputMethod inputMethod){
        inputMethods.remove(inputMethod);
    }

    public String serializeAll() {
        String total = "";
        //System.out.println(String.valueOf(inputMethods.size()));
        for(InputMethod method: inputMethods){
            total += method.serialize();
        }

        return total;
    }
}
