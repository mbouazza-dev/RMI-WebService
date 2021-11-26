/**
 * AccountManagerServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fr.uge.bank;

public class AccountManagerServiceLocator extends org.apache.axis.client.Service implements fr.uge.bank.AccountManagerService {

    public AccountManagerServiceLocator() {
    }


    public AccountManagerServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AccountManagerServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for AccountManager
    private java.lang.String AccountManager_address = "http://localhost:8080/bank-service/services/AccountManager";

    public java.lang.String getAccountManagerAddress() {
        return AccountManager_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AccountManagerWSDDServiceName = "AccountManager";

    public java.lang.String getAccountManagerWSDDServiceName() {
        return AccountManagerWSDDServiceName;
    }

    public void setAccountManagerWSDDServiceName(java.lang.String name) {
        AccountManagerWSDDServiceName = name;
    }

    public fr.uge.bank.AccountManager getAccountManager() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AccountManager_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAccountManager(endpoint);
    }

    public fr.uge.bank.AccountManager getAccountManager(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            fr.uge.bank.AccountManagerSoapBindingStub _stub = new fr.uge.bank.AccountManagerSoapBindingStub(portAddress, this);
            _stub.setPortName(getAccountManagerWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAccountManagerEndpointAddress(java.lang.String address) {
        AccountManager_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (fr.uge.bank.AccountManager.class.isAssignableFrom(serviceEndpointInterface)) {
                fr.uge.bank.AccountManagerSoapBindingStub _stub = new fr.uge.bank.AccountManagerSoapBindingStub(new java.net.URL(AccountManager_address), this);
                _stub.setPortName(getAccountManagerWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("AccountManager".equals(inputPortName)) {
            return getAccountManager();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://bank.uge.fr", "AccountManagerService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://bank.uge.fr", "AccountManager"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("AccountManager".equals(portName)) {
            setAccountManagerEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
