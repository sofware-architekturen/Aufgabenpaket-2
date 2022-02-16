package com.buchlager.server.services.order;

import com.buchlager.core.interfaces.IBuchlagerRepository;
import com.buchlager.core.interfaces.IMessageService;
import com.buchlager.core.interfaces.IOrderService;
import com.buchlager.core.model.Bestellung;
import com.buchlager.server.services.observer.JMSOrderNotificationService;
import com.buchlager.server.services.observer.RESTOrderNotificationService;

import java.rmi.RemoteException;
import java.util.concurrent.TimeUnit;

public class OrderService extends OrderMessageService implements IOrderService<Integer> {

    IBuchlagerRepository repository;
    IMessageService messageService;

    public OrderService(IBuchlagerRepository repository) {
        this(repository, null);
    }

    public OrderService(IBuchlagerRepository repository, IMessageService messageService) {
        this.messageService = (messageService == null) ? new OrderMessageService(new JMSOrderNotificationService(), new RESTOrderNotificationService()) : messageService;
        this.repository = repository;
    }

    @Override
    public void makeOrder(Integer id) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                messageService.sendMessage(new Bestellung(id, 3));
                repository.bestandEinbuchen(id, 3);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }).start();
    }
}
