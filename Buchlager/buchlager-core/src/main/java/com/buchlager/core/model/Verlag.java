package com.buchlager.core.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Verlag implements Serializable
{
	private static final long serialVersionUID = 0x4444444;

	private int id = 0;
	private String name = null;
	private Adresse adr = null;

	private Set<Buch> buecher = null;

	public Verlag()
	{
		super();
	}

	public Verlag(int id, String name, Adresse adresse)
	{
		super();
		this.id = id;
		this.name = name;
		this.adr = adresse;

		this.buecher = new HashSet<>();
	}


  public int getId()
	{
		return this.id;
	}


  public String getName()
	{
		return this.name;
	}


  public Adresse getAdresse()
	{
		return this.adr;
	}


  public Collection<Buch> getBuecher()
	{
		return this.buecher;
	}


  public void addBuch(Buch buch)
	{
		if( this.buecher == null ) return;

		this.buecher.add(buch);
	}

	public String toString()
	{
		return name;
	}

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    return result;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Verlag other = (Verlag) obj;
    if (id != other.id)
      return false;
    return true;
  }

}
