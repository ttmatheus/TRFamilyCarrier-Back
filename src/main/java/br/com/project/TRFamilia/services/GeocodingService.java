package br.com.project.TRFamilia.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Service
public class GeocodingService {

    @Value("${google.maps.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public Location getCoordinates(String address) {
        String uri = "https://maps.googleapis.com/maps/api/geocode/json?address=" + 
			address.replaceAll(" ", "+") +
			"&key=" + apiKey;
        GoogleGeocodeResponse response = restTemplate.getForObject(uri, GoogleGeocodeResponse.class);

        if (response != null &&
            response.results != null &&
            !response.results.isEmpty()) {
            return response.results.get(0).geometry.location;
        }

        throw new RuntimeException("Endereço não encontrado: " + address);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GoogleGeocodeResponse {
        public java.util.List<Result> results;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result {
        public Geometry geometry;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Geometry {
        public Location location;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Location {
        @JsonProperty("lat")
        public double lat;

        @JsonProperty("lng")
        public double lng;
    }
}
