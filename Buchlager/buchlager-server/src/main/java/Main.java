import com.buchlager.server.facade.BuchlagerFacade;
import com.buchlager.core.interfaces.IBuchlagerRemoteFacade;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Main{

    public static void main(String[] args) throws RemoteException {
        IBuchlagerRemoteFacade facade = BuchlagerFacade.getInstance();
        UnicastRemoteObject.exportObject(facade,0);
        Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        registry.rebind("rmi://methods",facade);
        System.out.printf("Server is running!");
    }

}
