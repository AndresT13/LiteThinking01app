package com.microservices.one.exception;

public class ClientAlreadyExistsException extends RuntimeException{

        public ClientAlreadyExistsException(String message){
            super(message);
        }
}
