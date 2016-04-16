/**
 * WebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.aisino.kp;

public interface WebService extends javax.xml.rpc.Service {
    public java.lang.String getWebServiceHttpPortAddress();

    public com.aisino.kp.WebServicePortType getWebServiceHttpPort() throws javax.xml.rpc.ServiceException;

    public com.aisino.kp.WebServicePortType getWebServiceHttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
