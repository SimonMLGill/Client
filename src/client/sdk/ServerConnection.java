package client.sdk;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


/**
 * Created by Simon DonGill on 12-11-2015.
 */
public class ServerConnection {

    /**
     * constructor to this class
     */

    public ServerConnection(){
        this.hostAddress = "http://localhost";
        this.port = 8999;
    }

    /**
     * variables for host adress and port for server
     */

    private String hostAddress;
    private int port;

    /**
     * setters and getters for the aforementioned variables
     * @param hostAddress the hosting address in use
     */

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public int getPort() {
        return port;
    }

    /**
     * method for GET-ting from the restful webservice
     * @param path the path from which content is gotten
     * @return
     */

    public String get(String path){

        String message = "";

        Client client = Client.create();

        WebResource webResource = client.resource(getHostAddress() + ":" + getPort() + "/server/api/" + path);
        ClientResponse response = webResource.type("application/json").get(ClientResponse.class);


        if(response != null){
           message = response.getEntity(String.class);
        }

        return message;
    }

    /**
     * method for POST-ing to the restful webservice
     * @param json the json-formatted string that is posted
     * @param path the path to which the json string is posted
     * @return
     */

    public String post(String json, String path){
        String message = "";

        Client client = Client.create();

        WebResource webResource = client.resource(getHostAddress() + ":" + getPort() + "/server/api/" + path);
        ClientResponse response = webResource.type("application/json").post(ClientResponse.class, json);

        if (response != null){
            message = response.getEntity(String.class);
        }

        return message;
    }

    /**
     * method for deleting from the restful webservice
     * @param path the path from which content is deleted
     * @return
     */

    public String delete(String path){
        String message = "";
        Client client = Client.create();

            WebResource webResource = client.resource(getHostAddress() + ":" + getPort() + "/server/api/" + path);
            ClientResponse response = webResource.type("application/json").delete(ClientResponse.class);

            if (response != null) {
                message = response.getEntity(String.class);
            }

        return message;

    }


    /**
     * method for PUT-ting to the restful webservice
     * @param json the json-formatted string that is put
     * @param path the path to which content is put
     * @return
     */
    public String put(String json, String path){

        String message = "";
        Client client = Client.create();

        WebResource webResource = client.resource(getHostAddress() + ":" + getPort() + "/server/api/" + path);
        ClientResponse response = webResource.type("application/json").put(ClientResponse.class, json);

        if (response != null){
            message = response.getEntity(String.class);
        }

        return message;

    }
}
