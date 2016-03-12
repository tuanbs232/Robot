
/**
 * InternalServerExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.1  Built on : Feb 20, 2016 (10:01:29 GMT)
 */

package org.signserver.clientws;

public class InternalServerExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1457573999554L;
    
    private org.signserver.clientws.ClientWSServiceStub.InternalServerExceptionE faultMessage;

    
        public InternalServerExceptionException() {
            super("InternalServerExceptionException");
        }

        public InternalServerExceptionException(java.lang.String s) {
           super(s);
        }

        public InternalServerExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public InternalServerExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(org.signserver.clientws.ClientWSServiceStub.InternalServerExceptionE msg){
       faultMessage = msg;
    }
    
    public org.signserver.clientws.ClientWSServiceStub.InternalServerExceptionE getFaultMessage(){
       return faultMessage;
    }
}
    