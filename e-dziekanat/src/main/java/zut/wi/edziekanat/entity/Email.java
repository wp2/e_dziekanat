package zut.wi.edziekanat.entity;

import org.springframework.stereotype.Component;


public class Email 
{
	private String destanation;
	
	public String topic;
	
	public String msgText;

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getMsgText() {
		return msgText;
	}

	public void setMsgText(String msgText) {
		this.msgText = msgText;
	}

	public String getDestanation() {
		return destanation;
	}

	public void setDestanation(String destanation) {
		this.destanation = destanation;
	}
	
	
	
	
	
	

}
