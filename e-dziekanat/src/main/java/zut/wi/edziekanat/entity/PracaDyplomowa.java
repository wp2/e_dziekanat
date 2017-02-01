package zut.wi.edziekanat.entity;

import java.util.Date;

public class PracaDyplomowa 
{
	Integer Id;
	
	String Student;
	
	String Dydaktyk;
	
	String TytulPracy;
	
	String PierwszyRecenzent;
	
	String DrugiRecenzent;
	
	float PierwszaOcena;
	
	float DrugaOcena;
	
	Date TerminZlozeniaPracy;
	
	Date DataZlozeniaPracy;
	
	String Przewodnicacy;
	
	Date DataObrony;
	
	

	public PracaDyplomowa(String student, String dydaktyk, String tytulPracy, String pierwszyRecenzent,
			String drugiRecenzent, float pierwszaOcena, float drugaOcena, Date terminZlozeniaPracy,
			Date dataZlozeniaPracy, String przewodnicacy, Date dataObrony) {
		Student = student;
		Dydaktyk = dydaktyk;
		TytulPracy = tytulPracy;
		PierwszyRecenzent = pierwszyRecenzent;
		DrugiRecenzent = drugiRecenzent;
		PierwszaOcena = pierwszaOcena;
		DrugaOcena = drugaOcena;
		TerminZlozeniaPracy = terminZlozeniaPracy;
		DataZlozeniaPracy = dataZlozeniaPracy;
		Przewodnicacy = przewodnicacy;
		DataObrony = dataObrony;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getStudent() {
		return Student;
	}

	public void setStudent(String student) {
		Student = student;
	}

	public String getDydaktyk() {
		return Dydaktyk;
	}

	public void setDydaktyk(String dydaktyk) {
		Dydaktyk = dydaktyk;
	}

	public String getTytulPracy() {
		return TytulPracy;
	}

	public void setTytulPracy(String tytulPracy) {
		TytulPracy = tytulPracy;
	}

	public String getPierwszyRecenzent() {
		return PierwszyRecenzent;
	}

	public void setPierwszyRecenzent(String pierwszyRecenzent) {
		PierwszyRecenzent = pierwszyRecenzent;
	}

	public String getDrugiRecenzent() {
		return DrugiRecenzent;
	}

	public void setDrugiRecenzent(String drugiRecenzent) {
		DrugiRecenzent = drugiRecenzent;
	}

	public float getPierwszaOcena() {
		return PierwszaOcena;
	}

	public void setPierwszaOcena(float pierwszaOcena) {
		PierwszaOcena = pierwszaOcena;
	}

	public float getDrugaOcena() {
		return DrugaOcena;
	}

	public void setDrugaOcena(float drugaOcena) {
		DrugaOcena = drugaOcena;
	}

	public Date getTerminZlozeniaPracy() {
		return TerminZlozeniaPracy;
	}

	public void setTerminZlozeniaPracy(Date terminZlozeniaPracy) {
		TerminZlozeniaPracy = terminZlozeniaPracy;
	}

	public Date getDataZlozeniaPracy() {
		return DataZlozeniaPracy;
	}

	public void setDataZlozeniaPracy(Date dataZlozeniaPracy) {
		DataZlozeniaPracy = dataZlozeniaPracy;
	}

	public String getPrzewodnicacy() {
		return Przewodnicacy;
	}

	public void setPrzewodnicacy(String przewodnicacy) {
		Przewodnicacy = przewodnicacy;
	}

	public Date getDataObrony() {
		return DataObrony;
	}

	public void setDataObrony(Date dataObrony) {
		DataObrony = dataObrony;
	}
	
	
	
	

}
