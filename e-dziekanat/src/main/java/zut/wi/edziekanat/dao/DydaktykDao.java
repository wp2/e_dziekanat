package zut.wi.edziekanat.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import zut.wi.edziekanat.entity.Dydaktyk;
import zut.wi.edziekanat.entity.PrzedmiotyDydaktyka;
import zut.wi.edziekanat.entity.StudentOceny;
import zut.wi.edziekanat.entity.Zatrudnienie;
import zut.wi.edziekanat.entity.mappers.DydaktykRowMapper;
import zut.wi.edziekanat.entity.mappers.PracownikKursStudentOcenyRowMapper;
import zut.wi.edziekanat.entity.mappers.PrzedmiotyDydaktykaRowMapper;
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
	
	
}
