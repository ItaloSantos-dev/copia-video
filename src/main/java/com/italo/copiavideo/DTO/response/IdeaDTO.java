package com.italo.copiavideo.DTO.response;

import java.util.UUID;

public record IdeaDTO(
        UUID id,
        String title,
        String link_video,
        String annotations,
        String userName
) {
}
