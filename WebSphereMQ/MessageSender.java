import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.jms.JMSException;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.ibm.mq.jms.MQQueueConnectionFactory;

public class MessageSender implements Runnable {
	private QueueConnection conn;
	
	public MessageSender() throws JMSException {
		MQQueueConnectionFactory factory = new MQQueueConnectionFactory();
		factory.setHostName("localhost");
		factory.setPort(1414);
		factory.setQueueManager("QMA");
		conn = factory.createQueueConnection();
		conn.start();
	}
	
	public void close() throws JMSException {
		if(conn != null) {
			conn.close();
			conn = null;
		}
	}
	
	public static void main(String[] args) throws JMSException {
		// TODO Auto-generated method stub
		MessageSender sender = new MessageSender();
		sender.run();
		sender.close();
	}

	public void run() {
		// TODO Auto-generated method stub
		QueueSession session = null;
		try {
			session = conn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueSender sender = session.createSender(session.createQueue("QUEUE1"));
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String line;
			System.out.println("Input message to sent:");
			while(true) {
				line = reader.readLine();
				if(line == null || line.trim().equalsIgnoreCase("end"))
					break;
				TextMessage message = session.createTextMessage();
				message.setText(line);
				sender.send(message);
				System.out.println("Message " + line + " sent(" + message.getJMSMessageID() + ").");
			}
			System.out.println("Bye!");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(session != null)
					session.close();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}




