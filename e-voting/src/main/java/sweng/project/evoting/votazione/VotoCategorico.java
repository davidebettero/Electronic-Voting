package sweng.project.evoting.votazione;

public class VotoCategorico extends Voto {
    
    private String partito;
    private String candidatoPrincipale;
    private String candidatoUno;
    private String candidatoDue;

    public VotoCategorico(String idVotazione, String partito, String candidatoPrincipale, String candidatoUno, String candidatoDue){
        super(idVotazione);

        this.partito = partito;
        this.candidatoPrincipale = candidatoPrincipale;
        this.candidatoUno = candidatoUno;
        this.candidatoDue = candidatoDue;
    }

    //restituisce un array di stringhe di 4 elementi con le scelte di voto
    public String[] getPreferenza(){
        return new String[]{partito, candidatoPrincipale, candidatoUno, candidatoDue};
    }
}
