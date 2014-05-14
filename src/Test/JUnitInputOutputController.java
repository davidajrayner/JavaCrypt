package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Controller.InputOutputController;
import Model.Dizionario;

public class JUnitInputOutputController {
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
	public void testGetDizionario() {
		String nome = ioc.getDizionario().getNome();
		int size = ioc.getDizionario().getListaSostituzioni().size();	
		assertEquals("Cipher", nome);		
		assertTrue(size == 72);
	}

	@Test
	public void testGetNomeFileSorgente() {
		String nomeFileSorgente = ioc.getNomeFileSorgente();
		assertEquals("Dizionario_cipher.txt", nomeFileSorgente);	
	}

	@Test
	public void testGetNomeFileOutput() {
		String nomeFileOutput = ioc.getNomeFileOutput();
		assertEquals("Output.txt", nomeFileOutput);	
	}

	@Test
	// the characters " " (space) and "�" should not be changed in the output 
	// as they are not present in the dictionary
	public void testCriptaParola() {
		String parolaInput = "PrOvA (37)�";
		String parolaOutput = ioc.criptaParola(parolaInput);
		assertEquals("BfAlC 3)'2�", parolaOutput);
	}

	@Test
	// the characters "�" and "�" should not be changed in the output
	// as they are not present in the dictionary
	public void testDecriptaParola() {
		String parolaInput = "BfAlC�3)'2�";
		String parolaOutput = ioc.decriptaParola(parolaInput);
		assertEquals("PrOvA�(37)�", parolaOutput);
	}
	
	
}
