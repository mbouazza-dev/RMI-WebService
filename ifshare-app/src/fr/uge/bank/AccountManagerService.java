/**
 * AccountManagerService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fr.uge.bank;

public interface AccountManagerService extends javax.xml.rpc.Service {
    public java.lang.String getAccountManagerAddress();

    public fr.uge.bank.AccountManager getAccountManager() throws javax.xml.rpc.ServiceException;

    public fr.uge.bank.AccountManager getAccountManager(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
