package com.buchlager.server.services.order;

import com.buchlager.core.interfaces.IMessageService;
import com.buchlager.core.interfaces.IObserver;
import com.buchlager.core.model.Bestellung;
import com.buchlager.server.services.observer.Subject;

import java.util.Arrays;


public class OrderMessageService implements IMessageService<Bestellung> {

    private static Subject<Bestellung> orderSubject = new Subject<>();

    public OrderMessageService(IObserver... observers) {
        Arrays.stream(observers).forEach(orderSubject::register);
    }

    @Override
    public void sendMessage(Bestellung bestellung) {
        orderSubject.notifyObservers(bestellung);
    }
}
