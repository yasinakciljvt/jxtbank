package com.jxt.jxtbank.exceptions;

public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(String error){
        super(error);
    }

}
