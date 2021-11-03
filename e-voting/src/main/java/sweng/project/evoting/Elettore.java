package sweng.project.evoting;

import java.util.Objects;

/*
 * OVERVIEW: questa classe istanzia un oggetto di tipo Elettore che fa parte di un sistema di voto elettronico.
 * Esso rappresenta il cittadino elettore che deve votare.
 */
public class Elettore implements Utente {
	/*
     * Gli attributi name e surname rappresentano rispettivamente il nome e il cognome dell'elettore.
     * documentID è una stringa che rappresenta il codice della carta d'identità di ogni elettore.
    */
    private final String documentID, name, surname;
    /*
     * L'attributo booleano hasVoted indica se l'elettore ha già votato (true) oppure no (false),
     * city indica se l'elettore vive in un comune con più di 15.000 abitanti (true) o no (false),
     * canVote indica se l'elettore ha diritto di voto (true) oppure no (false).
    */
    private boolean hasVoted, city, canVote;

    /*
     * Effects: inizializza this affinché rappresenti un elettore che è identificato da nome, cognome 
     * 			e codice della carta d'identità passati come parametro al costruttore stesso.
     * 			Se name, surname e/o documentID sono null viene sollevata un'eccezione di tipo NullPointerException.
    */
    
    public Elettore(String name, String surname, String documentID) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(surname);
        Objects.requireNonNull(documentID);

        this.name = name;
        this.surname = surname;
        this.documentID = documentID;
    }
    

    /*
     * Effects: permette all'elettore di votare
    */
    public void vote(){}
    
    /* 
     * Effects: consente all'utente di effettuare la fase di identificazione 
    */
    public boolean login(){}

    /*
     * Modifies: potrebbe modificare canVote e city
     * Effects: se l'elettore ha diritto di voto modifica canVote assegnandogli valore true,
     *          false altrimenti.
     *          se l'elettore abita in un comune con più di 15.000 abitanti modifica city assegnandogli true,
     *          false altrimenti.
    */
    public boolean checkRightToVote(){}   
    

    /*
     * Effects: restituisce true se l'elettore ha già effettuato la votazione,
     *          false altrimenti.
    */
    public boolean checkAlreadyVoted(){}
    
    @Override
    public String toString() {
    }

    @Override
    public int hashCode() {
    }

    @Override
    public boolean equals(Object obj) {
    }
}
