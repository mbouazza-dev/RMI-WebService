/**
 * CurrencyServerSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fr.converter;

public interface CurrencyServerSoap extends java.rmi.Remote {

    /**
     * Converts a currency amount. The result is either a number or
     * a string which can optionally be rounded and formatted. When a string
     * is returned, either fromCurrency or toCurrency (but not both) may
     * contain a list of semicolon-separated active currencies. Leave the
     * time and type parameters empty for forward compatibility.
     */
    public java.lang.Object convert(java.lang.String licenseKey, java.lang.String fromCurrency, java.lang.String toCurrency, double amount, boolean rounding, java.lang.String format, fr.converter.CurncsrvReturnRate returnRate, java.lang.String time, java.lang.String type) throws java.rmi.RemoteException;

}
