import exceptions.InvalidServerException;
import webserver.WebServerController;
import webserver.WebServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import java.util.ArrayList;

public class MainServer implements ActionListener {

    int port = 8080;
    String websiteFilesPath = "src/main/java/website";
    String serverStatus = "Stopped";
    private static WebServerController controller;
    JFrame frame = new JFrame();
    JButton start_button;
    JLabel label1;
    JLabel label2;
    JLabel label3;


    public MainServer() {
        start_button = new JButton("START");
        start_button.addActionListener(this);
        label1 = new JLabel("Server port : 8080");
        label2 = new JLabel("Admin : Tomici Iasmin ");
        label3 = new JLabel("IP : 192.158.1.38");
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(150, 150, 50, 150));
        panel.setLayout(new GridLayout(0, 1));

        panel.add(start_button);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Web Server GUI");
        frame.pack();
        frame.setVisible(true);

    }


    public static void main(String[] args) throws InvalidServerException {
        new MainServer();
    }

    public void actionPerformed(ActionEvent e) {

        try{

        WebServer webServer = new WebServer(port, websiteFilesPath, serverStatus);
        controller=new WebServerController(webServer);

        ArrayList<String> level1 = new ArrayList<String>();
        ArrayList<String> level2 = new ArrayList<String>();
        ArrayList<String> level3 = new ArrayList<String>();

        webServer.addPageLevel(level1);
        webServer.addPageLevel(level2);
        webServer.addPageLevel(level3);

        webServer.addPageAtLevel("page_1.html", 0); // 0 means level1
        webServer.addPageAtLevel("page_2.html", 0); // 0 means level1
        webServer.addPageAtLevel("page_3.html", 0); // 0 means level1
        webServer.addPageAtLevel("page_4.html", 1); // 1 means level2
        webServer.addPageAtLevel("page_5.html", 2); // 2 means level3
        webServer.addPageAtLevel("page_Colored.html", 0); // 0 means level1
        webServer.addPageAtLevel("page_Colored.css", 0); // 0 means level1


        webServer.setServerStatus("Running");

        while (true) {
            controller.requestHandler();
        }

        }catch(InvalidServerException ex) {
            ex.printStackTrace();
        }
    }
}
