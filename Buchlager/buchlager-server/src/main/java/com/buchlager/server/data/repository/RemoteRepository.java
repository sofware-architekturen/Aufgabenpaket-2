package com.buchlager.server.data.repository;

import com.buchlager.core.interfaces.IBuchlagerRemoteRepository;
import com.buchlager.core.model.Buch;
import com.buchlager.server.data.facade.BuchlagerFacade;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Collection;

public class RemoteRepository implements IBuchlagerRemoteRepository, Serializable {
    private static final long serialVersionUID = 15454L;

    private BuchlagerFacade facade = null;

    public RemoteRepository() {
        facade = BuchlagerFacade.getInstance();
    }

    @Override
    public synchronized Buch findBuchMitId(int buchId) throws RemoteException {
            return facade.findBuchMitId(buchId);
    }

    @Override
    public synchronized Collection<Buch> findBuecherVonAutor(String nachname) throws RemoteException {
        synchronized (facade){
            return facade.findBuecherVonAutor(nachname);
        }
    }

    @Override
    public synchronized Collection<Buch> findAlleBuecher() throws RemoteException {
        return facade.findAlleBuecher();
    }

    @Override
    public synchronized int getAnzahlAllerBuecher() throws RemoteException {
        return facade.getAnzahlAllerBuecher();
    }

    @Override
    public synchronized void bestandAusbuchen(int buchId, int anzahl) throws RemoteException {
        facade.bestandAusbuchen(buchId, anzahl);
    }

    @Override
    public synchronized void bestandEinbuchen(int buchId, int anzahl) throws RemoteException {
        facade.bestandEinbuchen(buchId, anzahl);
    }

    @Override
    public synchronized int getBestand(int buchId) throws RemoteException {
        return facade.getBestand(buchId);
    }

    @Override
    public synchronized String findBuecherVonAutorAsXML(String nachname) throws RemoteException {
        return facade.findBuecherVonAutorAsXML(nachname);
    }
}
