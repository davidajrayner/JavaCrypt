package Model;

public class Sostituzione {

	private String charOriginale;			// original character
	private String charCriptata;			// encrypted character
	
	
	// constructor
	public Sostituzione(String charOriginale, String charCriptata){      
		this.charOriginale = charOriginale;
		this.charCriptata = charCriptata;
	}
	

	// getters 
	//
	public String getCharOriginale() {
		return charOriginale;
	}

	public String getCharCriptata() {
		return charCriptata;
	}

	
	// setters
	//
	public void setCharOriginale(String charOriginale) {
		this.charOriginale = charOriginale;
	}

	public void setCharCriptata(String charCriptata) {
		this.charCriptata = charCriptata;
	}


	//////////////////////////////////////////////
	@Override
	public String toString() {
		return "Sostituzione [charOriginale=" + charOriginale
				+ ", charCriptata=" + charCriptata + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((charCriptata == null) ? 0 : charCriptata.hashCode());
		result = prime * result
				+ ((charOriginale == null) ? 0 : charOriginale.hashCode());
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
		Sostituzione other = (Sostituzione) obj;
		if (charCriptata == null) {
			if (other.charCriptata != null)
				return false;
		} else if (!charCriptata.equals(other.charCriptata))
			return false;
		if (charOriginale == null) {
			if (other.charOriginale != null)
				return false;
		} else if (!charOriginale.equals(other.charOriginale))
			return false;
		return true;
	}


}
