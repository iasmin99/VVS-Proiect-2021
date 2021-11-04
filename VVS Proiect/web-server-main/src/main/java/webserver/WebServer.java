package webserver;

import exceptions.InvalidPortException;
import exceptions.InvalidServerException;
import exceptions.InvalidStatusException;
import exceptions.InvalidWebsitePathException;

import java.util.ArrayList;

public class WebServer {

    private int port;
    private String websiteFilesPath;
    private String serverStatus;
    private String request;


    public ArrayList<ArrayList<String>> pagesList;

    public WebServer(){}

    public WebServer(int port, String websiteFilesPath, String serverStatus) throws InvalidServerException {
        validateWebServerInputs(port,websiteFilesPath,serverStatus);
        this.port = port;
        this.websiteFilesPath = websiteFilesPath;
        this.serverStatus = serverStatus;
        this.request = "";
        pagesList = new ArrayList<ArrayList<String>>();
    }

    public int getPort() {

        return port;
    }

    public void setPort(int port) {

        this.port = port;
    }

    public String getServerStatus() {

        return serverStatus;
    }

    public void setServerStatus(String serverStatus) {
        this.serverStatus = serverStatus;
    }

    public String getWebsiteFilesPath() {
        return websiteFilesPath;
    }

    public void setWebsiteFilesPath(String websiteFilesPath) {
        this.websiteFilesPath = websiteFilesPath;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public void validateWebServerInputs(int port, String websiteFilesPath, String serverStatus) throws InvalidServerException {

        if(port != 8080){
            throw new InvalidPortException();
        }

        if(!serverStatus.equals("Stopped")){
            throw new InvalidStatusException();
        }

        if(!websiteFilesPath.equals("src/main/java/website")){
            throw new InvalidWebsitePathException();
        }

    }

    public void addPageLevel(ArrayList<String> newLevel){
        pagesList.add(newLevel);
    }
    public void addPageAtLevel(String newPage,int level){
        pagesList.get(level).add(newPage);
    }
}
