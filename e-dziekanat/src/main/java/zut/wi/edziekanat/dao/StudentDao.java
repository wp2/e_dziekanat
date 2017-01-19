package zut.wi.edziekanat.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import zut.wi.edziekanat.entity.Student;
import zut.wi.edziekanat.entity.StudentRowMapper;

@Repository
public class StudentDao 
{
	@Autowired
	NamedParameterJdbcTemplate namedJdbcTemplate;
	
	
	public Student getStudentById(int Id)
	{
		try
		{
			MapSqlParameterSource queryParams = new MapSqlParameterSource();
			queryParams.addValue("id", Id);
			Student student;
			String SQL = "SELECT * FROM STUDENT WHERE Album = :id";
			student = namedJdbcTemplate.queryForObject(SQL, queryParams,new StudentRowMapper());
			return student;
		}
		catch(BadSqlGrammarException e)
		{
			e.printStackTrace();
			return null;
		}
		catch (EmptyResultDataAccessException e)
		{
			System.out.println("STUDENT RESULT = 0");
			return null;
		}
		catch(DataAccessException e)
		{
			e.printStackTrace();
			return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	public boolean editStudentById(Student student)
	{
		return true;
	}
	
	public Student addStudent(Student student)
	{
		return null;
	}
	
	public boolean deleteStudentById(int Id)
	{
		return true;
	}

}
