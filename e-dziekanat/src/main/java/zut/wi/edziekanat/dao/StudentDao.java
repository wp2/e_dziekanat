package zut.wi.edziekanat.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataAccessException;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import zut.wi.edziekanat.entity.KursyStudenta;
import zut.wi.edziekanat.entity.KursyStudentaRowMapper;
import zut.wi.edziekanat.entity.Oplata;
import zut.wi.edziekanat.entity.OplataRowMapper;
import zut.wi.edziekanat.entity.PracaDyplomowa;
import zut.wi.edziekanat.entity.Student;
import zut.wi.edziekanat.entity.StudentPracaDyplomowaMapper;
import zut.wi.edziekanat.entity.StudentRowMapper;

@Repository
public class StudentDao 
{

	@Autowired 
	NamedParameterJdbcTemplate namedJdbcTemplate;	
	
	public Student getStudentById(String Id)
	{
		try
		{
			Student student;
			MapSqlParameterSource queryParams = new MapSqlParameterSource();
			queryParams.addValue("album", Id);
			student = namedJdbcTemplate.queryForObject("SELECT * FROM Student WHERE Album = :album",queryParams, new StudentRowMapper()); 
			return student;
		}
		catch(IncorrectResultSizeDataAccessException e)
		{
			System.out.println("Record does not exsits");
			return null;
		}
		catch(DataAccessException e)
		{
			return null;
		}	
		
	}
	
	public Student getStudentDataById(String Id)
	{		
			Student student = new Student();
			MapSqlParameterSource queryParams = new MapSqlParameterSource();
			queryParams.addValue("album", Id);
			SqlRowSet rs1 = namedJdbcTemplate.queryForRowSet("SELECT *,k.Nazwa FROM Student s JOIN Kierunek k ON k.Id = s.IdKierunek "
					+ "WHERE s.Album = :album", queryParams);
			MapSqlParameterSource queryParams2 = new MapSqlParameterSource();			
			
			if(rs1.next())
			{
				student.setAlbum(rs1.getString("Album"));
				student.setPlec(rs1.getString("Plec"));
				student.setNazwisko(rs1.getString("Nazwisko"));
				student.setHaslo(rs1.getString("Haslo"));		
				student.setDataUrodzenia(rs1.getDate("DataUrodzenia"));
				student.setImie(rs1.getString("Imie"));
				student.setAdres(rs1.getString("Adres"));
				student.setGrupaLab(rs1.getString("IdGrupaLab"));
				student.setKierunek(rs1.getString("Nazwa"));				
				queryParams2.addValue("kierunek", rs1.getInt("IdKierunek"));
			}
			SqlRowSet rs2 = namedJdbcTemplate.queryForRowSet("SELECT Nazwa FROM Specjalnosc WHERE IdKierunek = :kierunek", queryParams2);
			if(rs2.next())
			{
				student.setSpecjalnosc(rs2.getString("Nazwa"));
			}
			return student;		
	}
	
	public Map<String,String> getStudentGrupaRocznikSemestr(String studentAlbum)
	{		
			MapSqlParameterSource queryParams = new MapSqlParameterSource();
			queryParams.addValue("album", studentAlbum);
			String SQL = "SELECT l.Id AS Lab , c.Id AS Cw , w.Id AS Wyk , r.RocznikAkademicki AS Rocz , s.NumerSemestru , s.TypSemestru FROM Student st"+
			" JOIN GrupaLab l ON st.IdGrupaLab = l.Id"+
			" JOIN GrupaCw c ON l.IdGrupaCw = c.Id"+
			" JOIN GrupaWyk w ON c.IdGrupaWyk = w.Id"+
			" JOIN Rocznik r ON w.IdRocznik = r.Id"+
			" JOIN Semestr s ON r.IdSemestr = s.NumerSemestru"+
			" WHERE st.Album = :album";
			
			SqlRowSet rowSet = namedJdbcTemplate.queryForRowSet(SQL, queryParams);
			Map<String,String> studentEduInfo = new HashMap<String,String>();
			if(rowSet.next())
			{
				studentEduInfo.put("Lab",rowSet.getString("Lab"));
				studentEduInfo.put("Cw",rowSet.getString("Cw"));
				studentEduInfo.put("Wyk",rowSet.getString("Wyk"));
				studentEduInfo.put("Rocz",rowSet.getString("Rocz"));
				studentEduInfo.put("NumerSemestru",rowSet.getString("NumerSemestru"));
				studentEduInfo.put("TypSemestru",rowSet.getString("TypSemestru"));
			}
			return studentEduInfo;		
		
	}
	
	
	
