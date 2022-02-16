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

import com.buchlager.core.interfaces.IBuchlagerRepository;
import com.buchlager.core.model.Buch;

public class JPanelWarenkorb extends JPanel
{
  private static final long serialVersionUID = -4265792354476543154L;

  private BuchlagerView buchlagerView = null;

  private JList<Buch> content = new JList<>();
  private DefaultListModel<Buch> model = new DefaultListModel<>();

  private JButton backButton = new JButton("Zurück");
  private JButton kaufenButton = new JButton("Kaufen");

  private IBuchlagerRepository repository = null;


  public JPanelWarenkorb(BuchlagerView buchlagerView, IBuchlagerRepository buchlagerRemoteFacade)
  {
    super();

    this.repository = buchlagerRemoteFacade;
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
            System.out.println(repository.getBestand(buch.getId()) >= 1 ? "Das Buch " + buch.getTitel() + " wird versendet" : "Das Buch " + buch.getTitel() + " wird nachbestellt");
            repository.bestandAusbuchen(buch.getId(), 1);
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
