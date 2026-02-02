package com.italo.copiavideo.DTO.youtube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
@JsonIgnoreProperties
public record ThumbnailsDTO(
        @JsonProperty("default")
        HashMap<String, String> defaultThumb,
        @JsonProperty("high")
        HashMap<String, String> highThumb,
        @JsonProperty("medium")
        HashMap<String, String> mediumtThumb
) {
}
