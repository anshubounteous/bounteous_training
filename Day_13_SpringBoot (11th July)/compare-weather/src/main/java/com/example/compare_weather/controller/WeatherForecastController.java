package com.example.compare_weather.controller;

import com.example.compare_weather.dto.WeatherForecastDTO;
import com.example.compare_weather.dto.WeatherComparisonDTO;
import com.example.compare_weather.service.WeatherForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compare-weather")
public class WeatherForecastController {

    private WeatherForecastService weatherForecastService;

    @Autowired
    public void setWeatherForecastService(WeatherForecastService weatherForecastService) {
        this.weatherForecastService = weatherForecastService;
    }

    @PostMapping
    public ResponseEntity<WeatherComparisonDTO> compareWeather(@RequestBody WeatherForecastDTO weatherForecastDTO) {
        return ResponseEntity.ok(weatherForecastService.compairWeather(weatherForecastDTO));
    }
}
