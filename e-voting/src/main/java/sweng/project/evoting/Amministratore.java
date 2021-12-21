package sweng.project.evoting;

import java.util.Objects;

/*
 * OVERVIEW: questa classe istanzia un oggetto scrutatore per un sistema di voto elettronico, che rappresenta l'impiegato 
 * che gestisce la configurazione del sistema per permettere la votazione. 
 */
public class Amministratore implements Utente {
	/*
     * L'attributo specialID identifica univocamente uno scrutatore.
    */
    private final String specialID;

    /*
     * Effects: istanzia this affinch� rappresenti uno scrutatore che � identificato da nome, cognome,
     *          carta d'identit� e da un ID speciale a lui dedicato.
     *          Solleva un'eccezione di tipo NullPointerException se specialID � null.
    */
    public Amministratore(String name, String surname, String documentID, String specialID){

        Objects.requireNonNull(specialID);
        this.specialID = specialID;
    }

    /*
     * Effects: permette allo scrutatore di settare la modalit� di voto (referendum / politiche)
    */
    public void setVotingMethod(){}

    /*
     * Effects: permette allo scrutatore di settare le modalit� di calcolo del vincitore
    */
    public void setWinnerCalculation(){}

    /*
     * Effects: permette allo scrutatore di settare la votazione nella sua completezza
    */
    public void configure(){}

    /*
     * Effects: permette allo scrutatore di terminare la votazione in corso
    */
    public void terminaVotazione(){}

    /*
     * Effects: permette allo scrutatore di visualizzare l'esito della votazione
    */
    public void visualizzaEsiti(){}
    
    @Override
    public String toString() {
    	return "";
    }

    @Override
    public int hashCode() {
    	return 0;
    }

    @Override
    public boolean equals(Object obj) {
    	return false;
    }

	@Override
	public void vote() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkRightToVote() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkAlreadyVoted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean login() {
		// TODO Auto-generated method stub
		return false;
	}
    
}
