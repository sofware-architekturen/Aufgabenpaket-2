package com.buchlager.client.ui;

import java.awt.BorderLayout;
import java.awt.event.MouseListener;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.buchlager.core.model.Buch;

public class CompJPanelListContainer extends JPanel
{
  private static final long serialVersionUID = 1739945859088359874L;

  private CompJListModelBuchliste listmodel = new CompJListModelBuchliste();
  private JList<Buch> jlist = new JList<>();

  public CompJPanelListContainer()
  {
    super();
    this.jlist.setModel( this.listmodel );
    this.jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    this.setLayout( new BorderLayout(50, 50));
    JScrollPane scrollPane = new JScrollPane(this.jlist);
    this.add( scrollPane, BorderLayout.CENTER);
  }

  public void addBuch(Buch buch)
  {
    this.listmodel.addElementAt(buch);
  }

  public Buch getBuch(int index)
  {
   return  this.listmodel.getElementAt(index);
  }

  public Buch getSelectedBuch()
  {
    return this.jlist.getSelectedValue();
  }

  public void removeAlleBuecher()
  {
    this.listmodel.removeAll();
  }

  public void addMouseListener(MouseListener mouseListener)
  {
    this.jlist.addMouseListener(mouseListener);
  }

}
