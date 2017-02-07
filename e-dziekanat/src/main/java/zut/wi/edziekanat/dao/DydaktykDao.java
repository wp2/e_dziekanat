package zut.wi.edziekanat.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import zut.wi.edziekanat.entity.Dydaktyk;
import zut.wi.edziekanat.entity.Ogloszenie;
import zut.wi.edziekanat.entity.PracaDyplomowa;
import zut.wi.edziekanat.entity.PrzedmiotyDydaktyka;
import zut.wi.edziekanat.entity.StudentOceny;
import zut.wi.edziekanat.entity.Zatrudnienie;
import zut.wi.edziekanat.entity.mappers.DydaktykRowMapper;
import zut.wi.edziekanat.entity.mappers.OgloszenieRowMapper;
import zut.wi.edziekanat.entity.mappers.PracownikKursStudentOcenyRowMapper;
import zut.wi.edziekanat.entity.mappers.PrzedmiotyDydaktykaRowMapper;
import zut.wi.edziekanat.entity.mappers.StudentPracaDyplomowaMapper;
import zut.wi.edziekanat.entity.mappers.ZatrudnienieRowMapper;


@Repository
public class DydaktykDao 
{
	@Autowired
	NamedParameterJdbcTemplate namedJdbcTemplate;
	
	public Dydaktyk getDydaktykById(Integer Id)
	{
		
		Dydaktyk dydaktyk;
		MapSqlParameterSource queryParams = new MapSqlParameterSource();
		queryParams.addValue("id", Id);
		dydaktyk = namedJdbcTemplate.queryForObject("SELECT * FROM Dydaktyk WHERE Id = :id",queryParams,new DydaktykRowMapper()); 
		return dydaktyk;				
	}
	
	public Zatrudnienie getDydaktykZatrudnienieInfo(Integer Id)
	{
		Zatrudnienie zatrudnienie;
		MapSqlParameterSource queryParams = new MapSqlParameterSource();
		queryParams.addValue("id", Id);
		zatrudnienie = namedJdbcTemplate.queryForObject("SELECT z.*,d.Imie,d.Nazwisko FROM Zatrudnienie z "+
		"JOIN Dydaktyk d ON d.Id = NumerPracownika "+
		"WHERE NumerPracownika = :id ",queryParams,new ZatrudnienieRowMapper()); 
		return zatrudnienie;
	}
	
	public List<PrzedmiotyDydaktyka> getDydaktykPrzedmioty(Integer Id)
	{
		List<PrzedmiotyDydaktyka> przedmioty;
		MapSqlParameterSource queryParams = new MapSqlParameterSource();
		queryParams.addValue("id", Id);
		przedmioty = namedJdbcTemplate.query("SELECT fk.*,pf.IdGrupaL,pf.Id As PFormaId FROM ProwadzacyForme pf "+ 
		"JOIN FormaKursu fk ON pf.IdForma = fk.Id JOIN Kurs k ON fk.IdKurs = k.Nazwa WHERE pf.IdDydaktyk = :id",queryParams,new PrzedmiotyDydaktykaRowMapper()); 
		return przedmioty;
	}
	
	public List<StudentOceny> getOcenyzKursow(Integer Id)
	{
		String SQL = "SELECT st.Imie,st.Nazwisko,o.* FROM ProwadzacyForme pf JOIN Ocena o ON o.IdProwadzacyForme = pf.Id "+
		"JOIN Student st ON st.Album = o.IdStudent "+
		"WHERE pf.Id = :id";
		MapSqlParameterSource queryParams = new MapSqlParameterSource();
		queryParams.addValue("id", Id);
		List<StudentOceny> oceny = namedJdbcTemplate.query(SQL, queryParams,new PracownikKursStudentOcenyRowMapper());
		return oceny;
	}
	
	public boolean setStudentOcenyById(StudentOceny oceny )
	{
		String SQL = "UPDATE Ocena SET ITermin = :itermin, IITermin = :iitermin, IPoprawka = :ipoprawka , IIPoprawka = :iipoprawka WHERE Id= :id";
		MapSqlParameterSource queryParams = new MapSqlParameterSource();
		queryParams.addValue("id", oceny.getId());
		queryParams.addValue("itermin", oceny.getITermin());
		queryParams.addValue("iitermin", oceny.getIITermin());
		queryParams.addValue("ipoprawka", oceny.getIPoprawka());
		queryParams.addValue("iipoprawka", oceny.getIIPoprawka());
		namedJdbcTemplate.update(SQL, queryParams);
		return true;
	}
	
	public PracaDyplomowa getPromotorPracaDyplomowa(Integer Dydaktyk)
	{
		PracaDyplomowa pracaDyplomowa ;
		MapSqlParameterSource queryParams = new MapSqlParameterSource();
		queryParams.addValue("promotor",Dydaktyk);
		String SQL = "SELECT dp.TytulPracy,dp.OcenaPierwszego,dp.OcenaDrugiego,dp.TerminZlozeniaPracy,dp.DataZlozeniaPracy,dp.DataObrony ,d1.Tytul,d1.Imie,d1.Nazwisko,"
				+ "d2.Tytul AS d2Tytul,d2.Imie AS d2Imie,d2.Nazwisko AS d2Nazwisko, "
				+ "d3.Tytul AS d3Tytul,d3.Imie AS d3Imie,d3.Nazwisko AS d3Nazwisko  "
				+ ",d4.Tytul AS d4Tytul,d4.Imie AS d4Imie,d4.Nazwisko AS d4Nazwisko "
				+ "FROM Dyplomowa dp "
				+ "JOIN Dydaktyk d1 ON dp.IdDydaktyk = d1.Id "
				+ "JOIN Dydaktyk d2 ON dp.IdPierwszyRecenzent = d2.Id "
				+ "JOIN Dydaktyk d3 ON dp.IdDrugiRecenzent = d3.Id  "
				+ "JOIN Dydaktyk d4 ON dp.IdPrzewodnicacy = d4.Id "
				+ " WHERE dp.IdDydaktyk= :promotor";
		pracaDyplomowa = namedJdbcTemplate.queryForObject(SQL,queryParams, new StudentPracaDyplomowaMapper());
		return pracaDyplomowa;
	}
	
	public List<Ogloszenie> getAllOgloszenia()
	{
		String SQL ="SELECT * FROM Ogloszenia";
		List<Ogloszenie> ogloszenia = namedJdbcTemplate.query(SQL,new OgloszenieRowMapper());
		return ogloszenia; 
	}
	
	
}
