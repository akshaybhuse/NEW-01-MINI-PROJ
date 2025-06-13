package com.akshay.exception;

public class PlanNotFoundException extends RuntimeException{
    public PlanNotFoundException(String msg){
        super(msg);
    }

}
