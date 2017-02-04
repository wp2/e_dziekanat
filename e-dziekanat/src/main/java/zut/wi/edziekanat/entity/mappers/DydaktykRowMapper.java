package zut.wi.edziekanat.entity.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import zut.wi.edziekanat.entity.Dydaktyk;

public class DydaktykRowMapper implements RowMapper<Dydaktyk> {

	@Override
	public Dydaktyk mapRow(ResultSet rs, int rowNum) throws SQLException {
		Dydaktyk dydaktyk = new Dydaktyk();
		dydaktyk.setId(rs.getInt("Id"));
		dydaktyk.setPlec(rs.getString("Plec"));
		dydaktyk.setNazwisko(rs.getString("Nazwisko"));
		dydaktyk.setHaslo(rs.getString("Haslo"));		
		dydaktyk.setDataUrodzenia(rs.getDate("DataUrodzenia"));
		dydaktyk.setImie(rs.getString("Imie"));
		dydaktyk.setAdres(rs.getString("Adres"));	
		dydaktyk.setTytul(rs.getString("Tytul"));
		return dydaktyk;
	}

}
