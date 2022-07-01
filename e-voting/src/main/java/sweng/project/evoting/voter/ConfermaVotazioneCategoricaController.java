package sweng.project.evoting.voter;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sweng.project.evoting.DigitalVotingDaoImpl;
import sweng.project.evoting.Elettore;
import sweng.project.evoting.SessioneSingleton;
import sweng.project.evoting.votazione.VotoCategorico;

public class ConfermaVotazioneCategoricaController {
	private String idVotazione, candidato, partito, cDP1, cDP2;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button annullaButton;

    @FXML
    private Button confermaButton;

    @FXML
    private Pane pane;

    @FXML
    private Text scelta;

    @FXML
    void handleAnnulla(ActionEvent event) {
    	try {
			List<String[]> candidati = new DigitalVotingDaoImpl().getCandidatiVotazioneCategorica(idVotazione);
			FXMLLoader next = new FXMLLoader(getClass().getResource("..//voter//schedaVotoCategoricoWindow.fxml"));
			Parent root = next.load();
			SchedaVotoCategoricoController svcc = next.getController();
			svcc.setId(idVotazione);
			svcc.setTabella(candidati);
	    	
	    	pane.getChildren().removeAll();
	    	pane.getChildren().setAll(root);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
    }

    @FXML
    void handleConferma(ActionEvent event) {
    	Elettore el = (Elettore) SessioneSingleton.getSessioneSingleton().getUser();
    	el.esprimi_voto(idVotazione);
    	
    	VotoCategorico vc = new VotoCategorico(idVotazione, candidato, partito, cDP1, cDP2);
    	new DigitalVotingDaoImpl().insertVotoCategorico(idVotazione, vc);
    	
    	confermaButton.getScene().getWindow().hide();
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("..//voter//voterWindow.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(loader.load()));
			VoterWindowController vwc = loader.getController();
			vwc.setTabella(new DigitalVotingDaoImpl().getAllVotazioni());
			stage.setTitle("Benvenuto " + el.getName());
			stage.show();
		}catch (Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {
        assert annullaButton != null : "fx:id=\"annullaButton\" was not injected: check your FXML file 'confermaVotazioneCategoricaWindow.fxml'.";
        assert confermaButton != null : "fx:id=\"confermaButton\" was not injected: check your FXML file 'confermaVotazioneCategoricaWindow.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'confermaVotazioneCategoricaWindow.fxml'.";
        assert scelta != null : "fx:id=\"scelta\" was not injected: check your FXML file 'confermaVotazioneCategoricaWindow.fxml'.";

    }

	public void setText(final String idVotazione, final String preferenze, final String candidato, final String partito, final String cDP1, final String cDP2) {
		this.idVotazione = Objects.requireNonNull(idVotazione);
		this.candidato = candidato;
		this.partito = partito;
		this.cDP1 = cDP1;
		this.cDP2 = cDP2;
		
		scelta.setText(preferenze);
		scelta.setFill(Color.BLACK);
	}
}
