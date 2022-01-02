package sweng.project.evoting.administrator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ReferendumSettingsController {
	// variabile che vale true se il referendum è CON quorum, false altrimenti. Di default è settata a false.
	private boolean quorum = false;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button okButton;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextArea referendumText;

    @FXML
    private Button undoButton;
    
    // metodo che assegna true alla variabile booleana quorum
    public void setQuorum() {
    	quorum = true;
    }

    @FXML
    private void handleOk(ActionEvent event) throws IOException {
    	String text = referendumText.getText();
    	FXMLLoader next = new FXMLLoader(getClass().getResource("..//administrator//riepilogoReferendumWindow.fxml"));
    	Parent root = next.load();
    	RiepilogoReferendumController rrc = next.getController();
    	// se quorum = true allora il referendum sarà con quorum, senza altrimenti.
    	if(quorum) rrc.insertType("CON"); else rrc.insertType("SENZA");
    	rrc.insertText(text);
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(root);
    }

    @FXML
    private void handleReferendumText(MouseEvent event) {
    	
    }

    @FXML
    private void hanldeUndo(ActionEvent event) throws IOException {
    	AnchorPane next = FXMLLoader.load(getClass().getResource("..//administrator//modalitaCalcoloVincitoreReferendumWindow.fxml"));
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(next);
    }

    @FXML
    private void initialize() {
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'referendumSettingsWindow.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'referendumSettingsWindow.fxml'.";
        assert referendumText != null : "fx:id=\"referendumText\" was not injected: check your FXML file 'referendumSettingsWindow.fxml'.";
        assert undoButton != null : "fx:id=\"undoButton\" was not injected: check your FXML file 'referendumSettingsWindow.fxml'.";

    }
}
