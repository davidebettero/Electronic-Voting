package sweng.project.evoting.voter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import sweng.project.evoting.Candidato;

public class SchedaVotoOrdinaleController {
	private String idVotazione;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Text errorText;

    @FXML
    private TableColumn<?, ?> colonnaCandidati;

    @FXML
    private TableColumn<?, ?> colonnaOrdine;

    @FXML
    private Button confermaButton;

    @FXML
    private Pane pane;

    @FXML
    private Button schedaBiancaButton;

    @FXML
    private TableView<RowCandidatoOrdinale> tabellaCandidati;
    
    // restituisce true se la stringa passata come argomento è un numero intero; false altrimenti
    private boolean isInteger(final String number) {
    	for (int i = 0; i < number.length(); i++) {
            if(!Character.isDigit(number.charAt(i))) {
                return false;
            } 
        }
        return true;
    }
    
    private boolean areSequentials(final ObservableList<RowCandidatoOrdinale> lista) {
    	List<Integer> lst = new ArrayList<>();
    	for(int i = 0; i < lista.size(); i++) {
    		if(!lista.get(i).getTextField().getText().toString().isEmpty() && isInteger(lista.get(i).getTextField().getText().toString()))
    			lst.add(Integer.parseInt(lista.get(i).getTextField().getText().toString()));
    	}
    	Collections.sort(lst);
    	if(lst.size() > 0) {
	    	if(lst.get(0) != 1) return false;
	    	for(int i = 0; i < lst.size() - 1; i++) {
	    		if(lst.get(i) != lst.get(i + 1) - 1)
	    			return false;
	    	}
    	}
    	return true;
    }

    @FXML
    void handleConferma(ActionEvent event) throws IOException {
    	ObservableList<RowCandidatoOrdinale> lista = tabellaCandidati.getItems();
    	boolean isOk = true, isOnlyNumber = true, isSchedaBianca = true, isSequential = true;
    	for(int i = 0; i < lista.size(); i++) {
    		if(!isInteger(lista.get(i).getTextField().getText().toString())) isOnlyNumber = false;
    		if(!lista.get(i).getTextField().getText().toString().isEmpty()) isSchedaBianca = false;
    		for(int j = i+1; j < lista.size(); j++) {
    			if(lista.get(i).getTextField().getText().toString().isEmpty() || lista.get(j).getTextField().getText().toString().isEmpty()) continue;
    			if(lista.get(i).getTextField().getText().toString().equals(lista.get(j).getTextField().getText().toString())) isOk = false;
    		}
    	}
    	
    	// controllo se i numeri sono in ordine sequenziale
    	isSequential = areSequentials(lista);
    	
    	if(!isSequential) {
    		errorText.setText("L'ordine delle preferenze deve essere sequenziale!");
    		errorText.setTextAlignment(TextAlignment.CENTER);
    	} else if(isOk && isOnlyNumber) {
    		if(isSchedaBianca) {
    			handleSchedaBianca(event);
    		}else {
    			List<Candidato> preferenze = new ArrayList<>();
    			for(int i = 0; i < lista.size(); i++) {
    				for(int j = 0; j < lista.size(); j++) {
    					if(lista.get(j).getTextField().getText().toString().equals(Integer.valueOf(i+1).toString())) {
    						preferenze.add(new Candidato(lista.get(j).getNome(), lista.get(j).getCognome(), null));
    					}
    				}
    			}
    			
    			FXMLLoader next = new FXMLLoader(getClass().getResource("..//voter//confermaVotazioneOrdinaleWindow.fxml"));
    	    	Parent root = next.load();
    	    	ConfermaVotazioneOrdinaleController cvoc = next.getController();
    	    	cvoc.setText(idVotazione, preferenze);
    			pane.getChildren().removeAll();
    	    	pane.getChildren().setAll(root);
    			
    		}
    	} else if(!isOnlyNumber) {
    		errorText.setText("Numero non riconosciuto!");
    		errorText.setTextAlignment(TextAlignment.CENTER);
    	} else if(!isOk) {
    		errorText.setText("Non può essere espresso lo stesso grado di preferenza per più di un candidato!");
    		errorText.setTextAlignment(TextAlignment.CENTER);
    	}
    }

    @FXML
    void handleSchedaBianca(ActionEvent event) throws IOException {
    	FXMLLoader next = new FXMLLoader(getClass().getResource("..//voter//confermaSchedaBiancaWindow.fxml"));
    	Parent root = next.load();
		ConfermaSchedaBiancaController csbc = next.getController();
		csbc.setId(idVotazione);
		pane.getChildren().removeAll();
    	pane.getChildren().setAll(root);
    }

    @FXML
    void initialize() {
        assert colonnaCandidati != null : "fx:id=\"colonnaCandidati\" was not injected: check your FXML file 'schedaVotoOrdinaleWindow.fxml'.";
        assert colonnaOrdine != null : "fx:id=\"colonnaOrdine\" was not injected: check your FXML file 'schedaVotoOrdinaleWindow.fxml'.";
        assert confermaButton != null : "fx:id=\"confermaButton\" was not injected: check your FXML file 'schedaVotoOrdinaleWindow.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'schedaVotoOrdinaleWindow.fxml'.";
        assert schedaBiancaButton != null : "fx:id=\"schedaBiancaButton\" was not injected: check your FXML file 'schedaVotoOrdinaleWindow.fxml'.";
        assert tabellaCandidati != null : "fx:id=\"tabellaCandidati\" was not injected: check your FXML file 'schedaVotoOrdinaleWindow.fxml'.";
        assert errorText != null : "fx:id=\"errorText\" was not injected: check your FXML file 'schedaVotoOrdinaleWindow.fxml'.";
    }

	public void setInfo(final String[] candidati, final String idVotazione) {
		this.idVotazione = Objects.requireNonNull(idVotazione);
		
		colonnaCandidati.setCellValueFactory(new PropertyValueFactory<>("Generalita"));
		colonnaOrdine.setCellValueFactory(new PropertyValueFactory<>("TextField"));
		colonnaOrdine.setStyle( "-fx-alignment: CENTER;");
		
		for(String gen : candidati) {
    		String[] nC = gen.trim().split(" ", 2);
    		RowCandidatoOrdinale rco = new RowCandidatoOrdinale(nC[0], nC[1]);
    		tabellaCandidati.getItems().add(rco);
    	}
		
	}
}
