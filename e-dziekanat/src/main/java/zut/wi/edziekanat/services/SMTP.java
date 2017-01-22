package zut.wi.edziekanat.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.sql.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class SMTP 
{
	private String SMTP_SERVER;
	private int SMTP_PORT;
	
	
	public Socket Connection;
	BufferedReader breader;
	BufferedWriter bwriter;
	Reader reader ;
	
	private String UserName;
	private String Passwd;
	
	public SMTP(String Destanation,String Topic,String MessageText)
	{
		this.LoadSettingsFromXML();
		this.Connect();
		System.out.println(SingleResponse());
		this.Auth(this.UserName,this.Passwd);	
		this.PrepareMessage(Destanation,Topic,MessageText);
	}
	
	public SMTP()
	{
		this.LoadSettingsFromXML();
		this.Connect();
		System.out.println(SingleResponse());
		this.Auth(this.UserName,this.Passwd);		
	}
	private void LoadSettingsFromXML()
	{			
		this.SMTP_SERVER = "poczta.o2.pl";
		this.SMTP_PORT= 25;
		this.UserName = "YW5pbWFuYWR2QG8yLnBs";
		this.Passwd = "UHJvdG90eXBlMg==";		
	}
	private boolean Connect() 
	{
		Connection = new Socket();		
		try
		{
			Connection.connect(new InetSocketAddress(SMTP_SERVER, SMTP_PORT));
			breader = new BufferedReader(new InputStreamReader(Connection.getInputStream()));
			reader = new InputStreamReader(Connection.getInputStream(), "UTF-8");
			bwriter = new BufferedWriter(new OutputStreamWriter(Connection.getOutputStream()));	
		} 
		catch (IOException e) 
		{			
			return false;
		}		
		return true;
	}
	
	public void WriteToServer(String str)
	{
		if(Connection.isConnected())
		{
			try 
			{
				bwriter.write(str + "\n");
				bwriter.flush();				
			}
			catch (IOException e) 
			{				
				e.printStackTrace();
			}
		}
	}
	
	public String SingleResponse()
	{
		if(Connection.isConnected())
		{
			try
			{
				return breader.readLine();				
			}
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	public void Exit()
	{
		System.out.println("Preform Exit");
		WriteToServer("QUIT");
		//System.out.println("SERVER RESPONSE = " + ReadResponseFromServer());
		try 
		{
			Disconnect();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			System.out.println("Already disconnected");
		}
	}	
	
	public boolean Disconnect() throws IOException
	{
		breader = null;
		bwriter = null;
		reader = null;
		if(!Connection.isConnected())return true;
		Connection.close();
		return true;
	}	
	
	public String ReadAllResponseFromServer()
	{		
		if(Connection.isConnected())
		{
			String response="";
			try 
			{
				String line="";
				
				while( (line = breader.readLine()) != null )
				{				
					
		            if (line.equals(".") || line.equals("") || line.equals(" ")) {
		                // No more lines in the server response
		            	//System.out.println("LINE IS .");
		                break;
		            }
		            if ((line.length() > 0) && (line.charAt(0) == '.')) {
		            	//System.out.println("LINE IS SOMETHING");
		                // The line starts with a "." - strip it off.
		                line = line.substring(1);
		            }
		            if(line.isEmpty()){System.out.println("LINE IS EMPTY"); break;}
		            response += line+"\n" ;
		            //System.out.println("WHILE IS LOOPING");
		            line = null;
		           
				}
				//System.out.println("OUT OF LOOP");
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				System.out.println("IO EXCEPTION");
				e.printStackTrace();
			}
			//System.out.println("SERVER RESPONDED WITH = " + response);
			return response;			
		}
		else
		{
			return null;
		}
	}
	private boolean Auth(String USER,String PASS)
	{
		System.out.println("Preforming Auth");
		WriteToServer("AUTH LOGIN");
		String Res1 = this.SingleResponse();
		System.out.println(Res1);
		WriteToServer(USER);		
		Res1 = SingleResponse();
		System.out.println(Res1);
		//System.out.println("NOW PASS= " + ReadResponseFromServer());
		WriteToServer(PASS);
		Res1 = SingleResponse();
		System.out.println(Res1);
		String [] ResponseTable = Res1.split(" ");
		if(ResponseTable[0].equals("235")) return true;
		else return false;
		//System.out.println("SERVER AUTH RESPONSE = "+ReadResponseFromServer());
		
	}
	private boolean PrepareMessage(String Destanation,String Topic,String MessageText)
	{
		WriteToServer("MAIL FROM: animanadv@o2.pl");
		System.out.println(SingleResponse());
		WriteToServer("RCPT TO: " + Destanation );
		System.out.println(SingleResponse());
		WriteToServer("DATA");
		System.out.println(SingleResponse());
		String Message =  "From: animanadv@o2.pl \n" +
		"To: "+ Destanation	+"\n"+	
		//"Date: Tue, 2 January 2017 18:58:43 \n" +
		"Subject:" + Topic  + "\n"
		+ "\n" + MessageText +"\n.";
		WriteToServer(Message);
		System.out.println(SingleResponse());
		return true;
	}
}
