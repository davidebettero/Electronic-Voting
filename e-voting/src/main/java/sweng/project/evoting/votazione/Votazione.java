package sweng.project.evoting.votazione;

import java.sql.Timestamp;
import java.util.UUID;
import sweng.project.evoting.DigitalVotingDaoImpl;

public class Votazione {
	
    private final String id;// identificativo della votazione
    private final String inizio; //inizio votazione
    private final String fine;//fine votazione
    private final String modVoto;//modalità di voto
    private final String modCalcolo;//modalità di voto

    public Votazione(String id,String modVoto,String modCalcolo,Timestamp inizio,Timestamp fine){
	    this.id=id;
	    this.fine=fine.toString();
	    this.inizio=inizio.toString();
	    id=UUID.randomUUID().toString(); 
	    this.modVoto= modVoto;
	    this.modCalcolo=modCalcolo;
	    insertVotazione();
    }

    // restituisce true se la votazione è attiva, false altrimenti
    public boolean isAttiva(Timestamp inizio, Timestamp fine){
        Timestamp ts= new Timestamp(1000);
        if (ts.before(inizio) || ts.after(fine)) return false;
        return true;
    }

    //aggiunge voto nel database
    public void addVoto(Voto v){
       
        DigitalVotingDaoImpl d=new DigitalVotingDaoImpl();
        switch (modVoto){
            case "Categorico":
				if (v instanceof VotoCategorico){
				    VotoCategorico vr=(VotoCategorico) v;
				    String[] scelte=vr.getPreferenza();
				    String idVoto=vr.getId();
				    d.addVotoCategorico(id, idVoto, scelte);
				        
				}
            case "Referendum":
                if (v instanceof VotoReferendum){
                    VotoReferendum vr=(VotoReferendum) v;
                    Boolean scelte=vr.getPreferenza();
                    String idVoto=vr.getId();
                    d.addVotoReferendum(id,idVoto,scelte);
                    
                }  
           
        }   
         
    }

    public void insertVotazione(){
        DigitalVotingDaoImpl d = new DigitalVotingDaoImpl();
        d.insertVotingSession(id, inizio, fine, modVoto, modCalcolo);
    }

    //restituisce la modalita di votazione
    public String getTipo(){
        return modVoto;
    }
    
}
