package sweng.project.evoting.administrator;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ModalitaCalcoloVincitoreCategoricoPreferenzeController {

    @FXML
    private Button maggioranza;

    @FXML
    private Button maggioranzaAssoluta;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button undoButton;

    @FXML
    private void handleMaggioranza(ActionEvent event) throws IOException {
    	FXMLLoader next = new FXMLLoader(getClass().getResource("..//administrator//categoricoSettingsWindow.fxml"));
    	Parent root = next.load();
    	CategoricoSettingsController csc = next.getController();
    	csc.setPreferenze();
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(root);
    }

    @FXML
    private void handleMaggioranzaAssoluta(ActionEvent event) throws IOException {
    	FXMLLoader next = new FXMLLoader(getClass().getResource("..//administrator//categoricoSettingsWindow.fxml"));
    	Parent root = next.load();
    	CategoricoSettingsController csc = next.getController();
    	csc.setPreferenze();
    	csc.setAssoluta();
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(root);
    }

    @FXML
    private void hanldeUndo(ActionEvent event) throws IOException {
    	AnchorPane next = FXMLLoader.load(getClass().getResource("..//administrator//modalitaVotoWindow.fxml"));
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(next);
    }
    
    @FXML
    private void initialize() {
        assert maggioranza != null : "fx:id=\"maggioranza\" was not injected: check your FXML file 'modalitaCalcoloVincitoreCategoricoPreferenzeWindow.fxml'.";
        assert maggioranzaAssoluta != null : "fx:id=\"maggioranzaAssoluta\" was not injected: check your FXML file 'modalitaCalcoloVincitoreCategoricoPreferenzeWindow.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'modalitaCalcoloVincitoreCategoricoPreferenzeWindow.fxml'.";
        assert undoButton != null : "fx:id=\"undoButton\" was not injected: check your FXML file 'modalitaCalcoloVincitoreCategoricoPreferenzeWindow.fxml'.";

    }

}
