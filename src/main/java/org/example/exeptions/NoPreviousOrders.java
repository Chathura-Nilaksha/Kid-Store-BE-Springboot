package org.example.exeptions;

public class NoPreviousOrders extends RuntimeException{
    public NoPreviousOrders(String sendingMessage){
        super(sendingMessage);
    }


}
