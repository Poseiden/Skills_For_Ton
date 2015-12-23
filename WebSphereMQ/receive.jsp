<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.naming.*, javax.jms.Queue, javax.jms.*, java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
Message is:<br>
<%
	try {
		Context ctx =  new InitialContext();
		QueueConnectionFactory qcf = (QueueConnectionFactory)ctx.lookup("qma");
	    //Queue queue = (Queue)ctx.lookup("queue1");
	    QueueConnection conn = qcf.createQueueConnection();
	    conn.start();
	    boolean transacted = false;
	    QueueSession qSession = conn.createQueueSession(transacted, Session.AUTO_ACKNOWLEDGE);
	    
	    Queue queue = qSession.createQueue("QUEUE1");
	    
	    QueueReceiver queueReceiver = qSession.createReceiver(queue, null);
	    Message inMessage = queueReceiver.receive(2000);
	    if (inMessage instanceof TextMessage) {
			String replyString = ((TextMessage)inMessage).getText();
			out.println(replyString);
		}
		
		queueReceiver.close();
	    qSession.close();
	    qSession = null;
	    conn.close();
    	conn = null;
    
    } catch(Exception e) {
    	out.println(e.getMessage());
    	e.printStackTrace();
    }
    
%>
</body>
</html>