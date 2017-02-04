package zut.wi.edziekanat.entity.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import zut.wi.edziekanat.entity.StudentOceny;

public class PracownikKursStudentOcenyRowMapper implements RowMapper<StudentOceny> {

	@Override
	public StudentOceny mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		StudentOceny oceny = new StudentOceny(rs.getInt("Id"),rs.getString("Imie"),rs.getString("Nazwisko"),rs.getFloat("ITermin"), rs.getFloat("IITermin"),
				rs.getFloat("IPoprawka"), rs.getFloat("IIPoprawka"), rs.getFloat("Komisja"));
		// TODO Auto-generated method stub
		return oceny;
	}

}
