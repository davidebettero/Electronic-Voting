package sweng.project.evoting.administrator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class RiepilogoReferendumController {
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button confermaButton;

    @FXML
    private AnchorPane pane;

    @FXML
    private Text testo;

    @FXML
    private Text typeOfReferendum;

    @FXML
    private Button undoButton;

    @FXML
    private void handleConferma(ActionEvent event) {
    	// crea la votazione "referendum" secondo le preferenze selezionate dall'amministratore
    }

    @FXML
    private void handleTesto(MouseEvent event) {

    }

    @FXML
    private void handleType(MouseEvent event) {
    	
    }

    @FXML
    private void hanldeUndo(ActionEvent event) throws IOException {
    	AnchorPane next = FXMLLoader.load(getClass().getResource("..//administrator//referendumSettingsWindow.fxml"));
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(next);
    }

    @FXML
    private void initialize() {
        assert confermaButton != null : "fx:id=\"confermaButton\" was not injected: check your FXML file 'riepilogoReferendumWindow.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'riepilogoReferendumWindow.fxml'.";
        assert testo != null : "fx:id=\"testo\" was not injected: check your FXML file 'riepilogoReferendumWindow.fxml'.";
        assert typeOfReferendum != null : "fx:id=\"typeOfReferendum\" was not injected: check your FXML file 'riepilogoReferendumWindow.fxml'.";
        assert undoButton != null : "fx:id=\"undoButton\" was not injected: check your FXML file 'riepilogoReferendumWindow.fxml'.";

        typeOfReferendum.setText("Referendum " );
    }
}
