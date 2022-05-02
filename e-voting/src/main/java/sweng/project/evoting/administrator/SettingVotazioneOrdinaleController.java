package sweng.project.evoting.administrator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import sweng.project.evoting.votazione.VotazioneOrdinale;

public class SettingVotazioneOrdinaleController {
	private String id;
	private VotazioneOrdinale v;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button deleteVote;

    @FXML
    private Button goSummaryButton;

    @FXML
    private Button insertAnother;

    @FXML
    private AnchorPane pane;
    
    public void setId(final String id) {
    	this.id = id;
    }
    
    public void setVotazione(final VotazioneOrdinale v) {
    	this.v = v;
    }

    @FXML
    void handleDeleteVote(ActionEvent event) {

    }

    @FXML
    void handleGoSummary(ActionEvent event) {

    }

    @FXML
    void handleInsertAnother(ActionEvent event) throws IOException {
    	FXMLLoader next = new FXMLLoader(getClass().getResource("..//administrator//inserimentoCandidatoOrdinaleWindow.fxml"));
    	Parent root = next.load();
    	InserimentoCandidatoOrdinaleController icoc = next.getController();
    	icoc.setId(id);
    	icoc.setVotazione(v);
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(root);
    }

    @FXML
    void initialize() {
        assert deleteVote != null : "fx:id=\"deleteVote\" was not injected: check your FXML file 'settingVotazioneOrdinaleWindow.fxml'.";
        assert goSummaryButton != null : "fx:id=\"goSummaryButton\" was not injected: check your FXML file 'settingVotazioneOrdinaleWindow.fxml'.";
        assert insertAnother != null : "fx:id=\"insertAnother\" was not injected: check your FXML file 'settingVotazioneOrdinaleWindow.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'settingVotazioneOrdinaleWindow.fxml'.";

    }
}
