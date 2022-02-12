package com.buchlager.client.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.Collection;

import javax.swing.JPanel;

import com.buchlager.core.interfaces.IBuchlagerRemoteFacade;
import com.buchlager.core.model.Buch;


public class JPanelBuchlagerView extends JPanel
{
  private static final long serialVersionUID = 3209504767472694065L;

  private BuchlagerView buchlagerView = null;

  private CompJPanelBuchSearch jSearchComp = null;
  private CompJPanelListContainer jListContainer = null;
  private CompJPanelButtonBar jButtonBar = null;
  private IBuchlagerRemoteFacade buchlagerRemoteFacade = null;

  public JPanelBuchlagerView(BuchlagerView buchlagerView, IBuchlagerRemoteFacade buchlagerRemoteFacade)
  {
    super();

    this.buchlagerRemoteFacade = buchlagerRemoteFacade;
    this.buchlagerView = buchlagerView;

    this.jSearchComp = new CompJPanelBuchSearch();
    this.jListContainer = new CompJPanelListContainer();
    this.jButtonBar = new CompJPanelButtonBar();

    this.setLayout(new BorderLayout());
    this.add( this.jSearchComp, BorderLayout.NORTH );
    this.add( this.jListContainer, BorderLayout.CENTER );
    this.add( this.jButtonBar, BorderLayout.SOUTH );

    this.initListener();
  }

  public void addBuecher(Collection<Buch> buecher)
  {
    this.jListContainer.removeAlleBuecher();
    for( Buch buch : buecher )
    {
      this.jListContainer.addBuch(buch);
    }

  }

  public Buch getSelectedBuch()
  {
    return this.jListContainer.getSelectedBuch();
  }

  private void initListener()
  {
    this. jSearchComp.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        String input = jSearchComp.getTextFieldInput();
        Collection<Buch> buecher = null;
        try {
          buecher = buchlagerRemoteFacade.findBuecherVonAutor(input);
        } catch (RemoteException ex) {
          ex.printStackTrace();
        }
        if( buecher != null )
        {
          addBuecher(buecher);
        }
        else
        {
          jListContainer.removeAlleBuecher();
        }
      }
    });

    this.jListContainer.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e)
      {
        if(e.getClickCount()==2){
          buchlagerView.showDetail();
        }
        super.mouseClicked(e);
      }
    });

    this.jButtonBar.addActionListenerOnDetails(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        buchlagerView.showDetail();
      }
    });

    this.jButtonBar.addActionListenerOnWarenkorb(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        buchlagerView.showWarenkorb();
      }
    });

    this.jButtonBar.addActionListenerOnExit(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e)
      {
          System.exit(0);
      }
    });
  }
}
