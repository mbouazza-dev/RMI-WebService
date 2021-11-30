/**
 * AccountManager.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fr.uge.bank;

public interface AccountManager extends java.rmi.Remote {
    public void deposit(double amount, int id) throws java.rmi.RemoteException;
    public float amount(int id) throws java.rmi.RemoteException;
    public boolean withdraw(double amount, int id) throws java.rmi.RemoteException;
}
