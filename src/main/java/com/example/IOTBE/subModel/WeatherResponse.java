package com.example.IOTBE.subModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherResponse {

    private Location location;
    private Current current;

    // Constructors, getters và setters

    public static class Location {
        private String name;
        private String region;
        private String country;
        private double lat;
        private double lon;
        @JsonProperty("tz_id")
        private String timezoneId;
        @JsonProperty("localtime_epoch")
        private long localtimeEpoch;
        private String localtime;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getRegion() {
			return region;
		}
		public void setRegion(String region) {
			this.region = region;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public double getLat() {
			return lat;
		}
		public void setLat(double lat) {
			this.lat = lat;
		}
		public double getLon() {
			return lon;
		}
		public void setLon(double lon) {
			this.lon = lon;
		}
		public String getTimezoneId() {
			return timezoneId;
		}
		public void setTimezoneId(String timezoneId) {
			this.timezoneId = timezoneId;
		}
		public long getLocaltimeEpoch() {
			return localtimeEpoch;
		}
		public void setLocaltimeEpoch(long localtimeEpoch) {
			this.localtimeEpoch = localtimeEpoch;
		}
		public String getLocaltime() {
			return localtime;
		}
		public void setLocaltime(String localtime) {
			this.localtime = localtime;
		}

        // Constructors, getters và setters
        
    }

    public static class Current {
        @JsonProperty("last_updated_epoch")
        private long lastUpdatedEpoch;
        @JsonProperty("last_updated")
        private String lastUpdated;
        @JsonProperty("temp_c")
        private double temperatureCelsius;
        @JsonProperty("temp_f")
        private double temperatureFahrenheit;
        @JsonProperty("is_day")
        private int isDay;
        private Condition condition;
        @JsonProperty("wind_mph")
        private double windMph;
        @JsonProperty("wind_kph")
        private double windKph;
        @JsonProperty("wind_degree")
        private int windDegree;
        @JsonProperty("wind_dir")
        private String windDirection;
        @JsonProperty("pressure_mb")
        private double pressureMb;
        @JsonProperty("pressure_in")
        private double pressureIn;
        @JsonProperty("precip_mm")
        private double precipitationMm;
        @JsonProperty("precip_in")
        private double precipitationIn;
        private int humidity;
        private int cloud;
        @JsonProperty("feelslike_c")
        private double feelsLikeCelsius;
        @JsonProperty("feelslike_f")
        private double feelsLikeFahrenheit;
        @JsonProperty("vis_km")
        private double visibilityKm;
        @JsonProperty("vis_miles")
        private double visibilityMiles;
        private double uv;
        @JsonProperty("gust_mph")
        private double windGustMph;
        @JsonProperty("gust_kph")
        private double windGustKph;
		public long getLastUpdatedEpoch() {
			return lastUpdatedEpoch;
		}
		public void setLastUpdatedEpoch(long lastUpdatedEpoch) {
			this.lastUpdatedEpoch = lastUpdatedEpoch;
		}
		public String getLastUpdated() {
			return lastUpdated;
		}
		public void setLastUpdated(String lastUpdated) {
			this.lastUpdated = lastUpdated;
		}
		public double getTemperatureCelsius() {
			return temperatureCelsius;
		}
		public void setTemperatureCelsius(double temperatureCelsius) {
			this.temperatureCelsius = temperatureCelsius;
		}
		public double getTemperatureFahrenheit() {
			return temperatureFahrenheit;
		}
		public void setTemperatureFahrenheit(double temperatureFahrenheit) {
			this.temperatureFahrenheit = temperatureFahrenheit;
		}
		public int getIsDay() {
			return isDay;
		}
		public void setIsDay(int isDay) {
			this.isDay = isDay;
		}
		public Condition getCondition() {
			return condition;
		}
		public void setCondition(Condition condition) {
			this.condition = condition;
		}
		public double getWindMph() {
			return windMph;
		}
		public void setWindMph(double windMph) {
			this.windMph = windMph;
		}
		public double getWindKph() {
			return windKph;
		}
		public void setWindKph(double windKph) {
			this.windKph = windKph;
		}
		public int getWindDegree() {
			return windDegree;
		}
		public void setWindDegree(int windDegree) {
			this.windDegree = windDegree;
		}
		public String getWindDirection() {
			return windDirection;
		}
		public void setWindDirection(String windDirection) {
			this.windDirection = windDirection;
		}
		public double getPressureMb() {
			return pressureMb;
		}
		public void setPressureMb(double pressureMb) {
			this.pressureMb = pressureMb;
		}
		public double getPressureIn() {
			return pressureIn;
		}
		public void setPressureIn(double pressureIn) {
			this.pressureIn = pressureIn;
		}
		public double getPrecipitationMm() {
			return precipitationMm;
		}
		public void setPrecipitationMm(double precipitationMm) {
			this.precipitationMm = precipitationMm;
		}
		public double getPrecipitationIn() {
			return precipitationIn;
		}
		public void setPrecipitationIn(double precipitationIn) {
			this.precipitationIn = precipitationIn;
		}
		public int getHumidity() {
			return humidity;
		}
		public void setHumidity(int humidity) {
			this.humidity = humidity;
		}
		public int getCloud() {
			return cloud;
		}
		public void setCloud(int cloud) {
			this.cloud = cloud;
		}
		public double getFeelsLikeCelsius() {
			return feelsLikeCelsius;
		}
		public void setFeelsLikeCelsius(double feelsLikeCelsius) {
			this.feelsLikeCelsius = feelsLikeCelsius;
		}
		public double getFeelsLikeFahrenheit() {
			return feelsLikeFahrenheit;
		}
		public void setFeelsLikeFahrenheit(double feelsLikeFahrenheit) {
			this.feelsLikeFahrenheit = feelsLikeFahrenheit;
		}
		public double getVisibilityKm() {
			return visibilityKm;
		}
		public void setVisibilityKm(double visibilityKm) {
			this.visibilityKm = visibilityKm;
		}
		public double getVisibilityMiles() {
			return visibilityMiles;
		}
		public void setVisibilityMiles(double visibilityMiles) {
			this.visibilityMiles = visibilityMiles;
		}
		public double getUv() {
			return uv;
		}
		public void setUv(double uv) {
			this.uv = uv;
		}
		public double getWindGustMph() {
			return windGustMph;
		}
		public void setWindGustMph(double windGustMph) {
			this.windGustMph = windGustMph;
		}
		public double getWindGustKph() {
			return windGustKph;
		}
		public void setWindGustKph(double windGustKph) {
			this.windGustKph = windGustKph;
		}

        // Constructors, getters và setters
        
        
    }

    public static class Condition {
        private String text;
        private String icon;
        private int code;

        // Constructors, getters và setters
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getIcon() {
			return icon;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
		public int getCode() {
			return code;
		}
		public void setCode(int code) {
			this.code = code;
		}
    }

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Current getCurrent() {
		return current;
	}

	public void setCurrent(Current current) {
		this.current = current;
	}

    // Constructors, getters và setters
    
}
