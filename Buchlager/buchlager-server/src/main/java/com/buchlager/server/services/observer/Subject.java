package com.buchlager.server.services.observer;

import com.buchlager.core.interfaces.IObserver;
import com.buchlager.core.interfaces.ISubject;

import java.util.ArrayList;
import java.util.List;

public class Subject<T> implements ISubject<T> {
    private List<IObserver<T>> observers = new ArrayList<>();

    public void register(IObserver<T> observer) {
        observers.add(observer);
    }

    public void remove(IObserver<T> observer) {
        observers.remove(observer);
    }

    public void notifyObservers(T pushedData){
        for(IObserver<T> observer : observers){
            observer.update(pushedData);
        }
    }

    public void clear(){
        observers = new ArrayList<>();
    }
}
