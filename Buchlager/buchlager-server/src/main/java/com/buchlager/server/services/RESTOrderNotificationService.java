package com.buchlager.server.services;

import com.buchlager.core.interfaces.IRemoteObserver;
import com.buchlager.core.model.Bestellung;

import java.io.Serializable;
import java.rmi.RemoteException;

public class RESTOrderNotificationService implements IRemoteObserver<Bestellung>, Serializable {
    private static final long serialVersionUID = 18486L;

    @Override
    public void update(Bestellung arg) throws RemoteException {

    }
}
