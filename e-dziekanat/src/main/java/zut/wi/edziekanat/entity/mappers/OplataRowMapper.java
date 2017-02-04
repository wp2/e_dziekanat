package zut.wi.edziekanat.entity.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import zut.wi.edziekanat.entity.Oplata;

public class OplataRowMapper implements RowMapper<Oplata> {

	@Override
	public Oplata mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		Oplata oplata = new Oplata(rs.getString("Tytul"), rs.getInt("Konto"), rs.getBigDecimal("Kwota"));
		return oplata;
	}

}
