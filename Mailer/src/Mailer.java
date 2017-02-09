import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;


public class Mailer {
	
	
	private static String msgFile;
	private static String addressFile;
	private String myEmail;
	
	public Mailer(String msgFile, String addressFile, String myEmail) {
		this.msgFile = msgFile;
		this.addressFile = addressFile;
		this.myEmail = myEmail;
	}
	
	public boolean sendMessage(StringBuilder compMsg, String recipientEmail) {
		
		Properties props = new Properties();
	    props.put("mail.smtp.host", "smtp.usfca.edu"); //add my smtp server
	    props.put("mail.from", myEmail); //sender email
	    Session session = Session.getInstance(props, null);
	    String strMsg = compMsg.toString();
		try {
    		MimeMessage msg = new MimeMessage(session);
        	msg.setFrom();
        	msg.setRecipients(Message.RecipientType.TO,
        				recipientEmail);
        	msg.setSubject("Project 2 Mailer Program Output");
        	msg.setSentDate(new Date());
        	msg.setText(strMsg);
        	Transport.send(msg);
        	System.out.println("The message was sent!");
    	} catch (MessagingException mex) {
    		System.out.println("Message send failed, exception: " + mex);
    		return false;
    	}
		
		return true;
	}
	
	public static void main(String[] args) {
		Mailer mail = new Mailer(args[0], args[1], args[2]);
		Msg msg = new Msg();
		Values val = new Values();
		msg.fillArray(msgFile);
		val.createHashMap(addressFile);
		ArrayList<StringBuilder> msgparts = msg.getMsgList(); //gets arraylist of msg
		ArrayList<String> variables = msg.getVariables();     //gets arralist of variables
		ArrayList<HashMap<String, String>> valHashMap = val.getHashArray();
		
		for(int i = 0; i < valHashMap.size(); i++) { //creates a msg with variables for each email and sends it
			HashMap<String, String> vals = valHashMap.get(i);
			StringBuilder compMsg = msg.compileMsg(vals);
			
			if(!mail.sendMessage(compMsg, vals.get("address"))) {
				System.out.println("Message could not be sent, program is now quitting.");
				System.exit(0);
			}
		}
	}
	
}
	
