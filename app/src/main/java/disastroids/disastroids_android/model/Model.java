package disastroids.disastroids_android.model;

/**
 * Created by Daniel on 02/10/2016.
 */
public class Model {

    private String IPAddress = "127.0.0.1"; // for setting which IP Address to sent the packages to
    private int Port = 2048; // for setting which Port to sent the packages to
    private static Model model;

    private Model(){

    }

    public static Model getInstance(){
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public void setIPAddress(String IPAddress){
        this.IPAddress = IPAddress;
    }

    public String getIPAddress(){
        return IPAddress;
    }

    public void setPort(int Port){
        this.Port = Port;
    }

    public int getPort(){
        return Port;
    }
}
