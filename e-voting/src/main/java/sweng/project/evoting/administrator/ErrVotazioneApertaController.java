package sweng.project.evoting.administrator;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ErrVotazioneApertaController {
	
	@FXML
    private Button okButton;

    @FXML
    private AnchorPane pane;

    @FXML
    void handleOk(ActionEvent event) throws IOException {
    	Stage s = (Stage) okButton.getScene().getWindow();
    	s.close();
    }
}
