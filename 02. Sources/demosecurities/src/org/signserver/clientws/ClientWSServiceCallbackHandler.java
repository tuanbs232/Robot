
/**
 * ClientWSServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.1  Built on : Feb 20, 2016 (10:01:29 GMT)
 */

    package org.signserver.clientws;

    /**
     *  ClientWSServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class ClientWSServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public ClientWSServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public ClientWSServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for processData method
            * override this method for handling normal response from processData operation
            */
           public void receiveResultprocessData(
                    org.signserver.clientws.ClientWSServiceStub.ProcessDataResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from processData operation
           */
            public void receiveErrorprocessData(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for verify method
            * override this method for handling normal response from verify operation
            */
           public void receiveResultverify(
                    org.signserver.clientws.ClientWSServiceStub.VerifyResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from verify operation
           */
            public void receiveErrorverify(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for sign method
            * override this method for handling normal response from sign operation
            */
           public void receiveResultsign(
                    org.signserver.clientws.ClientWSServiceStub.SignResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from sign operation
           */
            public void receiveErrorsign(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for processSOD method
            * override this method for handling normal response from processSOD operation
            */
           public void receiveResultprocessSOD(
                    org.signserver.clientws.ClientWSServiceStub.ProcessSODResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from processSOD operation
           */
            public void receiveErrorprocessSOD(java.lang.Exception e) {
            }
                


    }
    