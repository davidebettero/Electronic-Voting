package sweng.project.evoting;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AmministratoreTest {
	
	@Test
	public void testNameNotNull() {
		Amministratore a = new Amministratore("Luca", "lv");
		String result = a.getName();
		assertNotNull(result);
	}
	
	@Test
	public void testSurnameNotNull() {
		Amministratore a = new Amministratore("Luca", "lv");
		String result = a.getSurname();
		assertNotNull(result);
	}
	
	@Test
	public void testTaxCodeLengthFalse() {
		Amministratore a = new Amministratore("Luca", "lv");
		String result = a.getTaxCode();
		assertNotEquals(17, result.length());
	}
	
	@Test
	public void testTaxCodeLengthTrue() {
		Amministratore a = new Amministratore("Luca", "lv");
		String result = a.getTaxCode();
		assertEquals(16, result.length());
	}
	
	@Test
	public void testTaxCodeNotNull() {
		Amministratore a = new Amministratore("Luca", "lv");
		String result = a.getTaxCode();
		assertNotNull(result);
	}
}
