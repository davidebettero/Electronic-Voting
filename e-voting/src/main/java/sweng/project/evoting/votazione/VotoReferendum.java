package sweng.project.evoting.votazione;

import java.util.Objects;

public class VotoReferendum extends Voto{
    
    private final String scelta;

    public VotoReferendum(final String idVotazione, final String scelta){
        super(idVotazione);
        
        if(Objects.requireNonNull(scelta).isEmpty() || Objects.requireNonNull(scelta).isBlank())
        	throw new IllegalArgumentException("Deve essere indicata una scelta");
        if(!scelta.equalsIgnoreCase("si") && !scelta.equalsIgnoreCase("no") && !scelta.equalsIgnoreCase("scheda bianca"))
        	throw new IllegalArgumentException("Scelta non valida");
        this.scelta = scelta;
    }

    public String getPreferenza(){
        return this.scelta;
    }
}
