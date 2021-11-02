package sweng.project.evoting;

/*
 * OVERVIEW: questa interfaccia specifica dei metodi utili a gestire un oggetto di tipo Utente, in particolare 
 * nel caso del nostro progetto, l'interfaccia verrà implementata da piu' classi che rappresentano l'elettore e lo scrutatore.
 */
public interface Utente {
	public void vote();
    public boolean checkRightToVote();
    public boolean checkAlreadyVoted();
    public boolean login();
}
