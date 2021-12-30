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
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ReferendumSettingsController {
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

    @FXML
    private void handleOk(ActionEvent event) {

    }

    @FXML
    private void handleReferendumText(MouseEvent event) {

    }

    @FXML
    private void hanldeUndo(ActionEvent event) {
    	try {
    		undoButton.getScene().getWindow().hide();
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
    private void initialize() {
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'referendumSettingsWindow.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'referendumSettingsWindow.fxml'.";
        assert referendumText != null : "fx:id=\"referendumText\" was not injected: check your FXML file 'referendumSettingsWindow.fxml'.";
        assert undoButton != null : "fx:id=\"undoButton\" was not injected: check your FXML file 'referendumSettingsWindow.fxml'.";

    }
}
