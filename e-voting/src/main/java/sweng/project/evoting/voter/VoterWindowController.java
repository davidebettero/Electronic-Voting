package sweng.project.evoting.voter;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sweng.project.evoting.Elettore;
import sweng.project.evoting.SessioneSingleton;
import sweng.project.evoting.votazione.RowVotazione;
import sweng.project.evoting.votazione.Votazione;
import javafx.scene.text.Text;

public class VoterWindowController {
	private List<Votazione> lista;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<RowVotazione, Button> colonnaAzioni;

    @FXML
    private TableColumn<RowVotazione, String> colonnaVotazioni;

    @FXML
    private ImageView img;
    
    @FXML
    private Text infoVoter;

    @FXML
    private Button logoutButton;

    @FXML
    private Pane pane;

    @FXML
    private TableView<RowVotazione> tabellaVotazioni;
    
    public void setTabella(final List<Votazione> l) {
    	this.lista = l;
    	colonnaVotazioni.setCellValueFactory(new PropertyValueFactory<>("Tipo"));
    	colonnaAzioni.setCellValueFactory(new PropertyValueFactory<>("ButtonBar"));
    	
    	for(Votazione v : lista) {
    		RowVotazione rv = new RowVotazione(v, pane);
        	tabellaVotazioni.getItems().add(rv);
    	}
    	if(lista.size() == 0) {
    		tabellaVotazioni.setPlaceholder(new Label("Non c'è nessuna votazione per cui puoi votare"));
    	}
    }

    @FXML
    void handleLogout(ActionEvent event) {
    	try {
    		SessioneSingleton.getSessioneSingleton().logoutUser();
    		
    		logoutButton.getScene().getWindow().hide();
    		Parent root = FXMLLoader.load(getClass().getResource("..//login//loginWindow.fxml"));
    		Stage stage = new Stage();
    		stage.setTitle("Votazione Elettronica - Login");
    		stage.setScene(new Scene(root, 600, 400));
    		stage.setResizable(false);
    		stage.show();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void initialize() {
        assert colonnaAzioni != null : "fx:id=\"colonnaAzioni\" was not injected: check your FXML file 'voterWindow.fxml'.";
        assert colonnaVotazioni != null : "fx:id=\"colonnaVotazioni\" was not injected: check your FXML file 'voterWindow.fxml'.";
        assert img != null : "fx:id=\"img\" was not injected: check your FXML file 'voterWindow.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'voterWindow.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'voterWindow.fxml'.";
        assert tabellaVotazioni != null : "fx:id=\"tabellaVotazioni\" was not injected: check your FXML file 'voterWindow.fxml'.";
        assert infoVoter != null : "fx:id=\"infoVoter\" was not injected: check your FXML file 'voterWindow.fxml'.";
        
        Elettore e = (Elettore) SessioneSingleton.getSessioneSingleton().getUser();
        infoVoter.setText(String.format("%s %s\n%s\n%s", e.getName(), e.getSurname(), e.getBirthDate(), e.getTaxCode()));
        infoVoter.setStyle("-fx-font: 14 system;");
    }
}
