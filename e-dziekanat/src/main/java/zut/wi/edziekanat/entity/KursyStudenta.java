package zut.wi.edziekanat.entity;

public class KursyStudenta
{
	String nazwa;
	
	String prowadzacy;
	
	int eCTS;
	
	String formaZaliczenia;
	
	String formaZajec;
	
	int liczbaGodzin;
	
	StudentOceny Ocena;

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getProwadzacy() {
		return prowadzacy;
	}

	public void setProwadzacy(String prowadzacy) {
		this.prowadzacy = prowadzacy;
	}

	public int geteCTS() {
		return eCTS;
	}

	public void seteCTS(int eCTS) {
		this.eCTS = eCTS;
	}

	public String getFormaZaliczenia() {
		return formaZaliczenia;
	}

	public void setFormaZaliczenia(String formaZaliczenia) {
		this.formaZaliczenia = formaZaliczenia;
	}

	public String getFormaZajec() {
		return formaZajec;
	}

	public void setFormaZajec(String formaZajec) {
		this.formaZajec = formaZajec;
	}

	public int getLiczbaGodzin() {
		return liczbaGodzin;
	}

	public void setLiczbaGodzin(int liczbaGodzin) {
		this.liczbaGodzin = liczbaGodzin;
	}

	public StudentOceny getOcena() {
		return Ocena;
	}

	public void setOcena(StudentOceny ocena) {
		Ocena = ocena;
	}
	
	
	

}
