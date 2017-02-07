package zut.wi.edziekanat.entity;

import java.util.Date;

public class Ogloszenie
{
	String Tytul;
	
	Date DataDodania;
	
	String Tresc;
	
	
	
	public Ogloszenie(String tytul, Date dataDodania, String tresc) {
		Tytul = tytul;
		DataDodania = dataDodania;
		Tresc = tresc;
	}

	public String getTytul() {
		return Tytul;
	}

	public void setTytul(String tytul) {
		Tytul = tytul;
	}

	public Date getDataDodania() {
		return DataDodania;
	}

	public void setDataDodania(Date dataDodania) {
		DataDodania = dataDodania;
	}

	public String getTresc() {
		return Tresc;
	}

	public void setTresc(String tresc) {
		Tresc = tresc;
	}
	
	

}
