package com.example.socket;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HelloController implements Runnable
{
    @FXML
    private TextArea areaNachrichten;
    @FXML
    private TextField textPortNr, textNachricht;
    @FXML
    private Button btnStart;
    @FXML
    private Label lblConnection;
    private Thread t2;

    public ServerSocket server;
    public Socket client;

    public void starten(ActionEvent event) {
        try {
            server = new ServerSocket(Integer.parseInt(textPortNr.getText()));
            t2 = new Thread(this);
            t2.start(); 
            lblConnection.setText("Erfolgreich verbunden");
            lblConnection.setVisible(true);



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    @Override
    public void run() {
            try {
                client = server.accept();

                OutputStream out = client.getOutputStream();
                PrintWriter writer = new PrintWriter(out);

                InputStream in = client.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String s = null;

                if ((s = reader.readLine()) != null) {
                    areaNachrichten.setText(s + "\n");
                    writer.flush();
                }
                writer.close();
                reader.close();
            }
            catch (IOException e) {System.out.println(e);}

    }
}
