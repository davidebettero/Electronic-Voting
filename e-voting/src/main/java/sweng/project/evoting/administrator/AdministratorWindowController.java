package sweng.project.evoting.administrator;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.Instant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sweng.project.evoting.Amministratore;
import sweng.project.evoting.DigitalVotingDaoImpl;
import sweng.project.evoting.SessioneSingleton;

public class AdministratorWindowController {
	
	@FXML
    private Pane pane;

    @FXML
    private Button creaNuovaVotazione;
    
    @FXML
    private Text infoAdmin;

    @FXML
    private Button logoutButton;

    @FXML
    private Button terminaVotazione;

    @FXML
    private Button visualizzaRisultati;
    
    @FXML
    private Button visualizzaLogButton;

    @FXML
    private Text titleMsg;

    @FXML
    private void endVote(ActionEvent event) throws IOException, ParseException {
    	FXMLLoader next = new FXMLLoader(getClass().getResource("..//administrator//terminaVotazioniWindow.fxml"));
    	Parent root = next.load();
		TerminaVotazioniController tvc = next.getController();
		tvc.setTabella(new DigitalVotingDaoImpl().getAllVotazioni());
		pane.getChildren().removeAll();
    	pane.getChildren().setAll(root);
    }

    @FXML
    private void handleLogout(ActionEvent event) {
    	try {
    		SessioneSingleton.getSessioneSingleton().logoutUser();
    		
    		new DigitalVotingDaoImpl().insertIntoLogTable(
        			Timestamp.from(Instant.now()), 
        			(Amministratore) SessioneSingleton.getSessioneSingleton().getUser(), 
        			"Si è disconnesso"
        		);
    		
    		logoutButton.getScene().getWindow().hide();
    		Parent root = FXMLLoader.load(getClass().getResource("..//login//loginWindow.fxml"));
    		Stage stage = new Stage();
    		stage.setTitle("Votazione Elettronica - Login");
    		stage.setScene(new Scene(root, 600, 400));
    		stage.setResizable(false);
    		stage.show();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    private void handleNewVote(ActionEvent event) throws IOException {
    	AnchorPane next = FXMLLoader.load(getClass().getResource("..//administrator//modalitaVotoWindow.fxml"));
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(next);
    }

    @FXML
    private void viewElectionResults(ActionEvent event) throws IOException, ParseException {
    	FXMLLoader next = new FXMLLoader(getClass().getResource("..//administrator//visualizzaRisultatiWindow.fxml"));
    	Parent root = next.load();
    	VisualizzaRisultatiController vrc = next.getController();
    	vrc.setTabella(new DigitalVotingDaoImpl().getAllExistingVotazioni());
		pane.getChildren().removeAll();
    	pane.getChildren().setAll(root);
    }
    
    @FXML
    void handleLogButton(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("..//administrator//logWindow.fxml"));
		Stage stage = new Stage();
		stage.setScene(new Scene(loader.load()));
		
		LogController lc = loader.getController();
		lc.setTabella(new DigitalVotingDaoImpl().getLog());
		
		stage.setTitle("Log");
		stage.setResizable(false);
		stage.show();
    }

    @FXML
    private void initialize() {
        assert creaNuovaVotazione != null : "fx:id=\"creaNuovaVotazione\" was not injected: check your FXML file 'administratorWindow.fxml'.";
        assert infoAdmin != null : "fx:id=\"infoAdmin\" was not injected: check your FXML file 'administratorWindow.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'administratorWindow.fxml'.";
        assert terminaVotazione != null : "fx:id=\"terminaVotazione\" was not injected: check your FXML file 'administratorWindow.fxml'.";
        assert visualizzaRisultati != null : "fx:id=\"visualizzaRisultati\" was not injected: check your FXML file 'administratorWindow.fxml'.";
        assert titleMsg != null : "fx:id=\"welcomeMsg\" was not injected: check your FXML file 'administratorWindow.fxml'.";
        assert visualizzaLogButton != null : "fx:id=\"visualizzaLogButton\" was not injected: check your FXML file 'administratorWindow.fxml'.";
        
        Amministratore a = (Amministratore) SessioneSingleton.getSessioneSingleton().getUser();
        infoAdmin.setText(String.format("%s %s\n%s", a.getName(), a.getSurname(), a.getTaxCode()));
        infoAdmin.setStyle("-fx-font: 14 system;");
    }

}
