

/**
 * SajtIssueInvoiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.1  Built on : Feb 20, 2016 (10:01:29 GMT)
 */

    package sajt.webservice.ws.service;

    /*
     *  SajtIssueInvoiceService java interface
     */

    public interface SajtIssueInvoiceService {
          

        /**
          * Auto generated method signature
          * 
                    * @param saveDocument0
                
         */

         
                     public sajt.webservice.ws.service.SaveDocumentResponse saveDocument(

                        sajt.webservice.ws.service.SaveDocument saveDocument0)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param saveDocument0
            
          */
        public void startsaveDocument(

            sajt.webservice.ws.service.SaveDocument saveDocument0,

            final sajt.webservice.ws.service.SajtIssueInvoiceServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    