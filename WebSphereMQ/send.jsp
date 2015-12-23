<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.naming.*, javax.jms.Queue, javax.jms.*, java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	try {
		String message = request.getParameter("message") == null ? "" : request.getParameter("message").trim();
		Context ctx =  new InitialContext();
		QueueConnectionFactory qcf = (QueueConnectionFactory)ctx.lookup("qma");
	    Queue queue = (Queue)ctx.lookup("queue1");
	    QueueConnection conn = qcf.createQueueConnection();
	    conn.start();
	    boolean transacted = false;
	    QueueSession qSession = conn.createQueueSession(transacted, Session.AUTO_ACKNOWLEDGE);
	    QueueSender queueSender = qSession.createSender(queue);
	    TextMessage outMessage = qSession.createTextMessage(message);
	    queueSender.send(outMessage);
	    
	    queueSender.close();
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