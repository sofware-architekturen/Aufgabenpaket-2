package com.buchlager.server.facade;


import java.rmi.RemoteException;

public class BuchlagerFacadeException extends RemoteException {
	private static final long serialVersionUID = 0x9999999;
	
	public BuchlagerFacadeException()
	{
		super();
	}

	public BuchlagerFacadeException(String message)
	{
		super(message);
	}
}
