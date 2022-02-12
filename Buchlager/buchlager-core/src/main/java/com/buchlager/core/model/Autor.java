package com.buchlager.core.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Autor implements Serializable
{
	private static final long serialVersionUID = 0x2222222;

	private int id = 0;
	private String vorname = null;
	private String nachname = null;

	private Set<Buch> buecher = null;

	public Autor()
	{
		super();
	}

	public Autor(int id, String vorname, String nachname)
	{
		super();
		this.id = id;
		this.vorname = vorname;
		this.nachname = nachname;

		this.buecher = new HashSet<>();
	}


  public int getId()
	{
		return this.id;
	}

  public String getVorname()
	{
		return this.vorname;
	}



  public String getNachname()
	{
		return this.nachname;
	}


  public Collection<Buch> getBuecher()
	{
		return this.buecher;
	}


  public void addBuch(Buch buch)
	{
		if( this.buecher == null ) return;

		if( buch.getAutoren().contains(this) == false )
		{
			((Buch) buch).addAutorRelationship( this );
		}
		this.buecher.add(  buch );
	}

	protected void addBuchRelationship(Buch buch)
	{
		if( this.buecher == null ) return;

		this.buecher.add( buch );
	}

	public String toString()
	{
		return vorname + " " + nachname;
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
    Autor other = (Autor) obj;
    if (id != other.id)
      return false;
    return true;
  }

}
