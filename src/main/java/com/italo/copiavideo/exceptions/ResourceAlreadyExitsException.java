package com.italo.copiavideo.exceptions;

public class ResourceAlreadyExitsException extends RuntimeException {
    public ResourceAlreadyExitsException() {
        super("Este item ja existe");
    }

    public ResourceAlreadyExitsException(String item, String property) {
        super("Já existe um(a) " + item + " com este(a) " + property);
    }
}
