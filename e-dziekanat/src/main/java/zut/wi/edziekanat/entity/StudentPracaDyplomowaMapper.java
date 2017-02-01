package zut.wi.edziekanat.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StudentPracaDyplomowaMapper implements RowMapper<PracaDyplomowa> {

	@Override
	public PracaDyplomowa mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		String Promotor = rs.getString("Tytul") + " " + rs.getString("Imie") + " " + rs.getString("Nazwisko");		
		String Recenzent1 = rs.getString("d2Tytul") + " " + rs.getString("d2Imie") + " " + rs.getString("d2Nazwisko");
		String Recenzent2 = rs.getString("d3Tytul") + " " + rs.getString("d3Imie") + " " + rs.getString("d3Nazwisko");
		String Przewodnicacy = rs.getString("d4Tytul") + " " + rs.getString("d4Imie") + " " + rs.getString("d4Nazwisko");
		return new PracaDyplomowa("", Promotor ,rs.getString("TytulPracy"), Recenzent1, Recenzent2,rs.getFloat("OcenaPierwszego"), rs.getFloat("OcenaDrugiego"),
				rs.getDate("TerminZlozeniaPracy"), rs.getDate("DataZlozeniaPracy"), Przewodnicacy, rs.getDate("DataObrony"));
	}

}
