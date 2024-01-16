package com.lcwd.user.service.exception;




public class ResourceNotFoundException extends  RuntimeException{

    public ResourceNotFoundException(){
        super("Resource Not found on serve !!");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}
