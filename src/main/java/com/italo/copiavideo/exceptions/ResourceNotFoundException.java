package com.italo.copiavideo.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("Item não encontrado");
    }

    public ResourceNotFoundException(String item, String id) {
        super(item + " de id " + id + " não encontrado");
    }

}
