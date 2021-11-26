/**
 * AccountManager.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fr.uge.bank;

public interface AccountManager extends java.rmi.Remote {
    public long amount(int id) throws java.rmi.RemoteException;
    public boolean withdraw(int amount, int id) throws java.rmi.RemoteException;
    public void deposit(int amount, int id) throws java.rmi.RemoteException;
}
