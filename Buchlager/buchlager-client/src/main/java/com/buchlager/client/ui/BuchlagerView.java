package com.buchlager.client.ui;

import java.awt.CardLayout;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JFrame;

import com.buchlager.core.interfaces.IBuchlagerRemoteFacade;
import com.buchlager.core.model.Buch;

public class BuchlagerView extends JFrame
{
  private static final long serialVersionUID = 3209504767472694065L;

  private JPanelBuchlagerView buchlagerSearchView = null;
  private JPanelBuchdetail buchdetailView = null;
  private JPanelWarenkorb warenkorbView = null;
  private CardLayout cardLayout = null;
  private IBuchlagerRemoteFacade buchlagerRemoteFacade;

  public BuchlagerView()
  {
    super("Buchlager");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.initRemoteFacade();

    this.buchlagerSearchView = new JPanelBuchlagerView(this, buchlagerRemoteFacade);
    this.buchdetailView = new JPanelBuchdetail(this);
    this.warenkorbView = new JPanelWarenkorb(this, buchlagerRemoteFacade);

    this.cardLayout = new CardLayout(20,20);
    this.getContentPane().setLayout(this.cardLayout);
    this.getContentPane().add( this.buchlagerSearchView , "SEARCH" );
    this.getContentPane().add( this.buchdetailView, "DETAIL" );
    this.getContentPane().add( this.warenkorbView, "WARENKORB" );

    this.setResizable(false);
    this.pack();
  }

  public JPanelWarenkorb getWarenkorbPanel()
  {
    return this.warenkorbView;
  }

  public void showDetail()
  {
    Buch buch = this.buchlagerSearchView.getSelectedBuch();
    this.buchdetailView.setBuch(buch);
    this.cardLayout.show(this.getContentPane(), "DETAIL");
  }

  public void showSearch()
  {
    this.cardLayout.show(this.getContentPane(), "SEARCH");
  }

  public void showWarenkorb()
  {
    this.cardLayout.show(this.getContentPane(), "WARENKORB");
  }

  private void initRemoteFacade(){
    Registry registry = null;
    try {
      registry = LocateRegistry.getRegistry();
      this.buchlagerRemoteFacade = (IBuchlagerRemoteFacade) registry.lookup("rmi://methods");
    } catch (RemoteException e) {
      e.printStackTrace();
    } catch (NotBoundException e) {
      e.printStackTrace();
    }

  }
}

