package com.buchlager.server.services.observer;

import com.buchlager.core.interfaces.IObserver;
import com.buchlager.core.model.Bestellung;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.MediaType;

import java.io.Serializable;

public class RESTOrderNotificationService implements IObserver<Bestellung>, Serializable {
    private static final long serialVersionUID = 18486L;

    @Override
    public void update(Bestellung bestellung){
        Client client = ClientBuilder.newClient();
        client.target("http://localhost:8080/messages").request(MediaType.APPLICATION_JSON).post(Entity.entity(bestellung, MediaType.APPLICATION_JSON));
    }
}
