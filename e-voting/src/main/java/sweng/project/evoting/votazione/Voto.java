package sweng.project.evoting.votazione;

import java.util.Objects;

public class Voto {

    private String idVotazione;

    public Voto(String idVotazione) {
    	if(Objects.requireNonNull(idVotazione).isEmpty() || Objects.requireNonNull(idVotazione).isBlank())
    		throw new IllegalArgumentException("Deve essere indicato l'ID della votazione a cui appartiene questo voto");
    	
    	this.idVotazione = idVotazione;
    }

    //effects: restituisce l'id della votazione
    public String getVotazione(){
        return this.idVotazione;
    }
}
