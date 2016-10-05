package disastroids.disastroids_android.model;

/**
 * Created by Daniel on 02/10/2016.
 */
public class Model {

    private String IPAddress; // for setting which IP Address to sent the packages to
    private String Port; // for setting which Port to sent the packages to

    public Model(){

    }

    public void setIPAddress(String IPAddress){
        this.IPAddress = IPAddress;
    }

    public String getIPAddress(){
        return IPAddress;
    }

    public void setPort(String Port){
        this.Port = Port;
    }

    public String getPort(){
        return Port;
    }
}
