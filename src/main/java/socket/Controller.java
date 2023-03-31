package socket;

import com.example.socket.HelloController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Runnable, Initializable
{
    @FXML
    private ComboBox comboBoxServer;
    @FXML
    private TextField textEingabe;

    BufferedReader reader;
    InputStream in;
    OutputStream out;

    PrintWriter writer;
    @FXML
    public void starten(ActionEvent event) {
        try {
            Thread t1 = new Thread(this);

            Socket client = new Socket(comboBoxServer.getSelectionModel().getSelectedItem().toString(), 5555);

            System.out.println("Client gestartet!");

            out = client.getOutputStream();
             writer = new PrintWriter(out);

             in = client.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in));


            t1.start();
        }
        catch(IOException e)
        {
            throw new RuntimeException();
        }

    }
    @FXML
    public void sendMessage()
    {
        writer.write(textEingabe.getText());
        writer.flush();

        System.out.println(textEingabe.getText());
    }
    @Override
    public void run() {
        try {
            String s = null;

            while ((s = reader.readLine()) != null) {

                System.out.println("Empfangen vom Server:" + s);
            }
            writer.close();
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxServer.getItems().addAll("localhost");
    }
}
