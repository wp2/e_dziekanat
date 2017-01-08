package zut.wi.edziekanat.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student implements Serializable
{	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Album" , updatable = false) // PK nigdy nie powinien być edytowalny
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int album;
	
	@Column(name="Imie")
	String imie;
	
	@Column(name="Nazwisko")
	String nazwisko;
	
	@Column(name="Login")
	String login;
	
	@Column(name="Haslo")
	String haslo;
	
	@Column(name="Plec")
	String plec;
	
	@Column(name="DataUrodzenia")
	Date dataUrodzenia;
	
	@Column(name="Adres")
	String adres;

	public int getAlbum() {
		return album;
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

	

	

	
	

}
