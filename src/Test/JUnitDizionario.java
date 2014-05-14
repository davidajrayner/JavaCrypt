package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Controller.*;
import Model.*;

public class JUnitDizionario {
	
	InputOutputController iocVuoto=null, ioc=null;
	Dizionario dizionarioLetto = null;
	
	
	@Before
	public void setUp() throws Exception {
		
		iocVuoto = new InputOutputController("Dizionario_cipher.txt", "Output.txt");
		dizionarioLetto = iocVuoto.leggiDizionario();
		ioc = new InputOutputController(dizionarioLetto, "Dizionario_cipher.txt", "Output.txt");
		
	}

	
	// 
	// TESTS - START   -->  (expected,actual) 
	//
	
	@Test
	public void testGetNome() {
		String nome = dizionarioLetto.getNome();
		assertEquals("Cipher", nome);
	}

	@Test
	public void testGetListaSostituzioni() {
		int size = dizionarioLetto.getListaSostituzioni().size();
		assertTrue(size == 72);
	}
	
	@Test
	public void testCriptaChar() throws SimboloNonTrovatoException {
		String charCriptataB = dizionarioLetto.criptaChar("B");
		String charCriptata3 = dizionarioLetto.criptaChar("3");
		String charCriptatax = dizionarioLetto.criptaChar("x");
		
		assertEquals("I", charCriptataB);
		assertEquals(")", charCriptata3);
		assertEquals("n", charCriptatax);
		}

	@Test
	public void testDecriptaChar() throws SimboloNonTrovatoException {
		String charDecriptataP = dizionarioLetto.decriptaChar("P");
		String charDecriptata8 = dizionarioLetto.decriptaChar("8");
		String charDecriptatal = dizionarioLetto.decriptaChar("l");
		
		assertEquals("C", charDecriptataP);
		assertEquals("\"", charDecriptata8);
		assertEquals("v", charDecriptatal);
	}
	
	
	//
	// TEST EXCEPTIONS 
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testCriptaCharException() throws SimboloNonTrovatoException {
		exception.expect(Model.SimboloNonTrovatoException.class);
		exception.expectMessage("The symbol 'ç' cannot be found in the dictionary, so it will be left unchanged in the output.");
		dizionarioLetto.criptaChar("ç");
	}
	
	@Test
	public void testDecriptaCharException() throws SimboloNonTrovatoException {
		exception.expect(Model.SimboloNonTrovatoException.class);
		exception.expectMessage("The symbol 'ù' cannot be found in the dictionary, so it will be left unchanged in the output.");
		dizionarioLetto.decriptaChar("ù");
	}
	
}
