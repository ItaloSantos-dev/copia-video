package com.italo.copiavideo.DTO.request;

import java.util.UUID;

public record CreateIdeaDTO(
         String title,
         String annotations,
         String video_id
) {
}
