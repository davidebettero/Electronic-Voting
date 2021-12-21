package sweng.project.evoting;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.PasswordField;

public class LoginWindowController {
	
    @FXML
    private Text errorMsg;

    @FXML
    private Button login;
    
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private ChoiceBox<String> tipoUtente;
    
    @FXML
    private void enterPressed(KeyEvent ke) {
    	if(ke.getCode().equals(KeyCode.ENTER)) {
    		login.fire();
    	}
    }

    @FXML
    private void handleLogin(ActionEvent event) {
    	
    	String user = username.getText();
    	String psw = password.getText();
    	String type = tipoUtente.getValue().toString();
    	
    	// variabile che vale true se l'utente identificato da user, psw e type è presente nel database, false altrimenti
    	boolean isIn = LoginWindowView.authenticate(user, psw, type);
    	
    	if(isIn) {
    		errorMsg.setFill(Color.GREEN);
			errorMsg.setText("Benvenuto "+ user + "!");
    	}else {
    		errorMsg.setFill(Color.RED);
	    	errorMsg.setText("Errore. Username o password errati.");
		}
    }
     
    @FXML
    private void initialize() {
    	assert errorMsg != null : "fx:id=\"errorMsg\" was not injected: check your FXML file 'loginWindow.fxml'.";
        assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'loginWindow.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'loginWindow.fxml'.";
        assert tipoUtente != null : "fx:id=\"tipoUtente\" was not injected: check your FXML file 'loginWindow.fxml'.";
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'loginWindow.fxml'.";
        
        
        
    	ObservableList<String> tipiUtente = FXCollections.observableArrayList("Elettore", "Amministratore");
    	tipoUtente.setItems(tipiUtente);
    	tipoUtente.setValue(tipiUtente.get(0));
    	
    	password.setOnKeyPressed(new EventHandler<KeyEvent>() {
    		@Override
    		public void handle(KeyEvent ke) {
    			if(ke.getCode().equals(KeyCode.ENTER)) {
    				login.fire();
    			}
    		}
    	});
    	
    	username.setOnKeyPressed(new EventHandler<KeyEvent>() {
    		@Override
    		public void handle(KeyEvent ke) {
    			if(ke.getCode().equals(KeyCode.ENTER)) {
    				login.fire();
    			}
    		}
    	});
    }

}
