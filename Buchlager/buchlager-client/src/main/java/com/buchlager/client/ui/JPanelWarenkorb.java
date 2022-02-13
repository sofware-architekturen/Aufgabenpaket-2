package com.buchlager.client.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.buchlager.core.interfaces.IRemoteObserver;
import com.buchlager.core.interfaces.IRemoteSubject;
import com.buchlager.core.model.Bestellung;
import com.buchlager.core.model.Buch;
import com.buchlager.core.interfaces.IBuchlagerRemoteRepository;


public class JPanelWarenkorb extends JPanel
{
  private static final long serialVersionUID = -4265792354476543154L;

  private BuchlagerView buchlagerView = null;

  private JList<Buch> content = new JList<>();
  private DefaultListModel<Buch> model = new DefaultListModel<>();

  private JButton backButton = new JButton("Zurück");
  private JButton kaufenButton = new JButton("Kaufen");

  private IBuchlagerRemoteRepository buchlagerRemoteRepository = null;
  private IRemoteObserver jmsOrderNotificationService = null;
  private IRemoteObserver restOrderNotificationService = null;
  private IRemoteSubject orderSubject = null;


  public JPanelWarenkorb(BuchlagerView buchlagerView, IBuchlagerRemoteRepository buchlagerRemoteFacade)
  {
    super();

    initObserver();

    this.buchlagerRemoteRepository = buchlagerRemoteFacade;
    this.buchlagerView = buchlagerView;
    this.content.setModel(  this.model );
    this.setLayout( new BorderLayout() );

    JScrollPane scrollPane = new JScrollPane(this.content);
    this.add(scrollPane, BorderLayout.CENTER );

    JLabel label = new JLabel("Inhalt des Warenkorbs:");
    this.add(label, BorderLayout.NORTH );

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 10));
    buttonPanel.add(this.backButton);
    buttonPanel.add(this.kaufenButton);
    this.add(buttonPanel, BorderLayout.SOUTH);

    this.initListener();
  }


  public void addBuch(Buch buch)
  {
      this.model.addElement(buch);
  }

  private void initListener()
  {
    this.kaufenButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        Enumeration<Buch> en = model.elements();
        while( en.hasMoreElements() )
        {
          Buch buch = en.nextElement();
          try
          {
            buchlagerRemoteRepository.bestandAusbuchen(buch.getId(), 1);
            if(buchlagerRemoteRepository.getBestand(buch.getId()) <1 ){
                orderBook(buch.getId(), 5);
                new Thread(()->{
                  try {
                    TimeUnit.SECONDS.sleep(5);
                    buchlagerRemoteRepository.bestandEinbuchen(buch.getId(), 5);
                    System.out.println("Bestand für das Buch " + buch.getTitel() + " aktualisiert");
                  } catch (InterruptedException ex) {
                    ex.printStackTrace();
                  } catch (RemoteException ex) {
                    ex.printStackTrace();
                  }
                }).start();
            }
            System.out.println("Das Buch " + buch.getTitel() + " wird versendet");

          }
          catch (RemoteException e1)
          {
            System.out.println("Das Buch " + buch.getTitel() + " zur Zeit nicht lieferbar");
          }
        }
        model.removeAllElements();
      }
    });

    this.backButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        buchlagerView.showSearch();
      }
    });
  }

  private void orderBook(int bookId, int amount){
    try {
      Registry registry = LocateRegistry.getRegistry();
      jmsOrderNotificationService = (IRemoteObserver<Bestellung>) registry.lookup("rmi://methods/jmsobserver");
      restOrderNotificationService = (IRemoteObserver<Bestellung>) registry.lookup("rmi://methods/restobserver");
      orderSubject = (IRemoteSubject<Bestellung>) registry.lookup("rmi://methods/subject");

      orderSubject.register(jmsOrderNotificationService);
      orderSubject.register(restOrderNotificationService);

      orderSubject.register(jmsOrderNotificationService);
      orderSubject.register(restOrderNotificationService);
      orderSubject.notifyObservers(new Bestellung(bookId,amount));

      orderSubject.clear();
    } catch (RemoteException ex) {
      ex.printStackTrace();
    } catch (NotBoundException ex) {
      ex.printStackTrace();
    }
  }

  private void initObserver(){

  }
}
