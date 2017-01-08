package zut.wi.edziekanat.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import zut.wi.edziekanat.entity.Student;

public interface StudentDao extends Repository<Student, Integer> 
{
	Student findByAlbum(int Album);
	
	List<Student> findAll();
	
	Student findByLogin(String Login);
}
