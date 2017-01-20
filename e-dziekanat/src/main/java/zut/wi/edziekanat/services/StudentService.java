package zut.wi.edziekanat.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zut.wi.edziekanat.dao.StudentDao;
import zut.wi.edziekanat.entity.Student;

@Service
public class StudentService
{
	@Autowired
	StudentDao studentDao;
	
	public Student getStudentByAlbum(int Album)
	{
		return studentDao.getStudentById(Album);
	}
	
	

}
