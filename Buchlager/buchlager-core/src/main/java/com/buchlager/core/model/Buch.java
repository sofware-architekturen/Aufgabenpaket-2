package com.buchlager.core.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Buch implements Serializable
{
	private static final long serialVersionUID = 0x3333333;

	private int id = 0;
	private String titel = null;
	private Verlag verlag = null;


	private Set<Autor> autoren = null;

	public Buch()
	{
		super();
	}

	public Buch(int id, String titel, Verlag verlag)
	{
		super();
		this.id = id;
		this.titel = titel;
		this.verlag = verlag;
		if( verlag != null )
		{
			verlag.addBuch( this );
		}

		this.autoren = new HashSet<>();
	}


  public int getId()
	{
		return this.id;
	}


  public String getTitel()
	{
		return this.titel;
	}



  public Verlag getVerlag()
	{
		return this.verlag;
	}


  public void setVerlag(Verlag verlag)
	{
		if( this.verlag == null ) return;

		if( this.verlag.getBuecher().contains( this ) == false )
		{
			verlag.addBuch(this);
		}
		this.verlag = verlag;
	}


  public Collection<Autor> getAutoren()
	{
		return this.autoren;
	}


  public void addAutor(Autor autor)
	{
		if( this.autoren == null ) return;

		if( autor.getBuecher().contains( this ) == false )
		{
			((Autor) autor).addBuchRelationship( this );
		}
		this.autoren.add( autor );
	}

	protected void addAutorRelationship(Autor autor)
	{
		if( this.autoren == null ) return;

		this.autoren.add( autor );
	}


	public String toString()
	{
		return titel + " (" + verlag.getName() + ")";
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
    Buch other = (Buch) obj;
    if (id != other.id)
      return false;
    return true;
  }

}
