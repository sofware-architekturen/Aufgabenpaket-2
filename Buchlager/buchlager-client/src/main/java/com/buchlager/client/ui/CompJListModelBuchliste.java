package com.buchlager.client.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import com.buchlager.core.model.Buch;

public class CompJListModelBuchliste implements ListModel<Buch>
{
  private List<Buch> buchliste = new ArrayList<>();
  private List<ListDataListener> listener = new ArrayList<>();

  public CompJListModelBuchliste()
  {
    super();
  }

  @Override
  public int getSize()
  {
    return this.buchliste.size();
  }

  @Override
  public void addListDataListener(ListDataListener listener)
  {
    if (listener != null)
    {
      this.listener.add(listener);
    }
  }

  @Override
  public void removeListDataListener(ListDataListener listener)
  {
    if (listener != null)
    {
      this.listener.remove(listener);
    }
  }

  @Override
  public Buch getElementAt(int index)
  {
    if (this.buchliste.size() > 0)
    {
    return this.buchliste.get(index);
    }
    else
      return null;
  }

  public void addElementAt(Buch buch)
  {
      this.buchliste.add(buch);
      this.notifyListener();
  }

  public void remove(int index)
  {
    if (index >= 0 && index < this.buchliste.size())
    {
      this.buchliste.remove(index);
      this.notifyListener();
    }
  }

  public void removeAll()
  {
    this.buchliste.clear();
    ;
    this.notifyListener();
  }

  private void notifyListener()
  {
    for (ListDataListener l : this.listener)
    {
      l.intervalAdded(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, this.buchliste.size()));
    }
  }
}
