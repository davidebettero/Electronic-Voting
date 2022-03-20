package sweng.project.evoting.votazione;

import java.util.Objects;

public class Voto {

    private String idVotazione;
    private String idVoto;

    public Voto(String idVotazione, String idVoto) {
    	this.idVotazione = Objects.requireNonNull(idVotazione);
    	this.idVoto = Objects.requireNonNull(idVoto);
    }

    //effects: restituisce l'id della votazione
    public String getVotazione(){
        return this.idVotazione;
    }

    //effects: restituisce l'id del voto this
    public String getId(){
        return this.idVoto;
    }
}
