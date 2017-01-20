package zut.wi.edziekanat.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StudentRowMapper implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet rs, int arg1) throws SQLException 
	{
		Student student = new Student();
		student.setAlbum(rs.getInt("Album"));
		student.setPlec(rs.getString("Plec"));
		student.setNazwisko(rs.getString("Nazwisko"));
		student.setHaslo(rs.getString("Haslo"));
		student.setLogin(rs.getString("Login"));
		student.setDataUrodzenia(rs.getDate("DataUrodzenia"));
		student.setImie(rs.getString("Imie"));
		student.setAdres(rs.getString("Adres"));
		student.setGrupaLab(rs.getString("IdGrupaLab"));
		return student;
	}

}
