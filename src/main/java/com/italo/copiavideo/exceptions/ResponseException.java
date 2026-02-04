package com.italo.copiavideo.exceptions;

import org.springframework.http.HttpStatus;

public record ResponseException(
        HttpStatus status,
        String menssage
) {
}
