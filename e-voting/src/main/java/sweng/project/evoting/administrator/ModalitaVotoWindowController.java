package sweng.project.evoting.administrator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ModalitaVotoWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button referendumButton;

    @FXML
    private Button undoButton;

    @FXML
    private Button votoCategoricoButton;

    @FXML
    private Button votoCategoricoPreferenzeButton;

    @FXML
    private Button votoOrdinaleButton;

    @FXML
    private void handleReferendum(ActionEvent event) {
    	try {
    		referendumButton.getScene().getWindow().hide();
    		Parent root = FXMLLoader.load(getClass().getResource("..//administrator//modalitaCalcoloVincitoreReferendumWindow.fxml"));
    		Stage stage = new Stage();
    		stage.setTitle(((Stage) ((Node) event.getSource()).getScene().getWindow()).getTitle());
    		stage.setScene(new Scene(root, 600, 400));
    		stage.show();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    private void handleVotoCategorico(ActionEvent event) {
    	try {
    		votoCategoricoButton.getScene().getWindow().hide();
    		Parent root = FXMLLoader.load(getClass().getResource("..//administrator//modalitaCalcoloVincitoreCategoricoWindow.fxml"));
    		Stage stage = new Stage();
    		stage.setTitle(((Stage) ((Node) event.getSource()).getScene().getWindow()).getTitle());
    		stage.setScene(new Scene(root, 600, 400));
    		stage.show();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    private void handleVotoCategoricoPreferenze(ActionEvent event) {
    	try {
    		votoCategoricoPreferenzeButton.getScene().getWindow().hide();
    		Parent root = FXMLLoader.load(getClass().getResource("..//administrator//modalitaCalcoloVincitoreCategoricoPreferenzeWindow.fxml"));
    		Stage stage = new Stage();
    		stage.setTitle(((Stage) ((Node) event.getSource()).getScene().getWindow()).getTitle());
    		stage.setScene(new Scene(root, 600, 400));
    		stage.show();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    private void handleVotoOrdinale(ActionEvent event) {

    }

    @FXML
    private void hanldeUndo(ActionEvent event) {
    	try {
    		undoButton.getScene().getWindow().hide();
    		Parent root = FXMLLoader.load(getClass().getResource("..//administrator//administratorWindow.fxml"));
    		Stage stage = new Stage();
    		stage.setTitle(((Stage) ((Node) event.getSource()).getScene().getWindow()).getTitle());
    		stage.setScene(new Scene(root, 600, 400));
    		stage.show();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    private void initialize() {
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'modalitaVotoWindow.fxml'.";
        assert referendumButton != null : "fx:id=\"referendumButton\" was not injected: check your FXML file 'modalitaVotoWindow.fxml'.";
        assert undoButton != null : "fx:id=\"undoButton\" was not injected: check your FXML file 'modalitaVotoWindow.fxml'.";
        assert votoCategoricoButton != null : "fx:id=\"votoCategoricoButton\" was not injected: check your FXML file 'modalitaVotoWindow.fxml'.";
        assert votoCategoricoPreferenzeButton != null : "fx:id=\"votoCategoricoPreferenzeButton\" was not injected: check your FXML file 'modalitaVotoWindow.fxml'.";
        assert votoOrdinaleButton != null : "fx:id=\"votoOrdinaleButton\" was not injected: check your FXML file 'modalitaVotoWindow.fxml'.";

    }

}

