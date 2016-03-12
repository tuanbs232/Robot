
/**
 * RequestFailedExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.1  Built on : Feb 20, 2016 (10:01:29 GMT)
 */

package org.signserver.clientws;

public class RequestFailedExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1457573999582L;
    
    private org.signserver.clientws.ClientWSServiceStub.RequestFailedExceptionE faultMessage;

    
        public RequestFailedExceptionException() {
            super("RequestFailedExceptionException");
        }

        public RequestFailedExceptionException(java.lang.String s) {
           super(s);
        }

        public RequestFailedExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public RequestFailedExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(org.signserver.clientws.ClientWSServiceStub.RequestFailedExceptionE msg){
       faultMessage = msg;
    }
    
    public org.signserver.clientws.ClientWSServiceStub.RequestFailedExceptionE getFaultMessage(){
       return faultMessage;
    }
}
    