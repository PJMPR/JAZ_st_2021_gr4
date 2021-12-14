package com.example.demo.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MoviePagesDto {
    @JsonProperty("page")
    int page;
    @JsonProperty("results")
    List<MovieDto> results;
    @JsonProperty("total_pages")
    int total_pages;
    @JsonProperty("total_results")
    int total_results;

}
