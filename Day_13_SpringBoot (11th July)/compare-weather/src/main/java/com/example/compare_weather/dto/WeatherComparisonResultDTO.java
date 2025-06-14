package com.example.compare_weather.dto;

import com.example.compare_weather.entity.WeatherForecast;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherComparisonResultDTO {
    private LocalDate date;
    private Double city1TempMax;
    private Double city2TempMax;
    private String hotterCity;

    private Double city1Precipitation;
    private Double city2Precipitation;
    private String wetterCity;
}
