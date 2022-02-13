package com.buchlager.server.services;

import com.buchlager.core.interfaces.IRemoteObserver;
import com.buchlager.core.model.Bestellung;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.Serializable;
import java.rmi.RemoteException;

public class JMSOrderNotificationService implements IRemoteObserver<Bestellung>, Serializable {
    private static final long serialVersionUID = 1L;
    @Override
    public void update(Bestellung bestellung) throws RemoteException {
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("BOOK_ORDER_QUE");

            MessageProducer sender = session.createProducer(destination);

            ObjectMessage message = session.createObjectMessage(bestellung);
            sender.send(message);
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
