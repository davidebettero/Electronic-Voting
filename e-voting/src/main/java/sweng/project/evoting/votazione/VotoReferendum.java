package sweng.project.evoting.votazione;

public class VotoReferendum extends Voto{
    
    private Boolean scelta;

    public VotoReferendum(String id, String idVotazione, Boolean scelta){
        super(id,idVotazione);
        
        this.scelta=scelta;
    }

    public Boolean getPreferenza(){
        return this.scelta;
    }
}
