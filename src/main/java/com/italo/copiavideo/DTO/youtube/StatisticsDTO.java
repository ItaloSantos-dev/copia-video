package com.italo.copiavideo.DTO.youtube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record StatisticsDTO(
        int commentCount,
        int favoriteCount,
        Integer likeCount,
        int viewCount
) {
}
