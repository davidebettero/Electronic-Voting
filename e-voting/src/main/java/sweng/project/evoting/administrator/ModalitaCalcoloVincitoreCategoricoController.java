package sweng.project.evoting.administrator;

import java.io.IOException;
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

public class ModalitaCalcoloVincitoreCategoricoController {
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button maggioranza;

    @FXML
    private Button maggioranzaAssoluta;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button undoButton;

    @FXML
    private void handleMaggioranza(ActionEvent event) {

    }

    @FXML
    private void handleMaggioranzaAssoluta(ActionEvent event) {

    }

    @FXML
    private void hanldeUndo(ActionEvent event) throws IOException {
    	AnchorPane next = FXMLLoader.load(getClass().getResource("..//administrator//modalitaVotoWindow.fxml"));
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(next);
    }

    @FXML
    private void initialize() {
        assert maggioranza != null : "fx:id=\"maggioranza\" was not injected: check your FXML file 'modalitaCalcoloVincitoreCategoricoWindow.fxml'.";
        assert maggioranzaAssoluta != null : "fx:id=\"maggioranzaAssoluta\" was not injected: check your FXML file 'modalitaCalcoloVincitoreCategoricoWindow.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'modalitaCalcoloVincitoreCategoricoWindow.fxml'.";
        assert undoButton != null : "fx:id=\"undoButton\" was not injected: check your FXML file 'modalitaCalcoloVincitoreCategoricoWindow.fxml'.";

    }
}
