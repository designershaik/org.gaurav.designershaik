/**
 * Service_1_0.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.aramex.ws.ShippingAPI.v1;

public interface Service_1_0 extends java.rmi.Remote {
    public net.aramex.ws.ShippingAPI.v1.ShipmentTrackingResponse trackShipments(net.aramex.ws.ShippingAPI.v1.ShipmentTrackingRequest parameters) throws java.rmi.RemoteException;
    public net.aramex.ws.ShippingAPI.v1.PickupTrackingResponse trackPickup(net.aramex.ws.ShippingAPI.v1.PickupTrackingRequest parameters) throws java.rmi.RemoteException;
}
