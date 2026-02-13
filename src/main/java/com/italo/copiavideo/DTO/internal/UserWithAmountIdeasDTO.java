package com.italo.copiavideo.DTO.internal;

import com.italo.copiavideo.model.User;

public record UserWithAmountIdeasDTO(
        User user,
        Long totalIdeas
) {
}
