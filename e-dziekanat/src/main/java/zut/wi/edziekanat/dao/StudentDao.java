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

import zut.wi.edziekanat.entity.Student;
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
		try
		{
			Student student = new Student();
			MapSqlParameterSource queryParams = new MapSqlParameterSource();
			queryParams.addValue("album", Id);
			SqlRowSet rs1 = namedJdbcTemplate.queryForRowSet("SELECT *,k.Nazwa FROM Student s JOIN Kierunek k ON k.Id = s.IdKierunek "
					+ "WHERE s.Album = :album", queryParams);
			MapSqlParameterSource queryParams2 = new MapSqlParameterSource();
			//int t = rs1.getInt("IdKierunek");
			
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
		catch(IncorrectResultSizeDataAccessException e)
		{
			System.out.println("Record does not exsits");
			return null;
		}
		catch(DataAccessException e)
		{
			e.printStackTrace();
			return null;
		}	
		
	}
	
	public Map<String,String> getStudentGrupaRocznikSemestr(String studentAlbum)
	{
		try
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
		catch(IncorrectResultSizeDataAccessException e)
		{
			System.err.println("Record does not exsits");
			return null;
		}
		catch(BadSqlGrammarException e)
		{
			System.err.println("SQL Grammar ERROR in getStudentGrupaRocznikSemsetr");
			e.printStackTrace();
			return null;
		}
		catch(DataAccessException e)
		{
			return null;
		}	
		
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
	
	public List<String> getStudentKursy(String Album)
	{
		List<String> kursyStudenta = new ArrayList<String>();
		String SQL = "SELECT k.* FROM Student st JOIN GrupaLab l ON st.IdGrupaLab = l.Id JOIN GrupaCw c ON l.IdGrupaCw = c.Id JOIN GrupaWyk w ON c.IdGrupaWyk = w.Id"+
		"JOIN Rocznik r ON w.IdRocznik = r.Id"+
		"JOIN Semestr s ON r.IdSemestr = s.NumerSemestru"+
		"JOIN Kurs k ON k.IdSemestr = s.NumerSemestru"+
		"WHERE st.Album = :album";
		return kursyStudenta;
	}

}
