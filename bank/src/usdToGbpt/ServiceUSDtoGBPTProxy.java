package usdToGbpt;

public class ServiceUSDtoGBPTProxy implements usdToGbpt.ServiceUSDtoGBPT {
  private String _endpoint = null;
  private usdToGbpt.ServiceUSDtoGBPT serviceUSDtoGBPT = null;
  
  public ServiceUSDtoGBPTProxy() {
    _initServiceUSDtoGBPTProxy();
  }
  
  public ServiceUSDtoGBPTProxy(String endpoint) {
    _endpoint = endpoint;
    _initServiceUSDtoGBPTProxy();
  }
  
  private void _initServiceUSDtoGBPTProxy() {
    try {
      serviceUSDtoGBPT = (new usdToGbpt.ServiceUSDtoGBPTServiceLocator()).getServiceUSDtoGBPT();
      if (serviceUSDtoGBPT != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)serviceUSDtoGBPT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)serviceUSDtoGBPT)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (serviceUSDtoGBPT != null)
      ((javax.xml.rpc.Stub)serviceUSDtoGBPT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public usdToGbpt.ServiceUSDtoGBPT getServiceUSDtoGBPT() {
    if (serviceUSDtoGBPT == null)
      _initServiceUSDtoGBPTProxy();
    return serviceUSDtoGBPT;
  }
  
  public double convert(double usd) throws java.rmi.RemoteException{
    if (serviceUSDtoGBPT == null)
      _initServiceUSDtoGBPTProxy();
    return serviceUSDtoGBPT.convert(usd);
  }
  
  
}