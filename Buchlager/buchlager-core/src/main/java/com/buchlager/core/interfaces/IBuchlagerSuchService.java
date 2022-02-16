package com.buchlager.core.interfaces;

import com.buchlager.core.model.Buch;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface IBuchlagerSuchService extends Remote {
    Buch findBuchMitId(int buchId) throws RemoteException;

    Collection<Buch> findBuecherVonAutor(String nachname)throws RemoteException;

    Collection<Buch> findAlleBuecher() throws RemoteException;

    String findBuecherVonAutorAsXML(String nachname)throws RemoteException;
}
