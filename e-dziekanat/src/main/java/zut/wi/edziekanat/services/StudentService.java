package zut.wi.edziekanat.services;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zut.wi.edziekanat.dao.StudentDao;
import zut.wi.edziekanat.entity.Student;

@Service
public class StudentService
{
	@Autowired
	StudentDao studentDao;
	
	public Student getStudentByAlbum(String Album)
	{
		return studentDao.getStudentById(Album);
	}
	
	public Student getStudentDataByAlbum(String Album)
	{
		return studentDao.getStudentDataById(Album);
	}
	
	public Map<String,String> getStudentSemestrAndGroupsInfo(String Album)
	{
		return studentDao.getStudentGrupaRocznikSemestr(Album);
	}
	

}
