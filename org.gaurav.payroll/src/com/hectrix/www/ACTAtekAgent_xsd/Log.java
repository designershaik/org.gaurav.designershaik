/**
 * Log.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.hectrix.www.ACTAtekAgent_xsd;

public class Log  implements java.io.Serializable {
    private long logID;

    private java.lang.String userID;

    private java.util.Calendar timestamp;

    private com.hectrix.www.ACTAtekAgent_xsd.EventType trigger;

    private java.lang.String terminalSN;

    private java.lang.String sender;

    private com.hectrix.www.ACTAtekAgent_xsd.PhotoPart photoPart;

    public Log() {
    }

    public Log(
           long logID,
           java.lang.String userID,
           java.util.Calendar timestamp,
           com.hectrix.www.ACTAtekAgent_xsd.EventType trigger,
           java.lang.String terminalSN,
           java.lang.String sender,
           com.hectrix.www.ACTAtekAgent_xsd.PhotoPart photoPart) {
           this.logID = logID;
           this.userID = userID;
           this.timestamp = timestamp;
           this.trigger = trigger;
           this.terminalSN = terminalSN;
           this.sender = sender;
           this.photoPart = photoPart;
    }


    /**
     * Gets the logID value for this Log.
     * 
     * @return logID
     */
    public long getLogID() {
        return logID;
    }


    /**
     * Sets the logID value for this Log.
     * 
     * @param logID
     */
    public void setLogID(long logID) {
        this.logID = logID;
    }


    /**
     * Gets the userID value for this Log.
     * 
     * @return userID
     */
    public java.lang.String getUserID() {
        return userID;
    }


    /**
     * Sets the userID value for this Log.
     * 
     * @param userID
     */
    public void setUserID(java.lang.String userID) {
        this.userID = userID;
    }


    /**
     * Gets the timestamp value for this Log.
     * 
     * @return timestamp
     */
    public java.util.Calendar getTimestamp() {
        return timestamp;
    }


    /**
     * Sets the timestamp value for this Log.
     * 
     * @param timestamp
     */
    public void setTimestamp(java.util.Calendar timestamp) {
        this.timestamp = timestamp;
    }


    /**
     * Gets the trigger value for this Log.
     * 
     * @return trigger
     */
    public com.hectrix.www.ACTAtekAgent_xsd.EventType getTrigger() {
        return trigger;
    }


    /**
     * Sets the trigger value for this Log.
     * 
     * @param trigger
     */
    public void setTrigger(com.hectrix.www.ACTAtekAgent_xsd.EventType trigger) {
        this.trigger = trigger;
    }


    /**
     * Gets the terminalSN value for this Log.
     * 
     * @return terminalSN
     */
    public java.lang.String getTerminalSN() {
        return terminalSN;
    }


    /**
     * Sets the terminalSN value for this Log.
     * 
     * @param terminalSN
     */
    public void setTerminalSN(java.lang.String terminalSN) {
        this.terminalSN = terminalSN;
    }


    /**
     * Gets the sender value for this Log.
     * 
     * @return sender
     */
    public java.lang.String getSender() {
        return sender;
    }


    /**
     * Sets the sender value for this Log.
     * 
     * @param sender
     */
    public void setSender(java.lang.String sender) {
        this.sender = sender;
    }


    /**
     * Gets the photoPart value for this Log.
     * 
     * @return photoPart
     */
    public com.hectrix.www.ACTAtekAgent_xsd.PhotoPart getPhotoPart() {
        return photoPart;
    }


    /**
     * Sets the photoPart value for this Log.
     * 
     * @param photoPart
     */
    public void setPhotoPart(com.hectrix.www.ACTAtekAgent_xsd.PhotoPart photoPart) {
        this.photoPart = photoPart;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Log)) return false;
        Log other = (Log) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.logID == other.getLogID() &&
            ((this.userID==null && other.getUserID()==null) || 
             (this.userID!=null &&
              this.userID.equals(other.getUserID()))) &&
            ((this.timestamp==null && other.getTimestamp()==null) || 
             (this.timestamp!=null &&
              this.timestamp.equals(other.getTimestamp()))) &&
            ((this.trigger==null && other.getTrigger()==null) || 
             (this.trigger!=null &&
              this.trigger.equals(other.getTrigger()))) &&
            ((this.terminalSN==null && other.getTerminalSN()==null) || 
             (this.terminalSN!=null &&
              this.terminalSN.equals(other.getTerminalSN()))) &&
            ((this.sender==null && other.getSender()==null) || 
             (this.sender!=null &&
              this.sender.equals(other.getSender()))) &&
            ((this.photoPart==null && other.getPhotoPart()==null) || 
             (this.photoPart!=null &&
              this.photoPart.equals(other.getPhotoPart())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += new Long(getLogID()).hashCode();
        if (getUserID() != null) {
            _hashCode += getUserID().hashCode();
        }
        if (getTimestamp() != null) {
            _hashCode += getTimestamp().hashCode();
        }
        if (getTrigger() != null) {
            _hashCode += getTrigger().hashCode();
        }
        if (getTerminalSN() != null) {
            _hashCode += getTerminalSN().hashCode();
        }
        if (getSender() != null) {
            _hashCode += getSender().hashCode();
        }
        if (getPhotoPart() != null) {
            _hashCode += getPhotoPart().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Log.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.hectrix.com/ACTAtekAgent.xsd", "Log"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "logID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timestamp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "timestamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trigger");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trigger"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.hectrix.com/ACTAtekAgent.xsd", "eventType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("terminalSN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "terminalSN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sender");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sender"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("photoPart");
        elemField.setXmlName(new javax.xml.namespace.QName("", "photoPart"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.hectrix.com/ACTAtekAgent.xsd", "PhotoPart"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
