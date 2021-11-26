/**
 * ServiceUSDtoGBPTServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package usdToGbpt;

public class ServiceUSDtoGBPTServiceLocator extends org.apache.axis.client.Service implements usdToGbpt.ServiceUSDtoGBPTService {

    public ServiceUSDtoGBPTServiceLocator() {
    }


    public ServiceUSDtoGBPTServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ServiceUSDtoGBPTServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ServiceUSDtoGBPT
    private java.lang.String ServiceUSDtoGBPT_address = "http://localhost:8080/WebServiceProject/services/ServiceUSDtoGBPT";

    public java.lang.String getServiceUSDtoGBPTAddress() {
        return ServiceUSDtoGBPT_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ServiceUSDtoGBPTWSDDServiceName = "ServiceUSDtoGBPT";

    public java.lang.String getServiceUSDtoGBPTWSDDServiceName() {
        return ServiceUSDtoGBPTWSDDServiceName;
    }

    public void setServiceUSDtoGBPTWSDDServiceName(java.lang.String name) {
        ServiceUSDtoGBPTWSDDServiceName = name;
    }

    public usdToGbpt.ServiceUSDtoGBPT getServiceUSDtoGBPT() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ServiceUSDtoGBPT_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getServiceUSDtoGBPT(endpoint);
    }

    public usdToGbpt.ServiceUSDtoGBPT getServiceUSDtoGBPT(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            usdToGbpt.ServiceUSDtoGBPTSoapBindingStub _stub = new usdToGbpt.ServiceUSDtoGBPTSoapBindingStub(portAddress, this);
            _stub.setPortName(getServiceUSDtoGBPTWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setServiceUSDtoGBPTEndpointAddress(java.lang.String address) {
        ServiceUSDtoGBPT_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (usdToGbpt.ServiceUSDtoGBPT.class.isAssignableFrom(serviceEndpointInterface)) {
                usdToGbpt.ServiceUSDtoGBPTSoapBindingStub _stub = new usdToGbpt.ServiceUSDtoGBPTSoapBindingStub(new java.net.URL(ServiceUSDtoGBPT_address), this);
                _stub.setPortName(getServiceUSDtoGBPTWSDDServiceName());
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
        if ("ServiceUSDtoGBPT".equals(inputPortName)) {
            return getServiceUSDtoGBPT();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://usdToGbpt", "ServiceUSDtoGBPTService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://usdToGbpt", "ServiceUSDtoGBPT"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ServiceUSDtoGBPT".equals(portName)) {
            setServiceUSDtoGBPTEndpointAddress(address);
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
