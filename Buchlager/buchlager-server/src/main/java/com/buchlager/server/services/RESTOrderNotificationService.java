package com.buchlager.server.services;

import com.buchlager.core.interfaces.IRemoteObserver;
import com.buchlager.core.model.Bestellung;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.Serializable;
import java.rmi.RemoteException;

public class RESTOrderNotificationService implements IRemoteObserver<Bestellung>, Serializable {
    private static final long serialVersionUID = 18486L;

    @Override
    public void update(Bestellung bestellung) throws RemoteException {
            Client client = ClientBuilder.newClient();
//        WebTarget webTarget = client.target("http://localhost:8080");
//        WebTarget messageTarget = webTarget.path("messages");
//        Invocation.Builder invocationBuilder = messageTarget.request(MediaType.APPLICATION_JSON);
//        Response response = invocationBuilder.get();

        client.target("http://localhost:8080/messages").request(MediaType.APPLICATION_JSON).post(Entity.entity(bestellung, MediaType.APPLICATION_JSON));
    }
}
