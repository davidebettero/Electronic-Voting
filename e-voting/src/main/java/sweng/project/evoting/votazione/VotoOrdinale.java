package sweng.project.evoting.votazione;

import java.util.List;
import java.util.Objects;

import sweng.project.evoting.Candidato;

public class VotoOrdinale extends Voto{
	private final List<Candidato> ordinePreferenze;

	public VotoOrdinale(final String idVotazione, final List<Candidato> ordinePreferenze) {
		super(idVotazione);
		
		if(Objects.requireNonNull(ordinePreferenze).size() <= 0)
			throw new IllegalArgumentException("Nessun candidato presente nella lista degli ordini di preferenza");
		
		this.ordinePreferenze = ordinePreferenze;
	}
	
	public List<Candidato> getOrdinePreferenze(){
		return ordinePreferenze;
	}
	
	@Override
	public String toString() {
		String res = new String();
		for(int i = 0; i < ordinePreferenze.size(); i++) {
			res += String.format("%d°: %s\n", (i+1), ordinePreferenze.get(i));
		}
		return String.format("VOTO ORDINALE\nID votazione: %s\nOrdine candidati:\n%s", super.getVotazione(), res);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof VotoOrdinale) {
			VotoOrdinale vo = (VotoOrdinale) obj;
			if(!super.getVotazione().equals(vo.getVotazione()) || vo.ordinePreferenze.size() != this.ordinePreferenze.size())
				return false;
			
			for(int i = 0; i < this.ordinePreferenze.size(); i++) {
				if(!this.ordinePreferenze.get(i).equals(vo.ordinePreferenze.get(i)))
					return false;
			}
			return true;
		}
		return false;
	}
}
