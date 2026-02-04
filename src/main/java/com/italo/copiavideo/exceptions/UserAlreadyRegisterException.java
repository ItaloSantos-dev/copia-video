package com.italo.copiavideo.exceptions;

public class UserAlreadyRegisterException extends RuntimeException {

    public UserAlreadyRegisterException() {
        super("Este usuário já está cadastrado");
    }
    public UserAlreadyRegisterException(String email){
        super("Usuario de email " + email + " já cadastrado");
    }
}
