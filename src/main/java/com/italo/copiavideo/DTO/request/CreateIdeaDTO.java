package com.italo.copiavideo.DTO.request;

import java.util.UUID;

public record CreateIdeaDTO(
         String title,
         String annotations,
         String link_video,
         UUID user_id
) {
}
