package com.italo.copiavideo.DTO.youtube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Instant;
@JsonIgnoreProperties
public record SnippetDTO(
        String channelId,
        String channelTitle,
        ThumbnailsDTO thumbnails,
        String title,
        Instant publishTime
) {
}
