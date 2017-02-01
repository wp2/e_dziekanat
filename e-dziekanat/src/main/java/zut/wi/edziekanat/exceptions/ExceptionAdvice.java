package zut.wi.edziekanat.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler
{
	
	@ExceptionHandler(StudentNotFound.class)
	public ResponseEntity<GenericErrorMessage> studentNotFoundException(StudentNotFound e)
	{		
		GenericErrorMessage genericError = new GenericErrorMessage(e.getHttpStatusValue(), e.getHttpReason(),e.getMessage());
		return new ResponseEntity<GenericErrorMessage>(genericError,e.getHttpStatus());
	}

}
