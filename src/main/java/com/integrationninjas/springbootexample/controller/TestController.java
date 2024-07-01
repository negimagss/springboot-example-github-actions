package com.integrationninjas.springbootexample.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

}
