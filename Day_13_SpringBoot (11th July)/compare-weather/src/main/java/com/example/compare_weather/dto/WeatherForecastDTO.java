package com.example.compare_weather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherForecastDTO {
    private String city1;
    private double latitude1;
    private double longitude1;

    private String city2;
    private double latitude2;
    private double longitude2;
}
