package zut.wi.edziekanat.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OplataRowMapper implements RowMapper<Oplata> {

	@Override
	public Oplata mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		Oplata oplata = new Oplata(rs.getString("Tytul"), rs.getInt("Konto"), rs.getBigDecimal("Kwota"));
		return oplata;
	}

}
