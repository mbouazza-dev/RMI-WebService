package fr.converter;

public class CurrencyServerSoapProxy implements fr.converter.CurrencyServerSoap {
  private String _endpoint = null;
  private fr.converter.CurrencyServerSoap currencyServerSoap = null;
  
  public CurrencyServerSoapProxy() {
    _initCurrencyServerSoapProxy();
  }
  
  public CurrencyServerSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initCurrencyServerSoapProxy();
  }
  
  private void _initCurrencyServerSoapProxy() {
    try {
      currencyServerSoap = (new fr.converter.CurrencyServerLocator()).getCurrencyServerSoap();
      if (currencyServerSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)currencyServerSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)currencyServerSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (currencyServerSoap != null)
      ((javax.xml.rpc.Stub)currencyServerSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public fr.converter.CurrencyServerSoap getCurrencyServerSoap() {
    if (currencyServerSoap == null)
      _initCurrencyServerSoapProxy();
    return currencyServerSoap;
  }
  
  public java.lang.Object convert(java.lang.String licenseKey, java.lang.String fromCurrency, java.lang.String toCurrency, double amount, boolean rounding, java.lang.String format, fr.converter.CurncsrvReturnRate returnRate, java.lang.String time, java.lang.String type) throws java.rmi.RemoteException{
    if (currencyServerSoap == null)
      _initCurrencyServerSoapProxy();
    return currencyServerSoap.convert(licenseKey, fromCurrency, toCurrency, amount, rounding, format, returnRate, time, type);
  }
  
}