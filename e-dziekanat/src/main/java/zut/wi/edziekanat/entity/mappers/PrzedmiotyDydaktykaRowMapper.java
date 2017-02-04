package zut.wi.edziekanat.entity.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import zut.wi.edziekanat.entity.PrzedmiotyDydaktyka;

public class PrzedmiotyDydaktykaRowMapper implements RowMapper<PrzedmiotyDydaktyka> {

	@Override
	public PrzedmiotyDydaktyka mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		PrzedmiotyDydaktyka przedmioty = new PrzedmiotyDydaktyka(rs.getString("IdKurs"), rs.getString("Typ"), rs.getString("FormaZaliczenia")
				, rs.getInt("LiczbaGodzin"), rs.getString("IdGrupaL"));
		przedmioty.setFormaProwadzącegoId(rs.getInt("PFormaId"));
		return przedmioty;
	}

}
