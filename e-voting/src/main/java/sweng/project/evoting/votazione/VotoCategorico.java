package sweng.project.evoting.votazione;

import java.util.Objects;

public class VotoCategorico extends Voto {
    
    private final String partito;
    private final String candidatoPrincipale;
    private final String candidatoUno;
    private final String candidatoDue;

    public VotoCategorico(final String idVotazione, final String candidatoPrincipale, final String partito, final String candidatoUno, final String candidatoDue){
        super(idVotazione);
        
        if(Objects.requireNonNull(partito).trim().isEmpty() && Objects.requireNonNull(candidatoPrincipale).trim().isEmpty())
        	throw new IllegalArgumentException("Deve essere specificato almeno o il partito o il candidato principale");
        
        this.partito = partito;
        this.candidatoPrincipale = candidatoPrincipale;
        this.candidatoUno = (candidatoUno == null) ? "" : candidatoUno;
        this.candidatoDue = (candidatoDue == null) ? "" : candidatoDue;
    }

    //restituisce un array di stringhe di 4 elementi con le scelte di voto
    public String[] getPreferenza(){
        return new String[]{
        		(candidatoPrincipale.trim().isEmpty()) ? "Candidato: " : "Candidato: " + candidatoPrincipale, 
        		(partito.trim().isEmpty()) ? "Partito: " : "Partito: " + partito, 
        		(candidatoUno.trim().isEmpty()) ? "Candidato di partito 1: " : "Candidato di partito 1: " + candidatoUno, 
        		(candidatoDue.trim().isEmpty()) ? "Candidato di partito 2: " : "Candidato di partito 2: " + candidatoDue
        };
    }
    
    @Override
    public String toString() {
    	String res = String.format("VOTO CATEGORICO\nID votazione: %s\n", super.getVotazione());
    	String[] p = getPreferenza();
    	for(int i = 0; i < p.length; i++) 
    		if(!p[i].trim().isEmpty()) res += p[i].trim() + "\n";
    	
    	return res;
    }
    
    @Override
    public boolean equals(Object obj) {
    	if(obj instanceof VotoCategorico) {
    		VotoCategorico vc = (VotoCategorico) obj;
    		return vc.candidatoPrincipale.equalsIgnoreCase(this.candidatoPrincipale) && vc.partito.equalsIgnoreCase(this.partito) && vc.candidatoUno.equalsIgnoreCase(this.candidatoUno) && vc.candidatoDue.equalsIgnoreCase(this.candidatoDue);
    	}
    	return false;
    }
}
