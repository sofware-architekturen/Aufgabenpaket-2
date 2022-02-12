package com.buchlager.client.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.buchlager.core.model.Autor;
import com.buchlager.core.model.Buch;

public class JPanelBuchdetail extends JPanel
{
  private static final long serialVersionUID = -4265792354476543154L;

  private BuchlagerView buchlagerView = null;

  private JTextField titel = new JTextField();
  private JTextField autoren = new JTextField();
  private JTextField verlag = new JTextField();

  private JButton backButton = new JButton("Zurück");
  private JButton warenkorbButton = new JButton("In den Warenkorb");

  private Buch buch = null;

  public JPanelBuchdetail(BuchlagerView buchlagerView)
  {
    super();

    this.buchlagerView = buchlagerView;

    this.setLayout(null);
    JLabel titleLabel = new JLabel("Titel:");
    this.add(titleLabel);
    this.add(this.titel);
    titleLabel.setBounds(20, 20, 80, 30);
    this.titel.setBounds(120, 20, 350, 30);
    this.titel.setEditable(false);

    JLabel autorenLabel = new JLabel("Autoren: ");
    this.add(autorenLabel);
    this.add(this.autoren);
    autorenLabel.setBounds(20, 60, 80, 30);
    this.autoren.setBounds(120, 60, 350, 30);
    this.autoren.setEditable(false);

    JLabel verlagLabel = new JLabel("Verlag: ");
    this.add(verlagLabel);
    this.add(this.verlag);
    verlagLabel.setBounds(20, 100, 80, 30);
    this.verlag.setBounds(120, 100, 350, 30);
    this.verlag.setEditable(false);

    this.add(this.backButton);
    this.add(this.warenkorbButton);
    this.backButton.setBounds(120, 150, 80, 30);
    this.warenkorbButton.setBounds(250, 150, 150, 30);

    this.initListener();
  }

  public Buch getBuch()
  {
    return this.buch;
  }

  public void setBuch(Buch buch)
  {
    if (buch != null)
    {
      this.buch = buch;
      this.titel.setText(buch.getTitel());
      StringBuilder strBuild = new StringBuilder();
      Iterator<Autor> itr = this.buch.getAutoren().iterator();
      while (itr.hasNext())
      {
        Autor a = itr.next();
        strBuild.append(a.getVorname() + " " + a.getNachname());
        if (itr.hasNext())
          strBuild.append(", ");
      }
      this.autoren.setText(strBuild.toString());
      this.verlag.setText(buch.getVerlag().getName());
    }
    else
    {
      this.titel.setText(" - - - ");
      this.autoren.setText(" - - - ");
      this.verlag.setText(" - - - ");

    }
  }

  private void initListener()
  {
    this.warenkorbButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        buchlagerView.getWarenkorbPanel().addBuch(getBuch());
        buchlagerView.showWarenkorb();
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
