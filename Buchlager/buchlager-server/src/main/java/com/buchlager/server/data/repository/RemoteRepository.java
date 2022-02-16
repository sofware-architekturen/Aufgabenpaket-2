package com.buchlager.server.data.repository;

import com.buchlager.core.interfaces.IBuchlagerRepository;
import com.buchlager.core.model.Buch;
import com.buchlager.server.data.facade.BuchlagerFacade;
import com.buchlager.server.services.order.OrderService;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Collection;

public class RemoteRepository implements IBuchlagerRepository, Serializable {
    private static final long serialVersionUID = 15454L;

    private BuchlagerFacade facade;
    private OrderService orderService;

    public RemoteRepository() {
        facade = BuchlagerFacade.getInstance();
        orderService = new OrderService(this);
    }

    @Override
    public synchronized Buch findBuchMitId(int buchId) throws RemoteException {
        synchronized (facade){
            return facade.findBuchMitId(buchId);
        }
    }

    @Override
    public synchronized Collection<Buch> findBuecherVonAutor(String nachname) throws RemoteException {
        synchronized (facade){
            return facade.findBuecherVonAutor(nachname);
        }
    }

    @Override
    public synchronized Collection<Buch> findAlleBuecher() throws RemoteException {
        synchronized (facade){
            return facade.findAlleBuecher();
        }
    }

    @Override
    public synchronized int getAnzahlAllerBuecher() throws RemoteException {
        synchronized (facade){
            return facade.getAnzahlAllerBuecher();
        }
    }

    @Override
    public synchronized void bestandAusbuchen(int buchId, int anzahl) throws RemoteException {
        if(getBestand(buchId) >= 1) {
            synchronized (facade){
                facade.bestandAusbuchen(buchId, anzahl);
            }
        }else {
            orderService.makeOrder(buchId);
        }
    }

    @Override
    public synchronized void bestandEinbuchen(int buchId, int anzahl) throws RemoteException {
        synchronized (facade){
            facade.bestandEinbuchen(buchId, anzahl);
        }
    }

    @Override
    public synchronized int getBestand(int buchId) throws RemoteException {
        synchronized (facade){
            return facade.getBestand(buchId);
        }
    }

    @Override
    public synchronized String findBuecherVonAutorAsXML(String nachname) throws RemoteException {
        synchronized (facade){
            return facade.findBuecherVonAutorAsXML(nachname);
        }
    }
}
