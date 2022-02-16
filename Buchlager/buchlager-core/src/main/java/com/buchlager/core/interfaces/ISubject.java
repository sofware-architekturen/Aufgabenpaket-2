package com.buchlager.core.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISubject<T>{
    void register(IObserver<T> observer);
    void remove(IObserver<T> observer);
    void notifyObservers(T pushedData);
    void clear()throws RemoteException;
}
