package zut.wi.edziekanat.exceptions;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value={"httpCode","httpCodeReason","message"})
public class GenericErrorMessage 
{
	Integer HttpCode;
	
	String HttpCodeReason;
	
	String Message;
		

	/**
	 *  (HTTP ERROR CODE , HTTP ERROR CODE EXPLANATION , EXCEPTION DETAIL MESSAGE , EXCEPTION PATH)
	 *
	 */
	public GenericErrorMessage(Integer httpCode, String httpCodeReason, String message) {
		HttpCode = httpCode;
		HttpCodeReason = httpCodeReason;
		Message = message;
	
	}

	public Integer getHttpCode() {
		return HttpCode;
	}

	public String getHttpCodeReason() {
		return HttpCodeReason;
	}

	public String getMessage() {
		return Message;
	}
	
	
	
	
	

}