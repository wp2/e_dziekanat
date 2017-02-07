package zut.wi.edziekanat.entity.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import zut.wi.edziekanat.entity.Ogloszenie;

public class OgloszenieRowMapper implements RowMapper<Ogloszenie> 
{

	@Override
	public Ogloszenie mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		Ogloszenie ogloszenie = new Ogloszenie(rs.getString("Tytul"), rs.getDate("DataDodania"), rs.getString("Tresc"));
		return ogloszenie;
	}

}
