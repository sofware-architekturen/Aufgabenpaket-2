package com.buchlager.server.services;

import com.buchlager.core.interfaces.IRemoteObserver;
import com.buchlager.core.interfaces.IRemoteSubject;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Subject<T> implements IRemoteSubject<T> {
    private List<IRemoteObserver<T>> observers = new ArrayList<>();

    public void register(IRemoteObserver<T> observer) {
        observers.add(observer);
    }

    public void remove(IRemoteObserver<T> observer) {
        observers.remove(observer);
    }

    public void notifyObservers(T pushedData) throws RemoteException {
        for(IRemoteObserver<T> observer : observers){
            observer.update(pushedData);
        }
    }

    public void clear(){
        observers = new ArrayList<>();
    }
}
