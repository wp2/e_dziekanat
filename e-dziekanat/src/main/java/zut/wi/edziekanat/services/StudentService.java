package zut.wi.edziekanat.services;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zut.wi.edziekanat.dao.StudentDao;
import zut.wi.edziekanat.entity.KursyStudenta;
import zut.wi.edziekanat.entity.Oplata;
import zut.wi.edziekanat.entity.PracaDyplomowa;
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
	
	public List<KursyStudenta> getStudentKursy(String Album)
	{
		return studentDao.getStudentKursy(Album);
	}
	
	public PracaDyplomowa getStudentPracaDyplomowa(String Student)
	{
		return studentDao.getStudentPracaDyplomowa(Student);
	}
	
	public List<Oplata> getStudentOplaty(String Student)
	{
		return studentDao.getStudentOplaty(Student);
	}
	

}
