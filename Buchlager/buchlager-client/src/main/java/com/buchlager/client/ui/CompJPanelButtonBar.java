package com.buchlager.client.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CompJPanelButtonBar extends JPanel
{
  private static final long serialVersionUID = 5219025587412926540L;
  
  private JButton       warenkorbButton = new JButton("Warenkorb");
  private JButton       detailButton = new JButton("   Details  ");
  private JButton       exitButton = new JButton("        Exit         ");
  
  public CompJPanelButtonBar()
  {
    super();
    this.setLayout( new FlowLayout(FlowLayout.CENTER, 20, 5));
    this.add(this.warenkorbButton);
    this.add(this.detailButton);
    this.add(this.exitButton);
  }
  
  public void addActionListenerOnExit(ActionListener actionListener)
  {
    this.exitButton.addActionListener( actionListener );
  }
  
  public void addActionListenerOnWarenkorb(ActionListener actionListener)
  {
    this.warenkorbButton.addActionListener( actionListener );
  }
  
  public void addActionListenerOnDetails(ActionListener actionListener)
  {
    this.detailButton.addActionListener( actionListener );
  }
}
