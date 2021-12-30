package sweng.project.evoting.administrator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdministratorWindowController {

    @FXML
    private Button creaNuovaVotazione;

    @FXML
    private Button logoutButton;

    @FXML
    private Button terminaVotazione;

    @FXML
    private Button visualizzaRisultati;

    @FXML
    private Text titleMsg;

    @FXML
    private void endVote(ActionEvent event) {
    	
    }

    @FXML
    private void handleLogout(ActionEvent event) {
    	try {
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
    private void handleNewVote(ActionEvent event) {  	
    	try {
    		creaNuovaVotazione.getScene().getWindow().hide();
    		Parent root = FXMLLoader.load(getClass().getResource("..//administrator//modalitaVotoWindow.fxml"));
    		Stage stage = new Stage();
    		stage.setTitle(((Stage) ((Node) event.getSource()).getScene().getWindow()).getTitle());
    		stage.setScene(new Scene(root, 600, 400));
    		stage.show();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    private void viewElectionResults(ActionEvent event) {

    }

    @FXML
    private void initialize() {
        assert creaNuovaVotazione != null : "fx:id=\"creaNuovaVotazione\" was not injected: check your FXML file 'administratorWindow.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'administratorWindow.fxml'.";
        assert terminaVotazione != null : "fx:id=\"terminaVotazione\" was not injected: check your FXML file 'administratorWindow.fxml'.";
        assert visualizzaRisultati != null : "fx:id=\"visualizzaRisultati\" was not injected: check your FXML file 'administratorWindow.fxml'.";
        assert titleMsg != null : "fx:id=\"welcomeMsg\" was not injected: check your FXML file 'administratorWindow.fxml'.";

    }

}
