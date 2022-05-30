package sweng.project.evoting.voter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class SchedaVotoReferendumController {
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Pane pane;

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
    
    private String idVotazione;
    
    public void setInfo(final String idVotazione, final String testo, final String titolo) {
    	this.idVotazione = idVotazione;
    	
    	testoReferendum.setText(testo.replace("\n", " "));
    	testoReferendum.setTextAlignment(TextAlignment.JUSTIFY);
    	
    	titoloReferendum.setText(titolo);
    	titoloReferendum.setFill(Color.RED);
    	titoloReferendum.setTextAlignment(TextAlignment.CENTER);
    }

    @FXML
    void handleNo(ActionEvent event) throws IOException {
    	FXMLLoader next = new FXMLLoader(getClass().getResource("..//voter//confermaVotoReferendumWindow.fxml"));
    	Parent root = next.load();
    	ConfermaVotoReferendumController cvrc = next.getController();
		cvrc.setScelta(idVotazione, "NO");
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(root);
    }

    @FXML
    void handleSchedaBianca(ActionEvent event) throws IOException {
    	FXMLLoader next = new FXMLLoader(getClass().getResource("..//voter//confermaVotoReferendumWindow.fxml"));
    	Parent root = next.load();
    	ConfermaVotoReferendumController cvrc = next.getController();
		cvrc.setScelta(idVotazione, "SCHEDA BIANCA");
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(root);
    }

    @FXML
    void handleSi(ActionEvent event) throws IOException {
    	FXMLLoader next = new FXMLLoader(getClass().getResource("..//voter//confermaVotoReferendumWindow.fxml"));
    	Parent root = next.load();
    	ConfermaVotoReferendumController cvrc = next.getController();
		cvrc.setScelta(idVotazione, "SI");
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(root);
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
