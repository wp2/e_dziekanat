package zut.wi.edziekanat.exceptions;

import org.springframework.http.HttpStatus;

public class StudentNotFound extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String student;
	
	HttpStatus httpStatus;
	
	public StudentNotFound(String student) {
		super();
		this.student = student;
		httpStatus = HttpStatus.NOT_FOUND;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Student = " + student + " has been not found" ;
	}
	
	public HttpStatus getHttpStatus()
	{
		return this.httpStatus;
	}
	
	public Integer getHttpStatusValue()
	{
		return this.httpStatus.value();
	}
	
	public String getHttpReason()
	{
		return this.httpStatus.getReasonPhrase();
	}

}
