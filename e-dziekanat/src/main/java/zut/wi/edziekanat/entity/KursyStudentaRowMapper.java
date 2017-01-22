package zut.wi.edziekanat.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class KursyStudentaRowMapper implements RowMapper<KursyStudenta> {

	@Override
	public KursyStudenta mapRow(ResultSet rs, int arg1) throws SQLException 
	{
		KursyStudenta ks = new KursyStudenta();
		ks.setNazwa(rs.getString("Nazwa"));
		ks.seteCTS(rs.getInt("ECTS"));
		ks.setFormaZajec(rs.getString("Typ"));
		ks.setFormaZaliczenia(rs.getString("FormaZaliczenia"));
		ks.setLiczbaGodzin(rs.getInt("LiczbaGodzin"));
		ks.setProwadzacy(rs.getString("Tytul") + " " + rs.getString("Imie") + " " + rs.getString("Nazwisko"));
		return ks;
	}

}
