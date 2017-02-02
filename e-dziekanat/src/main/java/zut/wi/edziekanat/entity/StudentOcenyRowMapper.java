package zut.wi.edziekanat.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StudentOcenyRowMapper implements RowMapper<StudentOceny>{

	@Override
	public StudentOceny mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		StudentOceny studentOceny = new StudentOceny(rs.getString("Tytul") + " " + rs.getString("Imie") + rs.getString("Nazwisko"), rs.getString("Typ")
				, rs.getString("Nazwa"), rs.getFloat("ITermin"),rs.getFloat("IITermin"),rs.getFloat("IPoprawka"),rs.getFloat("IIPoprawka"),rs.getFloat("Komisja"));
		studentOceny.setLiczbaGodzin(rs.getInt("LiczbaGodzin"));
		studentOceny.setFormaZaliczenia(rs.getString("FormaZaliczenia"));
		return studentOceny;
		
	}

}
