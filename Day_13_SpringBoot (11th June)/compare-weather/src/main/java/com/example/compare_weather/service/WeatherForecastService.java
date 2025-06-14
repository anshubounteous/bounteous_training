package com.example.compare_weather.service;

import com.example.compare_weather.dto.WeatherComparisonDTO;
import com.example.compare_weather.dto.WeatherComparisonResultDTO;
import com.example.compare_weather.dto.WeatherForecastDTO;
import com.example.compare_weather.entity.WeatherForecast;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class WeatherForecastService {

    private final WebClient webClient = WebClient.builder().baseUrl("https://api.open-meteo.com").build();

    public WeatherComparisonDTO compairWeather(WeatherForecastDTO dto) {
        List<WeatherForecast> forecasts1 = fetchForecast(dto.getLatitude1(), dto.getLongitude1()).block();
        List<WeatherForecast> forecasts2 = fetchForecast(dto.getLatitude2(), dto.getLongitude2()).block();

        List<WeatherComparisonResultDTO> comparisonList = new ArrayList<>();

        for (int i = 0; i < forecasts1.size(); i++) {
            WeatherForecast f1 = forecasts1.get(i);
            WeatherForecast f2 = forecasts2.get(i);

            String hotterCity = f1.getTemperatureMax() > f2.getTemperatureMax() ? dto.getCity1()
                    : f1.getTemperatureMax() < f2.getTemperatureMax() ? dto.getCity2() : "Equal";

            String wetterCity = f1.getPrecipitationSum() > f2.getPrecipitationSum() ? dto.getCity1()
                    : f1.getPrecipitationSum() < f2.getPrecipitationSum() ? dto.getCity2() : "Equal";

            WeatherComparisonResultDTO result = new WeatherComparisonResultDTO(
                    f1.getDate(),
                    f1.getTemperatureMax(),
                    f2.getTemperatureMax(),
                    hotterCity,
                    f1.getPrecipitationSum(),
                    f2.getPrecipitationSum(),
                    wetterCity
            );

            comparisonList.add(result);
        }

        return new WeatherComparisonDTO(dto.getCity1(), dto.getCity2(), comparisonList);
    }

    public Mono<List<WeatherForecast>> fetchForecast(double latitude, double longitude) {
        String uri = String.format(
                "/v1/forecast?latitude=%f&longitude=%f&daily=temperature_2m_max,temperature_2m_min,precipitation_sum&timezone=auto",
                latitude, longitude);

        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    Map<String, List<?>> daily = (Map<String, List<?>>) response.get("daily");
                    List<String> dates = (List<String>) daily.get("time");
                    List<Double> tempMax = (List<Double>) daily.get("temperature_2m_max");
                    List<Double> tempMin = (List<Double>) daily.get("temperature_2m_min");
                    List<Double> precipitation = (List<Double>) daily.get("precipitation_sum");

                    List<WeatherForecast> forecasts = new ArrayList<>();
                    for (int i = 0; i < dates.size(); i++) {
                        forecasts.add(new WeatherForecast(
                                LocalDate.parse(dates.get(i)),
                                tempMax.get(i),
                                tempMin.get(i),
                                precipitation.get(i)
                        ));
                    }
                    return forecasts;
                });
    }
}
