/**
 * WebServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.aisino.kp;

public interface WebServicePortType extends java.rmi.Remote {
    public java.lang.String uploadRedInfo(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String invalidInvoice(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String distributeInvoice(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String downloadRedInfo(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String uploadInvoice(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String printInvoice(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String returnInvoice(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String printList(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String copyTax(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String queryInventory(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String queryJSPInfo(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String wasteInvoice(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String trackSource(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String clearCard(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String queryInvoice(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String invalidBlankInvoice(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String issueInvoice(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String printParams(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String queryStock(java.lang.String in0) throws java.rmi.RemoteException;
    public java.lang.String updateInvoiceStatus(java.lang.String in0) throws java.rmi.RemoteException;
}
