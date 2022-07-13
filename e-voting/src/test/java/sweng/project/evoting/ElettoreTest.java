package sweng.project.evoting;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ElettoreTest {
	
	@Test
	public void testDateOfBirthValid() {
		Elettore e = new Elettore("Davide", "db");
		boolean result = e.isDateOfBirthValid();
		assertTrue(result);
	}
	
	@Test
	public void testIsOfAge() {
		Elettore e = new Elettore("Davide", "db");
		boolean result = e.isOfAge();
		assertTrue(result);
	}
	
	@Test
	public void testNameNotNull() {
		Elettore e = new Elettore("Andrea", "ab");
		String result = e.getName();
		assertNotNull(result);
	}
	
	@Test
	public void testSurnameNotNull() {
		Elettore e = new Elettore("Andrea", "ab");
		String result = e.getSurname();
		assertNotNull(result);
	}
	
	@Test
	public void testTaxCodeLengthTrue() {
		Elettore e = new Elettore("Davide", "db");
		String result = e.getTaxCode();
		assertEquals(16, result.length());
	}
	
	@Test
	public void testTaxCodeLengthFalse() {
		Elettore e = new Elettore("Andrea", "ab");
		String result = e.getTaxCode();
		assertNotEquals(15, result.length());
	}
	
	@Test
	public void testTaxCodeNotNull() {
		Elettore e = new Elettore("Davide", "db");
		String result = e.getTaxCode();
		assertNotNull(result);
	}

	@Test
	public void testLeapYear2020() {
		boolean result = Elettore.isLeapYear(2020);
		assertTrue(result);
	}
	
	@Test
	public void testLeapYear2019() {
		boolean result = Elettore.isLeapYear(2019);
		assertFalse(result);
	}
	
	@Test
	public void testLeapYear1900() {
		boolean result = Elettore.isLeapYear(1900);
		assertFalse(result);
	}
	
	@Test
	public void testLeapYear2000() {
		boolean result = Elettore.isLeapYear(2000);
		assertTrue(result);
	}
	
	@Test
	public void testLeapYear2012() {
		boolean result = Elettore.isLeapYear(2012);
		assertTrue(result);
	}
	
	@Test
	public void testLeapYear1999() {
		boolean result = Elettore.isLeapYear(1999);
		assertFalse(result);
	}

}
