package com.hectrix.www.ACTAtekAgent_service;

public class ACTAtekAgentPortTypeProxy implements com.hectrix.www.ACTAtekAgent_service.ACTAtekAgentPortType {
  private String _endpoint = null;
  private com.hectrix.www.ACTAtekAgent_service.ACTAtekAgentPortType aCTAtekAgentPortType = null;
  
  public ACTAtekAgentPortTypeProxy() {
    _initACTAtekAgentPortTypeProxy();
  }
  
  public ACTAtekAgentPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initACTAtekAgentPortTypeProxy();
  }
  
  private void _initACTAtekAgentPortTypeProxy() {
    try {
      aCTAtekAgentPortType = (new com.hectrix.www.ACTAtekAgent_service.ACTAtekAgentLocator()).getACTAtekAgent();
      if (aCTAtekAgentPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)aCTAtekAgentPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)aCTAtekAgentPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (aCTAtekAgentPortType != null)
      ((javax.xml.rpc.Stub)aCTAtekAgentPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.hectrix.www.ACTAtekAgent_service.ACTAtekAgentPortType getACTAtekAgentPortType() {
    if (aCTAtekAgentPortType == null)
      _initACTAtekAgentPortTypeProxy();
    return aCTAtekAgentPortType;
  }
  
  public java.lang.String log(java.lang.String magic, com.hectrix.www.ACTAtekAgent_xsd.Log unencryptedLog) throws java.rmi.RemoteException{
    if (aCTAtekAgentPortType == null)
      _initACTAtekAgentPortTypeProxy();
    return aCTAtekAgentPortType.log(magic, unencryptedLog);
  }
  
  public java.lang.String encryptLog(byte[] encryptedLog, com.hectrix.www.ACTAtekAgent_xsd.PhotoPart photo) throws java.rmi.RemoteException{
    if (aCTAtekAgentPortType == null)
      _initACTAtekAgentPortTypeProxy();
    return aCTAtekAgentPortType.encryptLog(encryptedLog, photo);
  }
  
  public java.lang.String encryptLogMultiple(com.hectrix.www.ACTAtekAgent_xsd.ELog[] encryptedLogs) throws java.rmi.RemoteException{
    if (aCTAtekAgentPortType == null)
      _initACTAtekAgentPortTypeProxy();
    return aCTAtekAgentPortType.encryptLogMultiple(encryptedLogs);
  }
  
  public java.lang.String sync(java.lang.String magic, java.lang.String registrationID) throws java.rmi.RemoteException{
    if (aCTAtekAgentPortType == null)
      _initACTAtekAgentPortTypeProxy();
    return aCTAtekAgentPortType.sync(magic, registrationID);
  }
  
  
}