	public boolean editStudentById(Student student)
	{
		return true;
	}
	
	public Student addStudent(Student student)
	{
		return null;
	}
	
	public boolean deleteStudentById(int Id)
	{
		return true;
	}
	
	public List<String> getStudentKursOceny(String Album,String Kurs)
	{
		List<String> ocenyKursu = new ArrayList<String>();
		String SQL = "Select o.IdStudent,d.Imie,d.Nazwisko,k.Nazwa,k.ECTS,fk.Nazwa,fk.Typ,fk.LiczbaGodzin,o.ITermin,o.IITermin,o.IPoprawka,o.IIPoprawka,o.Komisja FROM ocena o"+ 
		"JOIN ProwadzacyForme pf ON pf.Id = o.IdProwadzacyForme"+
		"JOIN FormaKursu fk ON fk.Id = pf.IdForma AND fk.IdKurs = :kurs"+
		"JOIN Dydaktyk d ON d.Id = pf.IdDydaktyk"+
		"JOIN Kurs k ON  k.Nazwa = fk.IdKurs"+
		"WHERE o.IdStudent = :album";
		return ocenyKursu;
	}
	
	public List<KursyStudenta> getStudentKursy(String Album)
	{
		List<KursyStudenta> kursyStudenta ;
		MapSqlParameterSource queryParams = new MapSqlParameterSource();
		queryParams.addValue("album", Album);
		String SQL = "SELECT k.*,fk.*,d.Tytul,d.Imie,d.Nazwisko FROM Student st JOIN GrupaLab l ON st.IdGrupaLab = l.Id "+
     	" JOIN GrupaCw c ON l.IdGrupaCw = c.Id JOIN GrupaWyk w ON c.IdGrupaWyk = w.Id"+				
		" JOIN Rocznik r ON w.IdRocznik = r.Id"+
		" JOIN Semestr s ON r.IdSemestr = s.NumerSemestru"+
		" JOIN Kurs k ON k.IdSemestr = s.NumerSemestru"+
		" JOIN FormaKursu fk ON fk.IdKurs = k.Nazwa"+
		" JOIN ProwadzacyForme pf ON pf.IdGrupaL = st.IdGrupaLab AND pf.IdForma = fk.Id"+
		" JOIN Dydaktyk d ON d.Id = pf.IdDydaktyk"+
		" WHERE st.Album = :album";
		kursyStudenta = namedJdbcTemplate.query(SQL,queryParams, new KursyStudentaRowMapper());
		return kursyStudenta;
	}
	
	public PracaDyplomowa getStudentPracaDyplomowa(String Student)
	{
		PracaDyplomowa pracaDyplomowa ;
		MapSqlParameterSource queryParams = new MapSqlParameterSource();
		queryParams.addValue("student",Student);
		String SQL = "SELECT dp.TytulPracy,dp.OcenaPierwszego,dp.OcenaDrugiego,dp.TerminZlozeniaPracy,dp.DataZlozeniaPracy,dp.DataObrony ,d1.Tytul,d1.Imie,d1.Nazwisko,"
				+ "d2.Tytul AS d2Tytul,d2.Imie AS d2Imie,d2.Nazwisko AS d2Nazwisko, "
				+ "d3.Tytul AS d3Tytul,d3.Imie AS d3Imie,d3.Nazwisko AS d3Nazwisko  "
				+ ",d4.Tytul AS d4Tytul,d4.Imie AS d4Imie,d4.Nazwisko AS d4Nazwisko "
				+ "FROM Dyplomowa dp "
				+ "JOIN Dydaktyk d1 ON dp.IdDydaktyk = d1.Id "
				+ "JOIN Dydaktyk d2 ON dp.IdPierwszyRecenzent = d2.Id "
				+ "JOIN Dydaktyk d3 ON dp.IdDrugiRecenzent = d3.Id  "
				+ "JOIN Dydaktyk d4 ON dp.IdPrzewodnicacy = d4.Id "
				+ " WHERE dp.IdStudent = :student";
		pracaDyplomowa = namedJdbcTemplate.queryForObject(SQL,queryParams, new StudentPracaDyplomowaMapper());
		return pracaDyplomowa;
	}
	
	public List<Oplata> getStudentOplaty(String Student)
	{
		MapSqlParameterSource queryParams = new MapSqlParameterSource();
		queryParams.addValue("student",Student);
		String SQL = "SELECT * FROM Naleznosci WHERE IdStudent = :student";
		List<Oplata> oplaty = namedJdbcTemplate.query(SQL, queryParams, new OplataRowMapper());
		return oplaty;
	}
	
	

}
