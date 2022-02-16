package com.buchlager.core.interfaces;

import java.rmi.RemoteException;

public interface IObserver<T>{
    void update(T arg);
}
