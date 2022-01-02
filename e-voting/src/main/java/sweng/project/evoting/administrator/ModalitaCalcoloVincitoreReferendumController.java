package sweng.project.evoting.administrator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ModalitaCalcoloVincitoreReferendumController {
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button referendumConQuorum;

    @FXML
    private Button referendumSenzaQuorum;

    @FXML
    private Button undoButton;

    @FXML
    private void handleReferendumConQuorum(ActionEvent event) throws IOException {
    	FXMLLoader next = new FXMLLoader(getClass().getResource("..//administrator//referendumSettingsWindow.fxml"));
    	Parent root = next.load();
    	ReferendumSettingsController rsc = next.getController();
    	rsc.setQuorum();
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(root);
    }

    @FXML
    private void handleReferendumSenzaQuorum(ActionEvent event) throws IOException {
    	AnchorPane next = FXMLLoader.load(getClass().getResource("..//administrator//referendumSettingsWindow.fxml"));
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(next);
    }

    @FXML
    private void hanldeUndo(ActionEvent event) throws IOException {
    	AnchorPane next = FXMLLoader.load(getClass().getResource("..//administrator//modalitaVotoWindow.fxml"));
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(next);
    }

    @FXML
    private void initialize() {
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'modalitaCalcoloVincitoreReferendumWindow.fxml'.";
        assert referendumConQuorum != null : "fx:id=\"referendumConQuorum\" was not injected: check your FXML file 'modalitaCalcoloVincitoreReferendumWindow.fxml'.";
        assert referendumSenzaQuorum != null : "fx:id=\"referendumSenzaQuorum\" was not injected: check your FXML file 'modalitaCalcoloVincitoreReferendumWindow.fxml'.";
        assert undoButton != null : "fx:id=\"undoButton\" was not injected: check your FXML file 'modalitaCalcoloVincitoreReferendumWindow.fxml'.";

    }
}
