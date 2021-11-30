package fr.uge.bank;

public class AccountManagerProxy implements fr.uge.bank.AccountManager {
  private String _endpoint = null;
  private fr.uge.bank.AccountManager accountManager = null;
  
  public AccountManagerProxy() {
    _initAccountManagerProxy();
  }
  
  public AccountManagerProxy(String endpoint) {
    _endpoint = endpoint;
    _initAccountManagerProxy();
  }
  
  private void _initAccountManagerProxy() {
    try {
      accountManager = (new fr.uge.bank.AccountManagerServiceLocator()).getAccountManager();
      if (accountManager != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)accountManager)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)accountManager)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (accountManager != null)
      ((javax.xml.rpc.Stub)accountManager)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public fr.uge.bank.AccountManager getAccountManager() {
    if (accountManager == null)
      _initAccountManagerProxy();
    return accountManager;
  }
  
  public void deposit(float amount, int id) throws java.rmi.RemoteException{
    if (accountManager == null)
      _initAccountManagerProxy();
    accountManager.deposit(amount, id);
  }
  
  public boolean withdraw(float amount, int id) throws java.rmi.RemoteException{
    if (accountManager == null)
      _initAccountManagerProxy();
    return accountManager.withdraw(amount, id);
  }
  
  public float amount(int id) throws java.rmi.RemoteException{
    if (accountManager == null)
      _initAccountManagerProxy();
    return accountManager.amount(id);
  }
  
  
}