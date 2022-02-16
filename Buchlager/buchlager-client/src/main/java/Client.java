import javax.swing.SwingUtilities;

import com.buchlager.client.ui.BuchlagerView;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client
{
  public static void main(String[] args) throws RemoteException, NotBoundException {
    // Starte der GUI
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        BuchlagerView display = new BuchlagerView();
        display.setSize(800, 600);
        display.setLocationRelativeTo(null);
        display.setVisible(true);
      }
    });
  }
}
