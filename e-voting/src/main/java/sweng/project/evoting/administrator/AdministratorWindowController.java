package sweng.project.evoting.administrator;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdministratorWindowController {
	
	@FXML
    private Pane pane;

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
    private void handleNewVote(ActionEvent event) throws IOException {
    	AnchorPane next = FXMLLoader.load(getClass().getResource("..//administrator//modalitaVotoWindow.fxml"));
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(next);
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
