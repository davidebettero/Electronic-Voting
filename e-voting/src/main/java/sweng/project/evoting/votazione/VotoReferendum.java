package sweng.project.evoting.votazione;

public class VotoReferendum extends Voto{
    
    private boolean scelta;

    public VotoReferendum(String id, String idVotazione, boolean scelta){
        super(id, idVotazione);
        
        this.scelta = scelta;
    }

    public boolean getPreferenza(){
        return this.scelta;
    }
}
