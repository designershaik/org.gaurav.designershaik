package net.aramex.ws.ShippingAPI.v1;

public class Service_1_0Proxy implements net.aramex.ws.ShippingAPI.v1.Service_1_0_PortType {
  private String _endpoint = null;
  private net.aramex.ws.ShippingAPI.v1.Service_1_0_PortType service_1_0_PortType = null;
  
  public Service_1_0Proxy() {
    _initService_1_0Proxy();
  }
  
  public Service_1_0Proxy(String endpoint) {
    _endpoint = endpoint;
    _initService_1_0Proxy();
  }
  
  private void _initService_1_0Proxy() {
    try {
      service_1_0_PortType = (new net.aramex.ws.ShippingAPI.v1.Service_1_0_ServiceLocator()).getBasicHttpBinding_Service_1_0();
      if (service_1_0_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)service_1_0_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)service_1_0_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (service_1_0_PortType != null)
      ((javax.xml.rpc.Stub)service_1_0_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public net.aramex.ws.ShippingAPI.v1.Service_1_0_PortType getService_1_0_PortType() {
    if (service_1_0_PortType == null)
      _initService_1_0Proxy();
    return service_1_0_PortType;
  }
  
  public net.aramex.ws.ShippingAPI.v1.ShipmentTrackingResponse trackShipments(net.aramex.ws.ShippingAPI.v1.ShipmentTrackingRequest parameters) throws java.rmi.RemoteException{
    if (service_1_0_PortType == null)
      _initService_1_0Proxy();
    return service_1_0_PortType.trackShipments(parameters);
  }
  
  
}