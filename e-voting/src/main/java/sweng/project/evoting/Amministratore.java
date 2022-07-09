package sweng.project.evoting;

import java.io.IOException;
import java.util.Objects;

import sweng.project.evoting.administrator.RowVotazioneAdmin;
import sweng.project.evoting.votazione.Votazione;

/*
 * OVERVIEW: questa classe istanzia un oggetto scrutatore per un sistema di voto elettronico, che rappresenta l'impiegato 
 * che gestisce la configurazione del sistema per permettere la votazione. 
 */
public class Amministratore extends Utente {
	// attributi che rappresentano il nome, il cognome e il codice fiscale dell'amministratore
	private String name, surname;
	private char[] taxCode;
	
    /*
     * Effects: istanzia this affinché rappresenti un amministratore
    */
    public Amministratore(String username, String password){
    	super(username, password, "Amministratore");
    	
    	String[] info = new DigitalVotingDaoImpl().getAdministratorInfo(username, password);
    	
    	if(info[0] == null || info[1] == null || info[0].isEmpty() || info[0].isBlank() || info[1].isEmpty() || info[1].isBlank())
    		throw new IllegalArgumentException("Il nome e il cognome dell'amministrazione devono essere indicati");
    	this.name = info[0];
    	this.surname = info[1];
    	
    	if(Objects.requireNonNull(info[2]).length() != 16)
			throw new IllegalArgumentException("Codice fiscale non valido");
		this.taxCode = info[2].toCharArray();
    }
    
    public String getName() {
    	return this.name;
    }
    
    public String getSurname() {
    	return this.surname;
    }
    
    public String getTaxCode() {
		return new String(taxCode);
	}
    
    /*
     * Effects: permette all'amministratore di vedere i risultati della votazione v (se terminata)
     */
    public void visualizzaRisultati(final Votazione v) throws IOException {
    	Objects.requireNonNull(v);
    	new RowVotazioneAdmin(v).handleVisualizzaRisultati();
    }

    /*
     * Effects: permette allo scrutatore di terminare la votazione in corso
    */
    public void terminaVotazione(final String id, final String tipo){
    	if(Objects.requireNonNull(id).isEmpty() || Objects.requireNonNull(id).isBlank())
    		throw new IllegalArgumentException("Deve essere indicato l'ID della votazione che si vuole terminare");
    	if(Objects.requireNonNull(tipo).isEmpty() || Objects.requireNonNull(tipo).isBlank())
    		throw new IllegalArgumentException("Non è indicato il tipo della votazione che si vuole terminare");
    	if(!tipo.toLowerCase().contains("referendum") && !tipo.toLowerCase().contains("categorica") && !tipo.toLowerCase().contains("ordinale"))
    		throw new IllegalArgumentException("Il tipo indicato della votazione che si vuole terminare non è valido");
    		
    	if(tipo.toLowerCase().contains("referendum")) {
    		new DigitalVotingDaoImpl().terminaVotazione(id, "referendum");
    	}else if(tipo.toLowerCase().contains("categorica")) {
    		new DigitalVotingDaoImpl().terminaVotazione(id, "categorica");
    	}else if(tipo.toLowerCase().contains("ordinale")) {
    		new DigitalVotingDaoImpl().terminaVotazione(id, "ordinale");
    	}
    }
    
    @Override
    public String toString() {
    	return String.format("%s %s, %s, amministratore", name, surname, new String(taxCode));
    }

    @Override
    public int hashCode() {
    	int result = super.hashCode();
    	result = 31 * result + name.hashCode();
    	result = 31 * result + surname.hashCode();
    	return 31 * result + new String(taxCode).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
    	if(obj instanceof Amministratore) {
    		Amministratore a = (Amministratore) obj;
    		return a.name.equals(this.name) && a.surname.equals(this.surname) && new String(a.taxCode).equals(new String(this.taxCode));
    	}
    	return false;
    }
    
}
