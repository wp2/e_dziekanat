package zut.wi.edziekanat.entity;

public class StudentOceny
{
	Integer Id;
	
	String Dydaktyk;
	
	String Forma;
	
	String FormaZaliczenia;
	
	String Kurs;
	
	int LiczbaGodzin;
	
	float ITermin;
	
	float IITermin;
	
	float IPoprawka;
	
	float IIPoprawka;
	
	float Komisja;

	public StudentOceny(String dydaktyk, String forma, String kurs, float iTermin, float iITermin, float iPoprawka,
			float iIPoprawka, float komisja) {
		Dydaktyk = dydaktyk;
		Forma = forma;
		Kurs = kurs;
		ITermin = iTermin;
		IITermin = iITermin;
		IPoprawka = iPoprawka;
		IIPoprawka = iIPoprawka;
		Komisja = komisja;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getDydaktyk() {
		return Dydaktyk;
	}

	public void setDydaktyk(String dydaktyk) {
		Dydaktyk = dydaktyk;
	}

	public String getForma() {
		return Forma;
	}

	public void setForma(String forma) {
		Forma = forma;
	}

	public String getKurs() {
		return Kurs;
	}

	public void setKurs(String kurs) {
		Kurs = kurs;
	}

	public float getITermin() {
		return ITermin;
	}

	public void setITermin(float iTermin) {
		ITermin = iTermin;
	}

	public float getIITermin() {
		return IITermin;
	}

	public void setIITermin(float iITermin) {
		IITermin = iITermin;
	}

	public float getIPoprawka() {
		return IPoprawka;
	}

	public void setIPoprawka(float iPoprawka) {
		IPoprawka = iPoprawka;
	}

	public float getIIPoprawka() {
		return IIPoprawka;
	}

	public void setIIPoprawka(float iIPoprawka) {
		IIPoprawka = iIPoprawka;
	}

	public float getKomisja() {
		return Komisja;
	}

	public void setKomisja(float komisja) {
		Komisja = komisja;
	}

	public int getLiczbaGodzin() {
		return LiczbaGodzin;
	}

	public void setLiczbaGodzin(int liczbaGodzin) {
		LiczbaGodzin = liczbaGodzin;
	}

	public String getFormaZaliczenia() {
		return FormaZaliczenia;
	}

	public void setFormaZaliczenia(String formaZaliczenia) {
		FormaZaliczenia = formaZaliczenia;
	}
	
	
	
	

}
