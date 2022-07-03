package sweng.project.evoting.administrator;

import java.util.Objects;

public class RowLog {
	private String data, taxCode, azione;
	
	public RowLog(final String data, final String taxCode, final String azione) {
		if(Objects.requireNonNull(data).isEmpty() || Objects.requireNonNull(data).isBlank())
			throw new IllegalArgumentException("Data e ora non specificati");
		
		if(Objects.requireNonNull(taxCode).isEmpty() || Objects.requireNonNull(taxCode).isBlank())
			throw new IllegalArgumentException("Utente non specificato");
		
		if(Objects.requireNonNull(azione).isEmpty() || Objects.requireNonNull(azione).isBlank())
			throw new IllegalArgumentException("Azione non specificata");
		
		this.data = data;
		this.taxCode = taxCode;
		this.azione = azione;
	}
	
	public String getData() {
		return data;
	}
	
	public String getTaxCode() {
		return taxCode;
	}
	
	public String getAzione() {
		return azione;
	}
}
