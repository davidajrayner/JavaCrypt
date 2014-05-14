package Model;

import java.util.*;

public class Dizionario {
	
	private String nome;     						// name of dictionary             
	private List<Sostituzione> listaSostituzioni;   // list of substitutions: charOriginale, charCriptata
	
	
	// Constructors
	//
	// empty dictionary --> no name, no list of substitutions
	public Dizionario(){											
		this.nome = "";
		this.listaSostituzioni = new ArrayList<Sostituzione>();
	}
	
	// complete dictionary with name and list of substitutions
	public Dizionario(String nome, List<Sostituzione> listaSostituzioni){	     
		this.nome = nome;
		this.listaSostituzioni = listaSostituzioni;
	}


	// getters
	//
	public String getNome() {
		return nome;
	}

	public List<Sostituzione> getListaSostituzioni() {
		return listaSostituzioni;
	}

	
	//setters
	//
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setListSostituzioni(List<Sostituzione> listaSostituzioni) {
		this.listaSostituzioni = listaSostituzioni;
	}
	
	
	
	//
	// METHODS
	//
	// encrypt a single character
	public String criptaChar(String charOriginale) throws SimboloNonTrovatoException {  
		Sostituzione tempSostituzione;
		String tempCharOriginale;
		String tempCharCriptata;
		String charCriptata = "";
			
		for (int i=0; i < (listaSostituzioni.size() - 1); i++){
			tempSostituzione = listaSostituzioni.get(i);
			tempCharOriginale = tempSostituzione.getCharOriginale();
			tempCharCriptata = tempSostituzione.getCharCriptata();
		
			if (tempCharOriginale.equals(charOriginale)){
				charCriptata = tempCharCriptata;
			}	
		}
		
		if ( charCriptata != "" ){                
			return charCriptata;
		}else{
			throw new SimboloNonTrovatoException("The symbol '" + charOriginale 
					+ "' cannot be found in the dictionary, so it will be left unchanged in the output.");
		}
		
	}
	
	
	// decrypt a single character
	public String decriptaChar(String charCriptata) throws SimboloNonTrovatoException {  
		Sostituzione tempSostituzione;
		String tempCharOriginale;
		String tempCharCriptata;
		String charOriginale = "";
			
		for (int i=0; i < (listaSostituzioni.size() - 1); i++){
			tempSostituzione = listaSostituzioni.get(i);
			tempCharOriginale = tempSostituzione.getCharOriginale();
			tempCharCriptata = tempSostituzione.getCharCriptata();
		
			if (tempCharCriptata.equals(charCriptata)){
				charOriginale = tempCharOriginale;
			}	
		}
		
		if ( charOriginale != "" ){                
			return charOriginale;
		}else{
			throw new SimboloNonTrovatoException("The symbol '" + charCriptata 
					+ "' cannot be found in the dictionary, so it will be left unchanged in the output.");
		}

	}

	
	//////////////////////////////////////////////
	@Override
	public String toString() {
		return "Dizionario [nome=" + nome + ", listaSostituzioni="
				+ listaSostituzioni + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((listaSostituzioni == null) ? 0 : listaSostituzioni
						.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Dizionario other = (Dizionario) obj;
		if (listaSostituzioni == null) {
			if (other.listaSostituzioni != null)
				return false;
		} else if (!listaSostituzioni.equals(other.listaSostituzioni))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	
	
	
}
