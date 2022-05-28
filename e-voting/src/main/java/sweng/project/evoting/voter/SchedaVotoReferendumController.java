package sweng.project.evoting.voter;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class SchedaVotoReferendumController {
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button noButton;

    @FXML
    private Button schedaBiancaButton;

    @FXML
    private Button siButton;

    @FXML
    private Text testoReferendum;

    @FXML
    private Text titoloReferendum;
    
    public void setInfo(final String testo, final String titolo) {
    	testoReferendum.setText(testo.replace("\n", " "));
    	testoReferendum.setTextAlignment(TextAlignment.JUSTIFY);
    	
    	titoloReferendum.setText(titolo);
    	titoloReferendum.setFill(Color.RED);
    	titoloReferendum.setTextAlignment(TextAlignment.CENTER);
    }

    @FXML
    void handleNo(ActionEvent event) {

    }

    @FXML
    void handleSchedaBianca(ActionEvent event) {

    }

    @FXML
    void handleSi(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert noButton != null : "fx:id=\"noButton\" was not injected: check your FXML file 'schedaVotoReferendumWindow.fxml'.";
        assert schedaBiancaButton != null : "fx:id=\"schedaBiancaButton\" was not injected: check your FXML file 'schedaVotoReferendumWindow.fxml'.";
        assert siButton != null : "fx:id=\"siButton\" was not injected: check your FXML file 'schedaVotoReferendumWindow.fxml'.";
        assert testoReferendum != null : "fx:id=\"testoReferendum\" was not injected: check your FXML file 'schedaVotoReferendumWindow.fxml'.";
        assert titoloReferendum != null : "fx:id=\"titoloReferendum\" was not injected: check your FXML file 'schedaVotoReferendumWindow.fxml'.";

    }
}
