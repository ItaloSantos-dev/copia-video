package com.italo.copiavideo.DTO.response;



import java.util.Map;
import java.util.UUID;

public record IdeaSimplifiedDTO(
        UUID id,
        String title,
        String video_id,
        String annotations,
        Map<String, Object> drawn
) {
}
