import com.buchlager.core.interfaces.IRemoteObserver;
import com.buchlager.core.interfaces.IRemoteSubject;
import com.buchlager.core.model.Bestellung;
import com.buchlager.server.data.repository.RemoteRepository;
import com.buchlager.core.interfaces.IBuchlagerRemoteRepository;
import com.buchlager.server.services.JMSOrderNotificationService;
import com.buchlager.server.services.RESTOrderNotificationService;
import com.buchlager.server.services.Subject;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    public static void main(String[] args) throws RemoteException {
        IBuchlagerRemoteRepository remoteRepository = new RemoteRepository();
        IRemoteObserver<Bestellung> jmsOrderNotificationService = new JMSOrderNotificationService();
        IRemoteObserver<Bestellung> restOrderNotificationService = new RESTOrderNotificationService();
        IRemoteSubject<Bestellung> orderSubject = new Subject<>();

        UnicastRemoteObject.exportObject(remoteRepository,0);
        UnicastRemoteObject.exportObject(jmsOrderNotificationService,0);
        UnicastRemoteObject.exportObject(restOrderNotificationService,0);
        UnicastRemoteObject.exportObject(orderSubject,0);

        Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        registry.rebind("rmi://methods/repository",remoteRepository);
        registry.rebind("rmi://methods/jmsobserver",jmsOrderNotificationService);
        registry.rebind("rmi://methods/restobserver",restOrderNotificationService);
        registry.rebind("rmi://methods/subject",orderSubject);

        System.out.printf("Server is running!");
    }

}
