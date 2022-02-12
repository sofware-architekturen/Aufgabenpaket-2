package com.buchlager.core.model;

import java.io.Serializable;

public class Adresse implements Serializable
{
	private static final long serialVersionUID = 0x1111111;
	
	private int id = 0;
	private String ort = null;
	
	public Adresse()
	{
		super();
	}
	
	public Adresse(int id, String ort)
	{
		super();
		this.id = id;
		this.ort = ort;
	}
	

  public int getId()
	{
		return this.id;
	}
	

  public String getOrt()
	{
		return this.ort;
	}
	
	public String toString()
	{
		return ort;
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
    Adresse other = (Adresse) obj;
    if (id != other.id)
      return false;
    return true;
  }

	
}
