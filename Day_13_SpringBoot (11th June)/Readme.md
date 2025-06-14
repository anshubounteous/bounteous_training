# ☁️ Weather Forecast Comparison App (Spring Boot)

This is a **Spring Boot application** that compares the 7-day weather forecasts of two cities using the **Open-Meteo API**. It fetches temperature, humidity, wind speed, and optionally precipitation. The comparison helps in identifying the differences in forecasts between the two locations.

---
### 🧠 System Design
#### 🔹 Architecture Flow

![ddd](https://github.com/user-attachments/assets/fc7b804d-afc5-4006-9bce-8f61f093e23a)

---

## 📊 Sample Comparison API Call

![Screenshot 2025-06-14 235709](https://github.com/user-attachments/assets/f0cf6a20-f2ed-489a-8e7b-23f69da903f9)


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

### 📌 Key Components

* **WeatherForecast**: Entity class containing `date`, `temperature`, `humidity`, and `windSpeed`.
* **WeatherService**:

  * Fetches forecast using coordinates from Open-Meteo.
  * Compares forecasts of two cities.
* **WeatherController**:

  * `/compare-weather` — returns comparison.
    
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
* Internet connection (to access API)

### ▶️ Steps

1. **Clone the Project:**

```bash
git clone <repo-url>
cd compare_weather
```

2. **Use API Endpoints:**

* `GET /weather?city=Delhi`
* `GET /compare?city1=Delhi&city2=Mumbai`

---

## 📊 Sample Comparison Input

```json
{
  "city1": "Delhi",
  "latitude1": 28.6139,
  "longitude1": 77.2090,
  "city2": "Mumbai",
  "latitude2": 19.0760,
  "longitude2": 72.8777
}

```

## 📊 Sample Comparison Output

```json
{
    "city1": "Delhi",
    "city2": "Mumbai",
    "comparisonResults": [
        {
            "date": "2025-06-14",
            "city1TempMax": 40.0,
            "city2TempMax": 29.9,
            "hotterCity": "Delhi",
            "city1Precipitation": 0.1,
            "city2Precipitation": 71.9,
            "wetterCity": "Mumbai"
        },
        {
            "date": "2025-06-15",
            "city1TempMax": 36.7,
            "city2TempMax": 28.7,
            "hotterCity": "Delhi",
            "city1Precipitation": 3.1,
            "city2Precipitation": 66.3,
            "wetterCity": "Mumbai"
        },
        {
            "date": "2025-06-16",
            "city1TempMax": 29.2,
            "city2TempMax": 28.6,
            "hotterCity": "Delhi",
            "city1Precipitation": 25.5,
            "city2Precipitation": 46.7,
            "wetterCity": "Mumbai"
        },
        {
            "date": "2025-06-17",
            "city1TempMax": 31.3,
            "city2TempMax": 28.4,
            "hotterCity": "Delhi",
            "city1Precipitation": 18.8,
            "city2Precipitation": 41.3,
            "wetterCity": "Mumbai"
        },
        {
            "date": "2025-06-18",
            "city1TempMax": 29.3,
            "city2TempMax": 28.5,
            "hotterCity": "Delhi",
            "city1Precipitation": 18.0,
            "city2Precipitation": 32.7,
            "wetterCity": "Mumbai"
        },
        {
            "date": "2025-06-19",
            "city1TempMax": 30.2,
            "city2TempMax": 28.7,
            "hotterCity": "Delhi",
            "city1Precipitation": 14.4,
            "city2Precipitation": 56.1,
            "wetterCity": "Mumbai"
        },
        {
            "date": "2025-06-20",
            "city1TempMax": 33.7,
            "city2TempMax": 28.4,
            "hotterCity": "Delhi",
            "city1Precipitation": 3.0,
            "city2Precipitation": 58.2,
            "wetterCity": "Mumbai"
        }
    ]
}

```

---

## 🔍 Bonus Features Implemented

* 🌧️ Precipitation comparison
* 🌬️ Wind speed added to DTO and analysis

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
