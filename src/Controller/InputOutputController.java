package Controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import Model.*;

public class InputOutputController {

	private Dizionario dizionario;
	private String nomeFileSorgente;
	private String nomeFileOutput;
	
	
	// Constructors
	//
	// InputOutputController WITHOUT dictionary defined
	public InputOutputController(String nomeFileSorgente, String nomeFileOutput) {
		this.dizionario = new Dizionario();
		this.nomeFileSorgente = nomeFileSorgente;
		this.nomeFileOutput = nomeFileOutput;
	}

	// InputOutputController WITH dictionary defined
	public InputOutputController(Dizionario dizionario,	String nomeFileSorgente, String nomeFileOutput) {
		this.dizionario = dizionario;
		this.nomeFileSorgente = nomeFileSorgente;
		this.nomeFileOutput = nomeFileOutput;
	}


	// getters
	//
	public Dizionario getDizionario() {
		return dizionario;
	}

	public String getNomeFileSorgente() {
		return nomeFileSorgente;
	}

	public String getNomeFileOutput() {
		return nomeFileOutput;
	}
	
	
	// setters
	//
	public void setDizionario(Dizionario dizionario) {
		this.dizionario = dizionario;
	}

	public void setNomeFileSorgente(String nomeFileSorgente) {
		this.nomeFileSorgente = nomeFileSorgente;
	}

	public void setNomeFileOutput(String nomeFileOutput) {
		this.nomeFileOutput = nomeFileOutput;
	}

	
	//
	// METHODS
	//
	// read dictionary (refer to dictionary filename specified in IOC object)
	public Dizionario leggiDizionario() {
		BufferedReader buf = null;
		try {
			buf = new BufferedReader(new FileReader(nomeFileSorgente));
		} catch (FileNotFoundException e) {
			System.err.println("It is not possible to read the file \"" + nomeFileSorgente + "\".");
		}
		
		String nomeLetto = "";
		try {
			nomeLetto = buf.readLine();
		} catch (IOException e) {
			System.err.println("It is not possible to read the name of the dictionary from the file \"" 
									+ nomeFileSorgente + "\".");
		}
		
		List<Sostituzione> listaSostituzioniLetto = new ArrayList<Sostituzione>();
		
		String temp = "";
		try {
			temp = buf.readLine();
		} catch (IOException e) {
			System.err.println("It is not possible to read the substitions from the file \"" 
					+ nomeFileSorgente + "\".");
		}
		
		StringTokenizer t;
	
		while ( temp != null ){
			t = new StringTokenizer(temp, ",");     
			
			String tempCharOriginale = t.nextToken();
			String tempCharCriptata = t.nextToken();
			
			Sostituzione tempSostituzione = new Sostituzione(tempCharOriginale, tempCharCriptata);
			listaSostituzioniLetto.add(tempSostituzione);
			
			try {
				temp = buf.readLine();
			} catch (IOException e) {
				System.err.println("It is not possible to read the substitions from the file \"" 
						+ nomeFileSorgente + "\".");
			}         
		}
		
		try {
			buf.close();
		} catch (IOException e) {
			System.err.println("It is not possible to close the file \"" + nomeFileSorgente + "\".");
		}
		
		Dizionario dizionarioLetto = new Dizionario(nomeLetto,listaSostituzioniLetto);
		return dizionarioLetto;
		
	}

	
	// write output/log file of encryptions/decryptions (and type of change=encryption/decryption)
	// to output file specified in IOC object --> output file appended with new conversion each time
	public void scriviLog(String parolaInput, String parolaOutput, String tipo) {
		PrintWriter pr = null;
		try {
			pr = new PrintWriter(new FileWriter(nomeFileOutput,true));  
		} catch (IOException e) {
			System.err.println("It is not possible to create the file \"" + nomeFileOutput + "\".");
		}
	
		pr.append(parolaInput + " --> " + tipo + " --> " + parolaOutput + "\r\n");
		
		pr.close();
		
	}
	
	
	// encrypt whole word
	public String criptaParola(String parolaOriginale) {  
		
		String tempCharOriginale = "";
		String tempCharCriptata = "";
		String tempParolaCriptata = "";
		
		int lenParolaOriginale = parolaOriginale.length();
		
		for (int i=0; i < lenParolaOriginale; i++){
			tempCharOriginale = parolaOriginale.substring(i, i+1);
			try{
				tempCharCriptata = dizionario.criptaChar(tempCharOriginale);
			}catch (SimboloNonTrovatoException e) {
				System.err.println(e.getMessage());
				// if character not found in dictionary, it is left unchanged in output
				tempCharCriptata = tempCharOriginale;		
			}
			tempParolaCriptata = tempParolaCriptata + tempCharCriptata;
		}
		
		return tempParolaCriptata;		
	}

	
	// decrypt whole word
	public String decriptaParola(String parolaCriptata) {  
		
		String tempCharOriginale = "";
		String tempCharCriptata = "";
		String tempParolaOriginale = "";
		
		int lenParolaCriptata = parolaCriptata.length();
		
		for (int i=0; i < lenParolaCriptata; i++){
			tempCharCriptata = parolaCriptata.substring(i, i+1);
			try{
				tempCharOriginale = dizionario.decriptaChar(tempCharCriptata);
			}catch (SimboloNonTrovatoException e) {
				System.err.println(e.getMessage());
				// if character not found in dictionary, it is left unchanged in output
				tempCharOriginale = tempCharCriptata;
			}
			tempParolaOriginale = tempParolaOriginale + tempCharOriginale;
		}
		
		return tempParolaOriginale;		
	}
	
	
	
	//////////////////////////////////////////////
	@Override
	public String toString() {
		return "InputOutputController [dizionario=" + dizionario
				+ ", nomeFileSorgente=" + nomeFileSorgente
				+ ", nomeFileOutput=" + nomeFileOutput + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dizionario == null) ? 0 : dizionario.hashCode());
		result = prime * result
				+ ((nomeFileOutput == null) ? 0 : nomeFileOutput.hashCode());
		result = prime
				* result
				+ ((nomeFileSorgente == null) ? 0 : nomeFileSorgente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InputOutputController other = (InputOutputController) obj;
		if (dizionario == null) {
			if (other.dizionario != null)
				return false;
		} else if (!dizionario.equals(other.dizionario))
			return false;
		if (nomeFileOutput == null) {
			if (other.nomeFileOutput != null)
				return false;
		} else if (!nomeFileOutput.equals(other.nomeFileOutput))
			return false;
		if (nomeFileSorgente == null) {
			if (other.nomeFileSorgente != null)
				return false;
		} else if (!nomeFileSorgente.equals(other.nomeFileSorgente))
			return false;
		return true;
	}	

	
	
}
