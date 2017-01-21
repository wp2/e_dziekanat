package zut.wi.edziekanat.entity;

import java.io.Serializable;
import java.util.Date;





public class Student implements Serializable
{	
	private static final long serialVersionUID = 1L;

	
	String album;
	
	
	String imie;
	

	String nazwisko;
	
	
	String login;
	
	
	String haslo;
	
	
	String plec;	
	
	Date dataUrodzenia;	

	String adres;
	
	String grupaLab;
	
	String kierunek;
	
	String specjalnosc;


	public String getAlbum() {
		return album;
	}		
	
	public void setAlbum(String album) {
		this.album = album;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getHaslo() {
		return haslo;
	}

	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}

	public String getPlec() {
		return plec;
	}

	public void setPlec(String plec) {
		this.plec = plec;
	}

	public Date getDataUrodzenia() {
		return dataUrodzenia;
	}

	public void setDataUrodzenia(Date dataUrodzenia) {
		this.dataUrodzenia = dataUrodzenia;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getGrupaLab() {
		return grupaLab;
	}

	public void setGrupaLab(String grupaLab) {
		this.grupaLab = grupaLab;
	}

	public String getKierunek() {
		return kierunek;
	}

	public void setKierunek(String kierunek) {
		this.kierunek = kierunek;
	}

	public String getSpecjalnosc() {
		return specjalnosc;
	}

	public void setSpecjalnosc(String specjalnosc) {
		this.specjalnosc = specjalnosc;
	}
	
	
	
	

	

	

	
	

}
