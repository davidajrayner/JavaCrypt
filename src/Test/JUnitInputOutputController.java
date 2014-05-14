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
	// the characters " " (space) and "è" should not be changed in the output 
	// as they are not present in the dictionary
	public void testCriptaParola() {
		String parolaInput = "PrOvA (37)è";
		String parolaOutput = ioc.criptaParola(parolaInput);
		assertEquals("BfAlC 3)'2è", parolaOutput);
	}

	@Test
	// the characters "à" and "é" should not be changed in the output
	// as they are not present in the dictionary
	public void testDecriptaParola() {
		String parolaInput = "BfAlCà3)'2é";
		String parolaOutput = ioc.decriptaParola(parolaInput);
		assertEquals("PrOvAà(37)é", parolaOutput);
	}
	
	
}
