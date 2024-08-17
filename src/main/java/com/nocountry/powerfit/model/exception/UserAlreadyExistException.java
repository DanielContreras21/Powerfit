package com.nocountry.powerfit.model.exception;

public class UserAlreadyExistException  extends RuntimeException{
    public UserAlreadyExistException(String message){
        super(message);
    }
}
