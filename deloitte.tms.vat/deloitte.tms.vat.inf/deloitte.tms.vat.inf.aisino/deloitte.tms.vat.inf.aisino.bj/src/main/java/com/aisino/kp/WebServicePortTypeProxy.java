package com.aisino.kp;

public class WebServicePortTypeProxy implements com.aisino.kp.WebServicePortType {
  private String _endpoint = null;
  private com.aisino.kp.WebServicePortType webServicePortType = null;
  
  public WebServicePortTypeProxy() {
    _initWebServicePortTypeProxy();
  }
  
  public WebServicePortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initWebServicePortTypeProxy();
  }
  
  private void _initWebServicePortTypeProxy() {
    try {
      webServicePortType = (new com.aisino.kp.WebServiceLocator()).getWebServiceHttpPort();
      if (webServicePortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)webServicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)webServicePortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (webServicePortType != null)
      ((javax.xml.rpc.Stub)webServicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.aisino.kp.WebServicePortType getWebServicePortType() {
    if (webServicePortType == null)
      _initWebServicePortTypeProxy();
    return webServicePortType;
  }
  
  public java.lang.String uploadRedInfo(java.lang.String in0) throws java.rmi.RemoteException{
    if (webServicePortType == null)
      _initWebServicePortTypeProxy();
    return webServicePortType.uploadRedInfo(in0);
  }
  
  public java.lang.String invalidInvoice(java.lang.String in0) throws java.rmi.RemoteException{
    if (webServicePortType == null)
      _initWebServicePortTypeProxy();
    return webServicePortType.invalidInvoice(in0);
  }
  
  public java.lang.String distributeInvoice(java.lang.String in0) throws java.rmi.RemoteException{
    if (webServicePortType == null)
      _initWebServicePortTypeProxy();
    return webServicePortType.distributeInvoice(in0);
  }
  
  public java.lang.String downloadRedInfo(java.lang.String in0) throws java.rmi.RemoteException{
    if (webServicePortType == null)
      _initWebServicePortTypeProxy();
    return webServicePortType.downloadRedInfo(in0);
  }
  
  public java.lang.String uploadInvoice(java.lang.String in0) throws java.rmi.RemoteException{
    if (webServicePortType == null)
      _initWebServicePortTypeProxy();
    return webServicePortType.uploadInvoice(in0);
  }
  
  public java.lang.String printInvoice(java.lang.String in0) throws java.rmi.RemoteException{
    if (webServicePortType == null)
      _initWebServicePortTypeProxy();
    return webServicePortType.printInvoice(in0);
  }
  
  public java.lang.String returnInvoice(java.lang.String in0) throws java.rmi.RemoteException{
    if (webServicePortType == null)
      _initWebServicePortTypeProxy();
    return webServicePortType.returnInvoice(in0);
  }
  
  public java.lang.String printList(java.lang.String in0) throws java.rmi.RemoteException{
    if (webServicePortType == null)
      _initWebServicePortTypeProxy();
    return webServicePortType.printList(in0);
  }
  
  public java.lang.String copyTax(java.lang.String in0) throws java.rmi.RemoteException{
    if (webServicePortType == null)
      _initWebServicePortTypeProxy();
    return webServicePortType.copyTax(in0);
  }
  
  public java.lang.String queryInventory(java.lang.String in0) throws java.rmi.RemoteException{
    if (webServicePortType == null)
      _initWebServicePortTypeProxy();
    return webServicePortType.queryInventory(in0);
  }
  
  public java.lang.String queryJSPInfo(java.lang.String in0) throws java.rmi.RemoteException{
    if (webServicePortType == null)
      _initWebServicePortTypeProxy();
    return webServicePortType.queryJSPInfo(in0);
  }
  
  public java.lang.String wasteInvoice(java.lang.String in0) throws java.rmi.RemoteException{
    if (webServicePortType == null)
      _initWebServicePortTypeProxy();
    return webServicePortType.wasteInvoice(in0);
  }
  
  public java.lang.String trackSource(java.lang.String in0) throws java.rmi.RemoteException{
    if (webServicePortType == null)
      _initWebServicePortTypeProxy();
    return webServicePortType.trackSource(in0);
  }
  
  public java.lang.String clearCard(java.lang.String in0) throws java.rmi.RemoteException{
    if (webServicePortType == null)
      _initWebServicePortTypeProxy();
    return webServicePortType.clearCard(in0);
  }
  
  public java.lang.String queryInvoice(java.lang.String in0) throws java.rmi.RemoteException{
    if (webServicePortType == null)
      _initWebServicePortTypeProxy();
    return webServicePortType.queryInvoice(in0);
  }
  
  public java.lang.String invalidBlankInvoice(java.lang.String in0) throws java.rmi.RemoteException{
    if (webServicePortType == null)
      _initWebServicePortTypeProxy();
    return webServicePortType.invalidBlankInvoice(in0);
  }
  
  public java.lang.String issueInvoice(java.lang.String in0) throws java.rmi.RemoteException{
    if (webServicePortType == null)
      _initWebServicePortTypeProxy();
    return webServicePortType.issueInvoice(in0);
  }
  
  public java.lang.String printParams(java.lang.String in0) throws java.rmi.RemoteException{
    if (webServicePortType == null)
      _initWebServicePortTypeProxy();
    return webServicePortType.printParams(in0);
  }
  
  public java.lang.String queryStock(java.lang.String in0) throws java.rmi.RemoteException{
    if (webServicePortType == null)
      _initWebServicePortTypeProxy();
    return webServicePortType.queryStock(in0);
  }
  
  public java.lang.String updateInvoiceStatus(java.lang.String in0) throws java.rmi.RemoteException{
    if (webServicePortType == null)
      _initWebServicePortTypeProxy();
    return webServicePortType.updateInvoiceStatus(in0);
  }
  
  
}