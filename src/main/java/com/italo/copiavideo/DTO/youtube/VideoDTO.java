package com.italo.copiavideo.DTO.youtube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
@JsonIgnoreProperties
public record VideoDTO (
        HashMap<String, String> id,
        SnippetDTO snippet
){

}
