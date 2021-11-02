package sweng.project.evoting;

import java.util.Objects;

/*
 * OVERVIEW: questa classe istanzia un oggetto elettore per un sistema di voto elettronico 
 */
public class Elettore implements Utente {
	/*
     * Gli attributi name e surname rappresentano rispettivamente il nome e il cognome dell'elettore.
     * id e documentID sono stringhe che rappresentano rispettivamente un identificativo
     * diverso per ogni elettore e il codice della carta d'identità di ogni elettore.
    */
    private final String id, documentID, name, surname;
    /*
     * L'attributo booleano hasVoted indica se l'elettore ha già votato (true) oppure no (false),
     * city indica se l'elettore vive in u comune con più di 15.000 abitanti (true) o no (false),
     * canVote indica se l'elettore ha diritto di voto (true) oppure no (false).
    */
    private boolean hasVoted, city, canVote;

    /*
     * Effects: inizializza this affinché rappresenti un elettore che è identificato da nome, cognome 
     * e codice della carta d'identità passati come parametro al costruttore stesso.
     * Se name, surname e/o documentID sono null viene sollevata un'eccezione di tipo NullPointerException.
    */
    
    public Elettore(String id, String name, String surname, String documentID) {
    	Objects.requireNonNull(id);
        Objects.requireNonNull(name);
        Objects.requireNonNull(surname);
        Objects.requireNonNull(documentID);

        this.id = id;
        this.name = name;
        this.surname = surname;
        this.documentID = documentID;
    }
    

    /*
     * Effects: permette all'elettore di votare
    */
    public void vote(){}

    /*
     * Modifies: potrebbe modificare canVote e city
     * Effects: se l'elettore ha diritto di voto modifica canVote assegnandogli valore true,
     *          false altrimenti.
     *          se l'elettore abita in un comune con più di 15.000 abitanti modifica city assegnandogli true,
     *          false altrimenti.
    */
    public void checkRightToVote(){
        
    }

    /*
     * Modifies: potrebbe modificare id
     * Effects: assegna a id un valore pseudocasuale
    */
    public void generaID(){
        
    }

    /*
     * Effects: restituisce true se l'elettore ha già effettuato la votazione,
     *          false altrimenti.
    */
    public boolean checkAlreadyVoted(){
        return hasVoted;
    }
}
