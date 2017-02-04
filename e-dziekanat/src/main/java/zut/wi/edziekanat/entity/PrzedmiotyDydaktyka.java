package zut.wi.edziekanat.entity;

public class PrzedmiotyDydaktyka 
{
	Integer formaProwadzącegoId;
	
	String Nazwa;
	
	String FormaZajec;
	
	String FormaZaliczenia;
	
	Integer LiczbaGodzin;
	
	String Grupa;

	public PrzedmiotyDydaktyka(String nazwa, String formaZajec, String formaZaliczenia, Integer liczbaGodzin,
			String grupa) {
		Nazwa = nazwa;
		FormaZajec = formaZajec;
		FormaZaliczenia = formaZaliczenia;
		LiczbaGodzin = liczbaGodzin;
		Grupa = grupa;
	}

	public String getNazwa() {
		return Nazwa;
	}

	public void setNazwa(String nazwa) {
		Nazwa = nazwa;
	}

	public String getFormaZajec() {
		return FormaZajec;
	}

	public void setFormaZajec(String formaZajec) {
		FormaZajec = formaZajec;
	}

	public String getFormaZaliczenia() {
		return FormaZaliczenia;
	}

	public void setFormaZaliczenia(String formaZaliczenia) {
		FormaZaliczenia = formaZaliczenia;
	}

	public Integer getLiczbaGodzin() {
		return LiczbaGodzin;
	}

	public void setLiczbaGodzin(Integer liczbaGodzin) {
		LiczbaGodzin = liczbaGodzin;
	}

	public String getGrupa() {
		return Grupa;
	}

	public void setGrupa(String grupa) {
		Grupa = grupa;
	}

	public Integer getFormaProwadzącegoId() {
		return formaProwadzącegoId;
	}

	public void setFormaProwadzącegoId(Integer formaProwadzącegoId) {
		this.formaProwadzącegoId = formaProwadzącegoId;
	}
	
	public String getPrzedmiotOpis()
	{
		return "Kurs : " + this.Nazwa + "  " + "Forma Zajęć : " + this.FormaZajec + "   Forma Zaliczenia:" + this.FormaZaliczenia;
	}
	
	

}
