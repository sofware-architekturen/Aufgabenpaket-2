package com.buchlager.core.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteObserver<T> extends Remote {
    void update(T arg) throws RemoteException;
}
