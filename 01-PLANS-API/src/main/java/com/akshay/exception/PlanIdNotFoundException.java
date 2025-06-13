package com.akshay.exception;

public class PlanIdNotFoundException extends RuntimeException{

    public PlanIdNotFoundException(String msg){
        super(msg);
    }
}
