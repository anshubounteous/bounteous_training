package com.example.compare_weather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherComparisonDTO {
    private String city1;
    private String city2;
    private List<WeatherComparisonResultDTO> comparisonResults;
}
