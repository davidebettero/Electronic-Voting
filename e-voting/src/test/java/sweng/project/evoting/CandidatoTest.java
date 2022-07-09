package sweng.project.evoting;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CandidatoTest {
	@Test
	public void testNameNotNull() {
		Candidato c = new Candidato("Mario", "Rossi", "prova");
		String result = c.getNome();
		assertNotNull(result);
	}
	
	@Test
	public void testSurnameNotNull() {
		Candidato c = new Candidato("Mario", "Rossi", "prova");
		String result = c.getCognome();
		assertNotNull(result);
	}
	
	@Test
	public void testPartito() {
		Candidato c = new Candidato("Mario", "Rossi", "prova");
		String result = c.getPartito();
		assertEquals("prova", result);
	}
	
	@Test
	public void testPartitoNull() {
		Candidato c = new Candidato("Mario", "Rossi", null);
		String partito = "Candidato votazione ordinale (non indicato il partito)";
		assertEquals(c.getPartito(), partito);
	}
	
	@Test
	public void testEqualsFalse() {
		Candidato c1 = new Candidato("Mario", "Rossi", "prova1");
		Candidato c2 = new Candidato("Mario", "Rossi", "prova2");
		assertFalse(c1.equals(c2));
	}
	
	@Test
	public void testEqualsTrue() {
		Candidato c1 = new Candidato("Mario", "Rossi", "prova");
		Candidato c2 = new Candidato("Mario", "Rossi", "prova");
		assertTrue(c1.equals(c2));
	}
}
