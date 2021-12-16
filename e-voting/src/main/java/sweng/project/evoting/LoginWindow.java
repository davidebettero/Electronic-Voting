package sweng.project.evoting;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.PasswordField;

public class LoginWindow {
	
    @FXML
    private Text errorMsg;

    @FXML
    private Button login;

    @FXML
    private PasswordField password;

    @FXML
    private ChoiceBox<String> tipoUtente;

    @FXML
    private TextField username;

    @FXML
    private void handleLogin(ActionEvent event) {
    	//String psw = password.getText();
    	String user = username.getText();
    	//String type = tipoUtente.getValue().toString();

    	if (!user.equals("Andrea") && !user.equals("Davide"))
    		errorMsg.setText("Errore. Username o password errati.");
    	else
    		errorMsg.setText("Benvenuto "+ user);
    }

    @FXML
    private void handlePassword(ActionEvent event) {
    	return;
    }

    @FXML
    private void handleUsername(ActionEvent event) {
    	return;
    }
    
    @FXML
    private void initialize() {
    	assert errorMsg != null : "fx:id=\"errorMsg\" was not injected: check your FXML file 'loginWindow.fxml'.";
        assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'loginWindow.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'loginWindow.fxml'.";
        assert tipoUtente != null : "fx:id=\"tipoUtente\" was not injected: check your FXML file 'loginWindow.fxml'.";
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'loginWindow.fxml'.";
        
    	ObservableList<String> tipiUtente = FXCollections.observableArrayList("Elettore", "Impiegato/gestore del sistema");
    	tipoUtente.setItems(tipiUtente);
    	tipoUtente.setValue(tipiUtente.get(0));
    }

}
