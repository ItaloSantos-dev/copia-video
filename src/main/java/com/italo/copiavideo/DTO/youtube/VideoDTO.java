package com.italo.copiavideo.DTO.youtube;

public record VideoDTO(
        String id,
        SnippetDTO snippet,
        StatisticsDTO statistics

) {
}
