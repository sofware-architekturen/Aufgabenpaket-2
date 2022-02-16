package com.buchlager.core.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IBuchlagerBestandService extends Remote {
    int getAnzahlAllerBuecher() throws RemoteException;

    void bestandAusbuchen(int buchId, int anzahl) throws RemoteException;

    void bestandEinbuchen(int buchId, int anzahl) throws RemoteException;

    int getBestand(int buchId) throws RemoteException;
}
