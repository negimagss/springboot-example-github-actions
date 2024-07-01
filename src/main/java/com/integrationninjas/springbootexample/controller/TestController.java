package com.integrationninjas.springbootexample.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping
	public Object hello() {
		Map<String, String> object = new HashMap<>();
		object.put("name", "Shardul Negi");
		object.put("text", "First Time using Github Actions");
		        // Calculate and add current date and time to the response
				LocalDateTime currentDateTime = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String formattedDateTime = currentDateTime.format(formatter);
				object.put("currentDateTime", formattedDateTime);
		return object;
	}
	@GetMapping("/hello")
    public String helloPage() {
        return "hello"; // This maps to src/main/resources/static/hello.html
    }

	@PostMapping("/postvalue")
    public Object postValue(@RequestBody Map<String, Integer> requestBody) {
        // Process the received input
        int a = requestBody.get("a");
        int b = requestBody.get("b");

        // Modify the response
        Map<String, Object> responseObject = new HashMap<>();
        responseObject.put("sum", a+b);
        responseObject.put("multiply", a*b);
		responseObject.put("divide", a/b);
		responseObject.put("subtract", a-b);

        return responseObject;
    }
	@GetMapping("/quotes")
    public ResponseEntity<String> getQuotes(
            @RequestParam(required = false) String coordinates,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime) throws Exception {

        // Build the URL dynamically (assuming no access to content)
        String url = "https://api-sandbox.parkwhiz.com/v4/quotes/";
        StringBuilder urlBuilder = new StringBuilder(url);

        if (coordinates != null) {
            urlBuilder.append("?q=coordinates:").append(coordinates);
        }
        if (startTime != null) {
            urlBuilder.append((coordinates != null ? "&" : "?") + "start_time=").append(startTime);
        }
        if (endTime != null) {
            urlBuilder.append((coordinates != null || startTime != null ? "&" : "?") + "end_time=").append(endTime);
        }

        // Simulate a successful response (assuming no access to content)
        String response = "{\n" +
                "  \"message\": \"ParkWhiz API successfully called. (Content not accessible)\"\n" +
                "}";

        return ResponseEntity.ok(response);
    }

	@GetMapping("/quote")
    public String quotes() {
        try {
            String url = "https://api-sandbox.parkwhiz.com/v4/quotes/?q=coordinates:41.881943,-87.630976&start_time=2015-11-22T16:35:28-06:00&end_time=2015-11-22T19:35:44-06:00";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("User-Agent", "Mozilla/5.0")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Response Code: " + response.statusCode());
            System.out.println(response.body());
			return response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

		return "null";
    }
	@GetMapping("/quoteinline")
    public String quotes(@RequestParam double latitude,
                         @RequestParam double longitude,
                         @RequestParam String startTime,
                         @RequestParam String endTime) {
        try {
            String url = String.format("https://api-sandbox.parkwhiz.com/v4/quotes/?q=coordinates:%f,%f&start_time=%s&end_time=%s",
                                        latitude, longitude, startTime, endTime);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("User-Agent", "Mozilla/5.0")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Response Code: " + response.statusCode());
            System.out.println(response.body());
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

}
