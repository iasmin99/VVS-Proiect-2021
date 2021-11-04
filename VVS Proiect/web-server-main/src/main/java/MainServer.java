import exceptions.InvalidServerException;
import webserver.WebServerController;
import webserver.WebServer;

import java.util.ArrayList;

public class MainServer {

    public static void main(String[] args) throws InvalidServerException {

        int port = 8080;
        String websiteFilesPath = "src/main/java/website";
        String serverStatus = "Stopped";

        WebServer webServer = new WebServer(port,websiteFilesPath,serverStatus);
        WebServerController webServerController = new WebServerController(webServer);

        ArrayList<String> level1 = new ArrayList<String>();
        ArrayList<String> level2 = new ArrayList<String>();
        ArrayList<String> level3 = new ArrayList<String>();

        webServer.addPageLevel(level1);
        webServer.addPageLevel(level2);
        webServer.addPageLevel(level3);

        webServer.addPageAtLevel("page_1.html",0); // 0 means level1
        webServer.addPageAtLevel("page_2.html",0); // 0 means level1
        webServer.addPageAtLevel("page_3.html",0); // 0 means level1
        webServer.addPageAtLevel("page_4.html",1); // 1 means level2
        webServer.addPageAtLevel("page_5.html",2); // 2 means level3
        webServer.addPageAtLevel("page_Colored.html",0); // 0 means level1
        webServer.addPageAtLevel("page_Colored.css",0); // 0 means level1


        webServer.setServerStatus("Running");

        while (true) {
            webServerController.requestHandler();
        }

    }
}
