package sweng.project.evoting;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginWindow {
	@FXML
	private ObservableList<String> tipiUtente = FXCollections.observableArrayList("Elettore", "Impiegato/gestore del sistema");

    @FXML
    private Text errorMsg;

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
    private void handleLogin(ActionEvent event) {
    	String psw = password.getText();
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
    	tipoUtente.setItems(tipiUtente);
    }

}
