package sweng.project.evoting;

import java.util.Objects;

/*
 * OVERVIEW: questa classe istanzia un oggetto scrutatore per un sistema di voto elettronico
 */
public class Scrutatore extends Elettore {
	/*
     * L'attributo specialID identifica univocamente uno scrutatore
    */
    private final String specialID;

    /*
     * Effects: istanzia this affinché rappresenti uno scrutatore che è identificato da nome, cognome,
     *          carta d'identità e da un ID speciale a lui dedicato.
     *          Solleva un'eccezione di tipo NullPointerException se specialID è null.
    */
    public Scrutatore(String id, String name, String surname, String documentID, String specialID){
        super(id, name, surname, documentID);

        Objects.requireNonNull(specialID);
        this.specialID = specialID;
    }

    /*
     * Effects: permette allo scrutatore di settare la modalità di voto (referendum / politiche)
    */
    public void setVotingMethod(){}

    /*
     * Effects: permette allo scrutatore di settare le modalità di calcolo del vincitore
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
}
