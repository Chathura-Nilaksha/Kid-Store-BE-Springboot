package org.example.exeptions;

public class CustomerAndOrderDataRawNotExistingInDBTable extends RuntimeException{
    public CustomerAndOrderDataRawNotExistingInDBTable(String sendingMessage){
        super(sendingMessage);
    }

}
