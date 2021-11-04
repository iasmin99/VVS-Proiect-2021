import exceptions.InvalidServerException;
import org.junit.Test;
import webserver.WebServerController;
import webserver.WebServer;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import static org.junit.Assert.assertTrue;

public class WebServerControllerTest {

    private int port8080 = 8080;
    private String websiteFilePath = "src/main/java/website";
    private ArrayList<String> status = new ArrayList<String>(Arrays.asList("Stopped","Running","Maintenance"));


    @Test
    public void testNewServerSocket_OK() throws InvalidServerException {

        WebServer webServer = new WebServer(port8080,websiteFilePath,status.get(0));

        webServer.setServerStatus(status.get(1));
        try {
            ServerSocket socket = WebServerController.newServerSocket(port8080);

            assertTrue(socket.isBound());

            socket.close();
        }catch(BindException e) {
            Assertions.fail(e);
        }catch(IOException b) {
            Assertions.fail(b);
        }

    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewServerSocketPort_NOT_OK() throws InvalidServerException, BindException {
        WebServer webServer = new WebServer(port8080, websiteFilePath, status.get(0));
        webServer.setServerStatus(status.get(1));
        ServerSocket socket = WebServerController.newServerSocket(85739); //wrong port
    }

    @Test(expected = BindException.class)
    public void testNewServerSocketPort_NOT_AVAILABLE() throws BindException {

        ServerSocket serverSocket1 = WebServerController.newServerSocket(port8080);
        ServerSocket serverSocket2 = WebServerController.newServerSocket(port8080);

    }
    @Test
    public void closeServerSocket_OK(){

        try{
            ServerSocket serverSocket = WebServerController.newServerSocket(port8080);
            WebServerController.closeServerSocket(serverSocket);
            assertTrue(serverSocket.isClosed());
        }catch(BindException e){
            Assertions.fail(e);
        }

    }


    @Test(expected = NullPointerException.class)
    public void closeServerSocket_NOT_OK(){

        WebServerController.closeServerSocket(null);
    }





    @Test
    public void testNewClientSocket_OK() throws BindException {

        ServerSocket serverSocket = WebServerController.newServerSocket(port8080);

        try{
            Socket clientSocket = WebServerController.newClientSocket(serverSocket);

        }catch(Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testAcceptSocket_OK() {
        try {
            ServerSocket serverSocket = WebServerController.newServerSocket(port8080);
            Socket clientSocket = WebServerController.acceptSocket(serverSocket);

            assertTrue(clientSocket.isBound());
            assertTrue(serverSocket.isBound());

            serverSocket.close();
            clientSocket.close();
        }catch(BindException e) {
            Assertions.fail(e);
        }catch(IOException e) {
            Assertions.fail(e);
        }catch(Exception e) {
            Assertions.fail(e);
        }
    }

    @Test(expected = SocketException.class)
    public void testAcceptSocket_NOT_OK() throws Exception {

        ServerSocket serverSocket = WebServerController.newServerSocket(port8080);
        WebServerController.closeServerSocket(serverSocket);
        Socket clientSocket = WebServerController.acceptSocket(serverSocket);
    }

    @Test
    public void testCloseClientSocket_OK() throws BindException {

        try{

            ServerSocket serverSocket = WebServerController.newServerSocket(port8080);
            Socket clientSocket = WebServerController.newClientSocket(serverSocket);

            WebServerController.closeClientSocket(clientSocket);
            assertTrue(clientSocket.isClosed());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test(expected = NullPointerException.class)
    public void testCloseClientSocket_NOT_OK(){

        WebServerController.closeClientSocket(null);

    }


    @Test
    public void clientHandler_OK() throws InvalidServerException, IOException {

        WebServer webServer = new WebServer(port8080,websiteFilePath,status.get(0));
        WebServerController webServerController = new WebServerController(webServer);

        ServerSocket serverSocket = new ServerSocket(port8080);

        Socket clientSocket = serverSocket.accept();

        webServerController.clientHandler(clientSocket);

    }

    @Test
    public void clientHandler_NOT_OK() throws InvalidServerException {
        WebServer webServer = new WebServer(port8080,websiteFilePath,status.get(0));
        WebServerController webServerController = new WebServerController(webServer);

        webServerController.clientHandler(null);

    }

    @Test
    public void requestHandlerServerRunningTest() throws InvalidServerException {

        WebServer webserver = new WebServer(port8080,websiteFilePath,status.get(0));
        webserver.setServerStatus(status.get(1));

        WebServerController firstWebServerController = new WebServerController(webserver);

        firstWebServerController.requestHandler();

    }

    @Test
    public void requestHandlerServerInMaintenanceTest() throws InvalidServerException {

        WebServer webserver = new WebServer(port8080,websiteFilePath,status.get(0));
        webserver.setServerStatus(status.get(2));
        WebServerController firstWebServerController = new WebServerController(webserver);
        firstWebServerController.requestHandler();
    }

    @Test
    public void requestHandlerServerStoppedTest() throws InvalidServerException {

        WebServer firstWebServer = new WebServer(port8080,websiteFilePath,status.get(0));

        WebServerController firstWebServerController = new WebServerController(firstWebServer);

        firstWebServerController.requestHandler();


    }

}