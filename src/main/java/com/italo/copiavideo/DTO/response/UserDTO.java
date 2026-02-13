package com.italo.copiavideo.DTO.response;

import com.italo.copiavideo.model.enums.RoleUser;

import java.time.LocalDate;
import java.util.UUID;

public record UserDTO(
        UUID id,
        String name,
        String email,
        RoleUser role,
        long quantityIdeas,
        LocalDate created_at
) {
}
