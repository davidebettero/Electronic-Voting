package sweng.project.evoting.administrator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    void handleMaggioranza(ActionEvent event) {

    }

    @FXML
    void handleMaggioranzaAssoluta(ActionEvent event) {

    }

    @FXML
    void hanldeUndo(ActionEvent event) {
    	try {
    		undoButton.getScene().getWindow().hide();
    		Parent root = FXMLLoader.load(getClass().getResource("..//administrator//modalitaVotoWindow.fxml"));
    		Stage stage = new Stage();
    		stage.setTitle(((Stage) ((Node) event.getSource()).getScene().getWindow()).getTitle());
    		stage.setScene(new Scene(root, 600, 400));
    		stage.show();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

}
