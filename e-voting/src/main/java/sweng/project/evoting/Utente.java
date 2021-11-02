package sweng.project.evoting;

/*
 * OVERVIEW: questa interfaccia specifica dei metodi utili a un oggetto utente
 */
public interface Utente {
	public void vote();
    public void checkRightToVote();
    public void generaID();
    public boolean checkAlreadyVoted();
}
