package zut.wi.edziekanat.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zut.wi.edziekanat.dao.StudentDao;

@Service
public class StudentService
{
	@Autowired
	StudentDao studentDao;
	
	public String getUserNamePassword(String UserName)
	{
		return studentDao.getUserNamePassword(UserName);
	}

}
