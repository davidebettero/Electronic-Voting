package sweng.project.evoting;

import java.sql.Connection;


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
    private void enterPressed(KeyEvent ke) {
    	if(ke.getCode().equals(KeyCode.ENTER)) {
    		login.fire();
    	}
    }

    @FXML
    private void handleLogin(ActionEvent event) {
    	
    	String user = username.getText();
    	String type = tipoUtente.getValue().toString();
    	String psw = password.getText();
    	
    	
    	App app = new App();
	    Connection conn=app.connect();
	        //System.out.println(App.encoding("a"));
	    DigitalVotingDaoImpl d=new DigitalVotingDaoImpl(conn);
    	if(d.isValid(user,psw,type)) {
    		errorMsg.setFill(Color.GREEN);
			errorMsg.setText("Benvenuto "+ user + "!");
    	}else {
    		errorMsg.setFill(Color.RED);
	    	errorMsg.setText("Errore. Username o password errati.");
		}
    	try {
	    conn.close();
    	}catch(Exception e) {}
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
