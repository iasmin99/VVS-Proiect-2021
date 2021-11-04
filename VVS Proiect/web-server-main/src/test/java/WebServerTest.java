import exceptions.InvalidPortException;
import exceptions.InvalidServerException;
import exceptions.InvalidStatusException;
import exceptions.InvalidWebsitePathException;

import org.junit.BeforeClass;
import org.junit.Test;
import webserver.WebServer;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class WebServerTest {

    static WebServer webServer;

    @BeforeClass
    public static void before() throws InvalidServerException {
        try{
            webServer = new WebServer(8080,"src/main/java/website","Stopped");
        }catch(InvalidServerException e){
            System.out.println("Wrong Inputs for the Server");
        }
    }

    @Test
    public void checkWebServerNotNull(){

        assertNotNull(webServer);
    }

    @Test
    public void testPort_OK() throws InvalidServerException {
        webServer = new WebServer(8080,"src/main/java/website","Stopped");
    }

    @Test(expected= InvalidPortException.class)
    public void testPort_NOT_Ok() throws InvalidServerException {
        webServer = new WebServer(8081,"src/main/java/website","Stopped");
    }

    @Test
    public void testGetAndSetPort_OK(){
        webServer = new WebServer();
        webServer.setPort(1000);
        assertEquals(1000,webServer.getPort());
    }

    @Test
    public void testServerStatus_OK() throws InvalidServerException {
        webServer = new WebServer(8080,"src/main/java/website","Stopped");
    }

    @Test(expected= InvalidStatusException.class)
    public void testServerStatus_NOT_OK() throws InvalidServerException {
        webServer = new WebServer(8080,"src/main/java/website","Running");
    }

    @Test
    public void testGetAndSetServerStatus_OK(){
        webServer = new WebServer();
        webServer.setServerStatus("test server status");
        assertEquals("test server status",webServer.getServerStatus());
    }

    @Test
    public void testWebsiteFilesPath_OK() throws InvalidServerException {
        webServer = new WebServer(8080,"src/main/java/website","Stopped");
    }

    @Test(expected= InvalidWebsitePathException.class)
    public void testWebsiteFilesPath_NOT_OK() throws InvalidServerException {
        webServer = new WebServer(8080,"src/main/java/webserver","Stopped");
    }

    @Test
    public void testGetAndSetWebsiteFilesPath_OK(){
        webServer = new WebServer();
        webServer.setWebsiteFilesPath("test website path");
        assertEquals("test website path",webServer.getWebsiteFilesPath());
    }


    @Test
    public void testAddLevel_OK() throws InvalidServerException {
        webServer = new WebServer(8080,"src/main/java/website","Stopped");
        assertEquals(0,webServer.pagesList.size());

        ArrayList<String> testLevel = new ArrayList<String>();
        webServer.addPageLevel(testLevel);
        assertEquals(1,webServer.pagesList.size());
    }

    @Test
    public void testGetAndSetRequest_OK(){
        webServer = new WebServer();
        webServer.setRequest("test request");
        assertEquals("test request",webServer.getRequest());
    }

    @Test
    public void testAddPageAtLevelOK() throws InvalidServerException {
        webServer = new WebServer(8080,"src/main/java/website","Stopped");
        ArrayList<String> testLevel = new ArrayList<String>();
        webServer.addPageLevel(testLevel);
        assertEquals(0,webServer.pagesList.get(0).size());
        webServer.addPageAtLevel("testPage",0);
        assertEquals(1,webServer.pagesList.get(0).size());
    }

}
