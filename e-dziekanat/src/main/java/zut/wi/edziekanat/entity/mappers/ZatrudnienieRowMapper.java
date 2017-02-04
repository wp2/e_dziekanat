package zut.wi.edziekanat.entity.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import zut.wi.edziekanat.entity.Zatrudnienie;

public class ZatrudnienieRowMapper implements RowMapper<Zatrudnienie> {

	@Override
	public Zatrudnienie mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		Zatrudnienie zatrudnienie = new Zatrudnienie(rs.getString("Imie"),rs.getString("Nazwisko"),rs.getInt("NumerPracownika"),
		rs.getString("FormaZatrudnienia"),rs.getBigDecimal("KwotaMies"),rs.getDate("ZatrudnienieOd"), rs.getDate("ZatrudnienieDo"));
		return zatrudnienie;
		
		
	}

}
