package com.nocountry.powerfit.model.exception;

public class EntityAlreadyExistException extends RuntimeException{
    public EntityAlreadyExistException(String message){
        super(message);
    }
}
