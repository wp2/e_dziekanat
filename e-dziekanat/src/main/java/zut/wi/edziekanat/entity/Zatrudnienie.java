package zut.wi.edziekanat.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Zatrudnienie 
{
	String Imie;
	
	String Nazwisko;
	
	Integer Id;
	
	String TypUmowy;
	
	BigDecimal Zarobki;
	
	Date UmowaOd;
	
	Date UmowaDo;

	public Zatrudnienie(String imie, String nazwisko, Integer id, String typUmowy, BigDecimal zarobki, Date umowaOd,
			Date umowaDo) {
		Imie = imie;
		Nazwisko = nazwisko;
		Id = id;
		TypUmowy = typUmowy;
		Zarobki = zarobki;
		UmowaOd = umowaOd;
		UmowaDo = umowaDo;
	}

	public String getImie() {
		return Imie;
	}

	public void setImie(String imie) {
		Imie = imie;
	}

	public String getNazwisko() {
		return Nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		Nazwisko = nazwisko;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getTypUmowy() {
		return TypUmowy;
	}

	public void setTypUmowy(String typUmowy) {
		TypUmowy = typUmowy;
	}

	public BigDecimal getZarobki() {
		return Zarobki;
	}

	public void setZarobki(BigDecimal zarobki) {
		Zarobki = zarobki;
	}

	public Date getUmowaOd() {
		return UmowaOd;
	}

	public void setUmowaOd(Date umowaOd) {
		UmowaOd = umowaOd;
	}

	public Date getUmowaDo() {
		return UmowaDo;
	}

	public void setUmowaDo(Date umowaDo) {
		UmowaDo = umowaDo;
	}
	
	
	

}
