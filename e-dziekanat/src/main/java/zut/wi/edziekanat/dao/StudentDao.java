package zut.wi.edziekanat.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao 
{
	@Autowired 
	NamedParameterJdbcTemplate namedJdbcTemplate;
	
	public String getStudentByAlbum(String Album)
	{
		try
		{
			Student student;
			MapSqlParameterSource queryParams = new MapSqlParameterSource();
			queryParams.addValue("id", Id);
			student = namedJdbcTemplate.queryForObject("SELECT * FROM movies WHERE Id = :id",queryParams, new MovieRowMapper()); 
			return movie;
		}
		catch(IncorrectResultSizeDataAccessException e)
		{
			System.out.println("Record does not exsits");
			return null;
		}
		catch(DataAccessException e)
		{
			return null;
		}	
	}

}
