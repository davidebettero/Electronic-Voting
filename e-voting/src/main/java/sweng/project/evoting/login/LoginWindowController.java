package sweng.project.evoting.login;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sweng.project.evoting.Amministratore;
import sweng.project.evoting.DigitalVotingDaoImpl;
import sweng.project.evoting.Elettore;
import sweng.project.evoting.SessioneSingleton;
import sweng.project.evoting.Utente;
import sweng.project.evoting.voter.VoterWindowController;
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
    	boolean isIn = new Utente(user, psw, type).login();
    	
    	if(isIn && type.equals("Amministratore")) {
    		SessioneSingleton.getSessioneSingleton().setUser(new Amministratore(user, psw));
    		
    		login.getScene().getWindow().hide();
    		try {
    			Parent root = FXMLLoader.load(getClass().getResource("..//administrator//administratorWindow.fxml"));
    			Stage stage = new Stage();
    			stage.setTitle("Benvenuto " + user);
    			stage.setScene(new Scene(root, 600, 400));
    			stage.show();
    		}catch (Exception e) {
    			e.printStackTrace();
    		}
    	} else if(isIn && type.equals("Elettore")) {
    		SessioneSingleton.getSessioneSingleton().setUser(new Elettore(user, psw));
    		
    		login.getScene().getWindow().hide();
    		try {
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("..//voter//voterWindow.fxml"));
    			Stage stage = new Stage();
    			stage.setScene(new Scene(loader.load()));
    			VoterWindowController vwc = loader.getController();
    			vwc.setTabella(new DigitalVotingDaoImpl().getAllVotazioni());
    			stage.setTitle("Benvenuto " + user);
    			stage.show();
    		}catch (Exception e) {
    			e.printStackTrace();
    		}
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
    	
    	/*
    	 * Premere "Enter" da tastiera quando il cursore si trova nella TextField username o nella 
    	 * PasswordField password equivale a premere il login Button.
    	 */
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
