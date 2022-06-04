package sweng.project.evoting.administrator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import sweng.project.evoting.DigitalVotingDaoImpl;
import sweng.project.evoting.votazione.Votazione;
import sweng.project.evoting.votazione.VotazioneReferendum;

public class RisultatiReferendumController {
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text numVotiNo;

    @FXML
    private Text numVotiSchedaBianca;

    @FXML
    private Text numVotiSi;

    @FXML
    private Button okButton;

    @FXML
    private Pane pane;

    @FXML
    private Text testoReferendum;

    @FXML
    private Text tipoReferendum;
    
    public void setInfo(final Votazione v) {
    	VotazioneReferendum vr = (VotazioneReferendum) v;
    	tipoReferendum.setText(vr.getTipo());
    	tipoReferendum.setFill(Color.BLUE);
    	testoReferendum.setText(vr.getTesto().replace("\n", " "));
    	testoReferendum.setFill(Color.BLUE);
    	testoReferendum.setTextAlignment(TextAlignment.JUSTIFY);
    	testoReferendum.setStyle("-fx-font: 13 system;");
    	
    	int[] res = new DigitalVotingDaoImpl().getRisultatiReferendum(vr.getId());
    	numVotiSi.setText(Integer.toString(res[0]));
    	numVotiSi.setFill(Color.RED);
    	numVotiNo.setText(Integer.toString(res[1]));
    	numVotiNo.setFill(Color.RED);
    	numVotiSchedaBianca.setText(Integer.toString(res[2]));
    	numVotiSchedaBianca.setFill(Color.RED);
    }

    @FXML
    void handleOk(ActionEvent event) {
    	Stage s = (Stage) okButton.getScene().getWindow();
    	s.close();
    }

    @FXML
    void initialize() {
        assert numVotiNo != null : "fx:id=\"numVotiNo\" was not injected: check your FXML file 'risultatiReferendumWindow.fxml'.";
        assert numVotiSchedaBianca != null : "fx:id=\"numVotiSchedaBianca\" was not injected: check your FXML file 'risultatiReferendumWindow.fxml'.";
        assert numVotiSi != null : "fx:id=\"numVotiSi\" was not injected: check your FXML file 'risultatiReferendumWindow.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'risultatiReferendumWindow.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'risultatiReferendumWindow.fxml'.";
        assert testoReferendum != null : "fx:id=\"testoReferendum\" was not injected: check your FXML file 'risultatiReferendumWindow.fxml'.";
        assert tipoReferendum != null : "fx:id=\"tipoReferendum\" was not injected: check your FXML file 'risultatiReferendumWindow.fxml'.";

    }
}
