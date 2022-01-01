package sweng.project.evoting.administrator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

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
    private void handleReferendum(ActionEvent event) throws IOException {
    	AnchorPane next = FXMLLoader.load(getClass().getResource("..//administrator//modalitaCalcoloVincitoreReferendumWindow.fxml"));
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(next);
    }

    @FXML
    private void handleVotoCategorico(ActionEvent event) throws IOException {
    	AnchorPane next = FXMLLoader.load(getClass().getResource("..//administrator//modalitaCalcoloVincitoreCategoricoWindow.fxml"));
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(next);
    }

    @FXML
    private void handleVotoCategoricoPreferenze(ActionEvent event) throws IOException {
    	AnchorPane next = FXMLLoader.load(getClass().getResource("..//administrator//modalitaCalcoloVincitoreCategoricoPreferenzeWindow.fxml"));
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(next);
    }

    @FXML
    private void handleVotoOrdinale(ActionEvent event) {

    }

    @FXML
    private void hanldeUndo(ActionEvent event) throws IOException {
    	AnchorPane next = FXMLLoader.load(getClass().getResource("..//administrator//administratorWindow.fxml"));
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(next);
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

