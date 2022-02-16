import com.buchlager.core.interfaces.IBuchlagerRepository;
import com.buchlager.server.data.repository.RemoteRepository;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    public static void main(String[] args) throws RemoteException {
        IBuchlagerRepository remoteRepository = new RemoteRepository();

        UnicastRemoteObject.exportObject(remoteRepository,0);

        Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        registry.rebind("rmi://methods/repository",remoteRepository);
        System.out.printf("Server is running!");
    }

}
