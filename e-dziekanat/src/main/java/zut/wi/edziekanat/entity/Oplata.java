package zut.wi.edziekanat.entity;

import java.math.BigDecimal;

public class Oplata 
{
	Integer Id;
	
	String Tytul;
	
	Integer Konto;
	
	BigDecimal Kwota;

	public Oplata(String tytul, Integer konto, BigDecimal kwota) {
		Tytul = tytul;
		Konto = konto;
		Kwota = kwota;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getTytul() {
		return Tytul;
	}

	public void setTytul(String tytul) {
		Tytul = tytul;
	}

	public Integer getKonto() {
		return Konto;
	}

	public void setKonto(Integer konto) {
		Konto = konto;
	}

	public BigDecimal getKwota() {
		return Kwota;
	}

	public void setKwota(BigDecimal kwota) {
		Kwota = kwota;
	}
	
	

}
