package sweng.project.evoting;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginWindow {
	@FXML
	private ObservableList<String> tipiUtente = FXCollections.observableArrayList("Elettore", "Impiegato/gestore del Sistema");

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label errorMsg;

    @FXML
    private Label header;

    @FXML
    private Button login;

    @FXML
    private TextField password;

    @FXML
    private ChoiceBox tipoUtente;

    @FXML
    private TextField username;

    @FXML
    void handlePassword(ActionEvent event) {
    	password.getText();
    }

    @FXML
    void handleUsername(ActionEvent event) {
    	username.getText();
    }

    @FXML
    void login(ActionEvent event) {
    	errorMsg.setText("Errore. Username o password errati.");
    }

    @FXML
    public void initialize() {
    	assert errorMsg != null : "fx:id=\"errorMsg\" was not injected: check your FXML file 'loginWindow.fxml'.";
        assert header != null : "fx:id=\"header\" was not injected: check your FXML file 'loginWindow.fxml'.";
        assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'loginWindow.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'loginWindow.fxml'.";
        assert tipoUtente != null : "fx:id=\"tipoUtente\" was not injected: check your FXML file 'loginWindow.fxml'.";
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'loginWindow.fxml'.";

        tipoUtente.setItems(tipiUtente);
    }
    
}
