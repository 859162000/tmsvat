package sajt.webservice.ws.service;

public class SajtIssueInvoiceServicePortTypeProxy implements sajt.webservice.ws.service.SajtIssueInvoiceServicePortType {
  private String _endpoint = null;
  private sajt.webservice.ws.service.SajtIssueInvoiceServicePortType sajtIssueInvoiceServicePortType = null;
  
  public SajtIssueInvoiceServicePortTypeProxy() {
    _initSajtIssueInvoiceServicePortTypeProxy();
  }
  
  public SajtIssueInvoiceServicePortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initSajtIssueInvoiceServicePortTypeProxy();
  }
  
  private void _initSajtIssueInvoiceServicePortTypeProxy() {
    try {
      sajtIssueInvoiceServicePortType = (new sajt.webservice.ws.service.SajtIssueInvoiceServiceLocator()).getSajtIssueInvoiceServiceHttpSoap11Endpoint();
      if (sajtIssueInvoiceServicePortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sajtIssueInvoiceServicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sajtIssueInvoiceServicePortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sajtIssueInvoiceServicePortType != null)
      ((javax.xml.rpc.Stub)sajtIssueInvoiceServicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public sajt.webservice.ws.service.SajtIssueInvoiceServicePortType getSajtIssueInvoiceServicePortType() {
    if (sajtIssueInvoiceServicePortType == null)
      _initSajtIssueInvoiceServicePortTypeProxy();
    return sajtIssueInvoiceServicePortType;
  }
  
  public sajt.webservice.ws.service.xsd.SajtIssueInvoiceResponse saveDocument(java.lang.String command) throws java.rmi.RemoteException{
    if (sajtIssueInvoiceServicePortType == null)
      _initSajtIssueInvoiceServicePortTypeProxy();
    return sajtIssueInvoiceServicePortType.saveDocument(command);
  }
  
  
}