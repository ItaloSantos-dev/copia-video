package com.italo.copiavideo.DTO.youtube;

public record StatisticsDTO(
        int commentCount,
        int favoriteCount,
        int likeCount,
        int viewCount
) {
}
