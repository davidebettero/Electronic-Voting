package sweng.project.evoting;

public interface Utente {
	public void vote();
    public void checkRightToVote();
    public void generaID();
    public boolean checkAlreadyVoted();
}
