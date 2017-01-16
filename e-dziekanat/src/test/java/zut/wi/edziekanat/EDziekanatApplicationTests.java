package zut.wi.edziekanat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import zut.wi.edziekanat.services.StudentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EDziekanatApplicationTests 
{
	@Autowired
	StudentService studentService;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void TestDB()
	{
		
	}

}
