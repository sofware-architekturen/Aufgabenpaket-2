import com.buchlager.core.model.Bestellung;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class Lieferant {
    public static void main(String[] args) {
        boolean continiueLoop = true;
        char breakCharacter;
            try {
                ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
                connectionFactory.setTrustAllPackages(true);
                Connection connection = connectionFactory.createConnection();

                connection.start();

                Thread messageReciverThread = new Thread(()-> new MessageReceiver().getMessage(connection));
                messageReciverThread.start();
                System.out.println("Service läuft, beenden mit \"q\" und Eingabe.");

                while (continiueLoop){
                    breakCharacter = (char) System.in.read();
                    if(breakCharacter == 'q'){
                        messageReciverThread.interrupt();
                        connection.close();
                        continiueLoop = false;
                    }
                }
                System.out.print(messageReciverThread.isInterrupted() ? "Client ist beendet!" : "Client konnte nicht beendet werden...");

            } catch (JMSException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
    static class MessageReceiver implements MessageListener {
        public void getMessage(Connection connection) {
            try {
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                Destination destination = session.createQueue("BOOK_ORDER_QUE");
                session.createConsumer(destination).setMessageListener(this::onMessage);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onMessage(Message message) {
            if (message instanceof ObjectMessage) {
                Bestellung bestellung = null;
                try {
                    bestellung = (Bestellung) ((ObjectMessage) message).getObject();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                System.out.println("Lieferant hat " + bestellung + " bekommen");
            }
        }
    }
}
