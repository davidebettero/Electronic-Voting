package sweng.project.evoting.administrator;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RisultatiOrdinaleController {
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text numSchedeBianche;

    @FXML
    private Text numTotaleVotanti;

    @FXML
    private Button okButton;

    @FXML
    private Pane pane;

    @FXML
    private Text risultati;
    
    public void setInfo(final int numeroSchedeBianche, final int numTotVotanti) {
    	if(numeroSchedeBianche > numTotVotanti)
    		throw new IllegalArgumentException("Il numero di schede bianche non può essere maggiore del numero totale dei votanti");
    	if(numeroSchedeBianche < 0 || numTotVotanti < 0)
    		throw new IllegalArgumentException("Il numero di schede bianche o il numero totale dei votanti non può essere negativo");
    	
    	numSchedeBianche.setText(Integer.valueOf(numeroSchedeBianche).toString());
    	numSchedeBianche.setFill(Color.BLACK);
    	numTotaleVotanti.setText(Integer.valueOf(numTotVotanti).toString());
    	numTotaleVotanti.setFill(Color.BLACK);
    }
    
    @FXML
    void handleOk(ActionEvent event) {
    	Stage s = (Stage) okButton.getScene().getWindow();
    	s.close();
    }

    @FXML
    void initialize() {
        assert numSchedeBianche != null : "fx:id=\"numSchedeBianche\" was not injected: check your FXML file 'risultatiOrdinaleWindow.fxml'.";
        assert numTotaleVotanti != null : "fx:id=\"numTotaleVotanti\" was not injected: check your FXML file 'risultatiOrdinaleWindow.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'risultatiOrdinaleWindow.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'risultatiOrdinaleWindow.fxml'.";
        assert risultati != null : "fx:id=\"risultati\" was not injected: check your FXML file 'risultatiOrdinaleWindow.fxml'.";

    }

	public void setRisultati(final Map<String, Map<String, Integer>> risultatiOrdinale) {
		String res = new String();
		for(var entry : risultatiOrdinale.entrySet()) {
			res += String.format("%s → ", entry.getKey().trim());
			for(var entry2 : entry.getValue().entrySet()) {
				res += String.format("%s°: %s    ", entry2.getKey().trim(), entry2.getValue().toString().trim());
			}
			res += "\n";
		}
		
		risultati.setText(res);
		risultati.setFill(Color.BLACK);
	}
}
