package com.italo.copiavideo.DTO.response;

import java.util.UUID;

public record IdeaDTO(
        UUID id,
        String title,
        String video_id,
        String annotations,
        String userName
) {
}
