package com.buchlager.client.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CompJPanelBuchSearch extends JPanel
{
  private static final long serialVersionUID = 5219025587412926540L;
  
  private JLabel         labelText = new JLabel("Autorensuche");
  private JTextField    searchInput = new JTextField(30);
  private JButton       searchButton = new JButton("Suchen");
  
  public CompJPanelBuchSearch()
  {
    super();
    this.setLayout( new FlowLayout(FlowLayout.CENTER));
    this.add(this.labelText);
    this.add(this.searchInput);
    this.add(this.searchButton);
  }
  
  public String getTextFieldInput()
  {
    return this.searchInput.getText();
  }
  
  public void addActionListener(ActionListener actionListener)
  {
    this.searchButton.addActionListener( actionListener );
    this.searchInput.addActionListener( actionListener );
  }
}
