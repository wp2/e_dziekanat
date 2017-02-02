package zut.wi.edziekanat.services;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zut.wi.edziekanat.dao.StudentDao;
import zut.wi.edziekanat.entity.KursyStudenta;
import zut.wi.edziekanat.entity.Oplata;
import zut.wi.edziekanat.entity.PracaDyplomowa;
import zut.wi.edziekanat.entity.Student;
import zut.wi.edziekanat.entity.StudentOceny;

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
	
	public Map<String, List<KursyStudenta>> getStudentOceny(String Student)
	{
		Map<String,List<KursyStudenta>> studentKursy = new HashMap<String,List<KursyStudenta>>();
		List<String> kursyStudentaSemestr = this.studentDao.getStudentKursNazwy(Student);
		
		List<KursyStudenta> kursyStudenta = this.getStudentKursy(Student);
		
		for(String nazwaKursu : kursyStudentaSemestr )
		{
			List<KursyStudenta> obecnyKurs = new ArrayList<KursyStudenta>();
			List<StudentOceny> ocenyStudenta = this.studentDao.getStudentKursOceny(Student, nazwaKursu);
			for(KursyStudenta k : kursyStudenta)
			{
				if(k.getNazwa().equals(nazwaKursu))
				{
					for(StudentOceny ocena : ocenyStudenta )
					{
						if(k.getFormaZajec().equals(ocena.getForma()))
						{
							k.setOcena(ocena);
							obecnyKurs.add(k);
						}
					}
				}
			}
			studentKursy.put(nazwaKursu, obecnyKurs);
		}
		return studentKursy;
	}
	

}
