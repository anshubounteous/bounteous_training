# ☁️ Weather Forecast Comparison App (Spring Boot)

This is a **Spring Boot application** that compares the 7-day weather forecasts of two cities using the **Open-Meteo API**. It fetches temperature, humidity, wind speed, and optionally precipitation. The comparison helps in identifying the differences in forecasts between the two locations.

---

## 📦 Project Structure

```
compare_weather/
├── controller/
│   └── WeatherController.java
├── dto/
│   ├── WeatherComparisonDTO.java
│   ├── WeatherComparisonResultDTO.java
│   └── WeatherForecastDTO.java
├── entity/
│   └── WeatherForecast.java
├── service/
│   └── WeatherService.java
└── resources/
    └── application.properties
```

---

## 🧠 System Design and Logic

### 🔹 Architecture Flow

```
+----------------------+
|   User Input (API)   |
+----------------------+
            |
            v
+------------------------+
|  WeatherController     |
+------------------------+
            |
            v
+------------------------+
|   WeatherService       |
+------------------------+
            |
            v
+------------------------+
| WebClient (Open-Meteo) |
+------------------------+
            |
            v
+------------------------+
|  JSON Parsed -> DTOs   |
+------------------------+
```

### 📌 Key Components

* **WeatherForecast**: Entity class containing `date`, `temperature`, `humidity`, and `windSpeed`.
* **WeatherService**:

  * Fetches forecast using coordinates from Open-Meteo.
  * Compares forecasts of two cities.
* **WeatherController**:

  * `/weather?city=CityName` — returns forecast for one city.
  * `/compare?city1=A&city2=B` — returns comparison.
* **WebClient**:

  * Fetches data using Open-Meteo's URL with city coordinates from a preloaded Excel file or config.

---

## 🔗 Open-Meteo API Usage

**Base URL:**

```
https://api.open-meteo.com/v1/forecast
```

**Sample Call:**

```
https://api.open-meteo.com/v1/forecast?latitude=28.6139&longitude=77.2090&daily=temperature_2m_max,temperature_2m_min,precipitation_sum,wind_speed_10m_max&timezone=auto
```

* Replaces `{latitude}` and `{longitude}` dynamically
* Parses daily arrays to map into `WeatherForecastDTO`

---

## 🚀 How to Run the App

### ✅ Prerequisites

* Java 17+
* Maven 3.6+
* Internet connection (to access API)

### ▶️ Steps

1. **Clone the Project:**

```bash
git clone <repo-url>
cd compare_weather
```

2. **Run using Maven:**

```bash
./mvnw spring-boot:run
```

3. **Use API Endpoints:**

* `GET /weather?city=Delhi`
* `GET /compare?city1=Delhi&city2=Mumbai`

---

## 📊 Sample Comparison Output

```json
{
  "city1": "Delhi",
  "city2": "Mumbai",
  "comparisons": [
    {
      "date": "2024-06-10",
      "temperatureDifference": 3.5,
      "humidityDifference": 12.0,
      "windSpeedDifference": 4.1
    }
  ]
}
```

---

## 🔍 Bonus Features Implemented

* 🌧️ Precipitation comparison
* 🌬️ Wind speed added to DTO and analysis
* 🛡️ City name validation and error handling

---

## 📚 Technologies Used

| Technology       | Usage                       |
| ---------------- | --------------------------- |
| Spring Boot      | Project Framework           |
| WebClient        | REST API calling (Reactive) |
| Open-Meteo API   | Forecast Data               |
| Java DTO Pattern | Structured Data Transfer    |
| Excel (Optional) | Coordinate mapping          |


---
