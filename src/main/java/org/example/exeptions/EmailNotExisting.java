package org.example.exeptions;

public class EmailNotExisting extends RuntimeException{

    public EmailNotExisting(String sendingMessage){
        super(sendingMessage);
    }


}
