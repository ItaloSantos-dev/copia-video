package com.italo.copiavideo.DTO.request;

public record RegisterUserDTO(
        String name,
        String email,
        String password
) {
}
