package com.example.compare_weather.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherForecast {
    private LocalDate date;
    private Double temperatureMax;
    private Double temperatureMin;
    private Double precipitationSum;
}
