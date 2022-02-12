package com.buchlager.client.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Enumeration;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.buchlager.core.model.Buch;
import com.buchlager.core.interfaces.IBuchlagerRemoteFacade;


public class JPanelWarenkorb extends JPanel
{
  private static final long serialVersionUID = -4265792354476543154L;

  private BuchlagerView buchlagerView = null;

  private JList<Buch> content = new JList<>();
  private DefaultListModel<Buch> model = new DefaultListModel<>();

  private JButton backButton = new JButton("Zurück");
  private JButton kaufenButton = new JButton("Kaufen");

  private IBuchlagerRemoteFacade buchlagerRemoteFacade = null;


  public JPanelWarenkorb(BuchlagerView buchlagerView, IBuchlagerRemoteFacade buchlagerRemoteFacade)
  {
    super();

    this.buchlagerRemoteFacade = buchlagerRemoteFacade;
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
            buchlagerRemoteFacade.bestandAusbuchen(buch.getId(), 1);
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
}
