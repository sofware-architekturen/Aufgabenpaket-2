package com.buchlager.core.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IRemoteSubject <T> extends Remote {
    void register(IRemoteObserver<T> observer)throws RemoteException;
    void remove(IRemoteObserver<T> observer)throws RemoteException;
    void notifyObservers(T pushedData) throws RemoteException;
    void clear()throws RemoteException;
}
